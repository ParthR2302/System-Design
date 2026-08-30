# Redis in Ticket Master

### Redis Reservation Layer

Redis is used for both:
1. Distributed locking
2. Temporary reservation state storage

When a user selects a seat, Booking Service tries to acquire a Redis lock using an atomic operation:

```text
SET ticket:{ticketId} reservationData NX EX 600
```

- `NX` -> lock only if key does not already exist
- `EX 600` -> auto expire after 10 minutes

Example:

```json
ticket:A12 -> {
  "reservationId": "R123",
  "userId": "U1",
  "ticketId": "A12",
  "expiresAt": "10:10PM"
}
```

This helps in:
- preventing double booking
- tracking reservation ownership
- handling reservation expiry automatically
- avoiding cron jobs for cleanup

### Booking Flow

#### Seat Reservation

```text
Client
 -> Booking Service
 -> Redis SET NX EX
```

If lock acquisition succeeds:
- seat is temporarily reserved
- Booking Service returns:
  - reservationId
  - expiresAt

If lock acquisition fails:
- another user already reserved/booked the seat

---

#### Payment Confirmation

Client sends:

```json
{
  "reservationId": "R123"
}
```

Booking Service:
1. Validates reservation from Redis
2. Calls payment provider (Stripe)
3. On successful payment:
   - stores final booking in Postgres
   - marks ticket as BOOKED
   - deletes Redis reservation entry

---

#### Reservation Expiry

If user abandons checkout:
- Redis TTL automatically expires
- reservation key disappears
- seat becomes available again

No scheduled cleanup job required.

---

### Important Design Decision

Redis stores only temporary/transient reservation state.

Postgres stores permanent truth:
- finalized bookings
- booked ticket ownership
- payment records
- booking history