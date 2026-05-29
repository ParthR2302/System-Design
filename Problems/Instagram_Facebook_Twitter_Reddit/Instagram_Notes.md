# Instagram, Facebook, Twitter, Reddit

## Things we would be supporting

- News feed
- Reddit style nested comment

## Functional Requirements (Objectives)

1. User can quickly see who they are following and who follows them
2. We can quickly load all posts for a given user
3. Low latency news feed from posts that a user follows
4. Posts can have configurable privacy type, as do followers
5. Users can comment on posts, and comments can be infinitely nested

Reads >> Writes

## Capacity Estimates

1. 100 characters a post ~ 100 byte for text, maybe ~100 for other metadata
2. 1 billion posts per day -> 1 Billion * 200 bytes * 365 days -> 73TB/year
3. On average users have 100 followers, some verified users have millions
4. Comments also 100 characters, so around 200 byte with metadata
5. Most comments on one post = 1 million, 1 million * 200 byte = 200MB

## Additional Notes

### Fetch Followers/Following

