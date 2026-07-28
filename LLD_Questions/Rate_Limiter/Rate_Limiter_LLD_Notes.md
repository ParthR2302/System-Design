# LLD of Rate Limiter

## What is Rate Limiter

controls the number of requests a user can make to a system within a specific time period to prevent overload and ensure fair usage.

It has 2 main things:
- time_range (00:00 to 23:59, or can be any other combination)
- no. of requests in window (K)
  - It means we can send K requests for the given time window
  - Request number greater than K would be rate limited and not even reach the server


## Requirements with LLD perspective

1. Rate Limit the users based on userID and their tier (free or premium)
2. Support 4 types (and extensible)
   1. Token bucket, fixed window, sliding window log, and sliding window counter
3. Thread safe and efficient

## Class Diagram

User:
- userId : String
- tier : UserTier

UserTier <\<enum\>>
- FREE
- PREMIUM

RateLimiterService:
- rateLimiters : Map<UserTier, RateLimiter>
- \+ allowRequest(User) : Boolean

RateLimiter <\<abstract\>>
- \# config : RateLimitConfig
- \# type : RateLimitType
- \+ allowRequest(User) : boolean

TokenBucket extends RateLimiter 
- allowRequest(User) : boolean

FixedWindow extends RateLimiter 
- allowRequest(User) : boolean

SlidingWindowLog extends RateLimiter 
- allowRequest(User) : boolean

SlidingWindowCounter extends RateLimiter 
- allowRequest(User) : boolean

RateLimitConfig
- maxRequests : int
- windowIntSeconds : int

RateLimiterFactory
- \+ createRateLimiter(algo, config)