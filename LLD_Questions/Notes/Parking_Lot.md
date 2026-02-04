# Parking Lot

- [Requirements](#requirements)
- [Flow](#flow)
- [Entities](#entities)

## Requirements

Parking Lot
- Entry Gates
- Exit Gates
- Building
    - Levels
        - Parking Spots

## Flow

- A `vehicle` come at `Entry Gate`
- `Parking spot` is assigned to that vehicle based on a `parking strategy`.
    - A building can have `multiple leveles`.
- If the parking spot is available, a `ticket` is generated for particular vehicle.
- At `exit gate`, cost computation happens.
- `Payment` is made at the exit gate for the given parking spot. After this the parking spot is free again.

## Entities

- Vehicle
- Parking Spot
- Parking Level
- Entry Gate
- Exit Gate
- Ticket
- Payment