# Software Development Security & Architecture Summary
*Revision guide for a developer with 3+ years of experience*

---

## 1. Authentication vs. Authorization

**Rule of Thumb:** "N before Z" — Authentication always happens before Authorization.

*   **Authentication (AuthN):** Verifies *who* you are (e.g., login, password, OTP, biometrics).
*   **Authorization (AuthZ):** Determines *what* you can do after login (e.g., permissions, admin rights).

### What a 3-Year Developer Should Know
*   **Protocols:** OAuth 2.0 (Authorization) and OpenID Connect / OIDC (Authentication).
*   **Storage:** Never store passwords in plain text; use slow hashing like `bcrypt` or `Argon2`.
*   **JWT Handling:** Sign tokens securely (RS256) and handle expiration/refresh tokens.
*   **Access Control:** RBAC (Role-Based) and ABAC (Attribute-Based). Prevent IDOR (Insecure Direct Object Reference) vulnerabilities.

---

## 2. Web Security State Management

To secure web traffic, developers must use SSL/TLS combined with a method to track user state.

### SSL / TLS
*   **Role:** Handles data in transit. It creates an encrypted tunnel between the browser and the server.
*   **Fact:** It does not know who the user is; it only ensures no one can eavesdrop on the traffic.

### Session-Based Auth (Stateful)
*   The server saves a session record in a database/Redis and sends a **Session ID** to the user's browser in a cookie.
*   **Pros:** Easy to revoke access instantly by deleting the session from the database.
*   **Cons:** Server must query the database on every single network request, creating a scaling bottleneck.

### JWT / JSON Web Token (Stateless)
*   The server generates a signed payload string (JWT) and sends it to the client. The server does not store it.
*   **Pros:** Highly scalable for APIs and microservices because servers don't need a database to verify the token.
*   **Cons:** Hard to revoke before expiration. Anyone can decode and view the payload data.

---

## 3. Core Web Concepts: Cookies, Encoding, and Encryption

These three concepts describe how data is stored, formatted, and hidden.


| Concept | What it is | Is it secret? | Key required? | Example Use Case |
| :--- | :--- | :--- | :--- | :--- |
| **Cookie** | Browser storage mechanism | No | No | Storing a session ID. |
| **Encoding** | Data formatting (e.g., Base64) | No | No | Making JSON safe for URL headers. |
| **Encryption**| Data scrambling (e.g., AES) | Yes | **Yes** | Protecting passwords in transit over SSL. |

### Cookie Security Flags
*   **HttpOnly:** Prevents JavaScript from accessing the cookie (protects against XSS).
*   **Secure:** Forces the browser to send the cookie only over HTTPS.
*   **SameSite:** Restricts the cookie from being sent on cross-site requests (protects against CSRF).

---

## 4. Cross-Site Tracking and Ads

*   **The Myth:** Instagram directly reading another website's cookies. This is **impossible** due to the browser's Same-Origin Policy.
*   **The Reality:** Ad networks track users via **Third-Party Cookies** and tracking pixels.
*   **How it works:** When you visit Site A, an embedded pixel from Site B (like Meta) reaches out to Site B's servers. Site B's servers place a cookie on your browser under Site B's domain. When you later visit Site B directly (e.g., Instagram), your browser sends that cookie back, letting them connect your browsing history.

---
*Generated for local revision. Keep learning!*
