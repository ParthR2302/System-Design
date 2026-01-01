# API Design

- [Overview](#overview)
- [REST](#rest)
- [GraphQL](#graphql)
- [RPC](#rpc)
- [Followup Questions](#follow-up-questions)

<br>

- [Resources](#resources)

## Overview:

API allows two set of softwares to communicate with each other using a set of definitions and protocol.

Cient to server communications using REST and GraphQL. Internal communications between server to server using RPC (Remote Procedure Call).

There are some real time protocols like web-socket, server sense events, web RTC for open continuous connection.

## REST:

REST is build around a concept of resources (Resource Modeling).
- Resources are just our core entities. 
- Plural nouns.
- These resources are represented in the URL path and they define the data that is being acted upon.

**HTTP Methods:** GET, POST, PUT, PATCH, DELETE

### Inputs:

In REST APIs, inputs come in one of below three forms.
- Path Param, Query Param or Request Body.


## GraphQL

GraphQL is a query language for APIs where:
- Client specifies exactly what data it needs
- Single endpoint
- Strongly typed schema

**Common Issues with GraphQL:** 
- N+1 query problem.
    - Example: Get posts for users
        - One query to get users, if there are N users then additional N queries to get posts for each users.
    - One of the Solutions: Use data loader. Batch (user_id in this case) and create one query to get the child data (posts in this case).

### Comparision

**REST:** Server decides what data is returned.

**GraphQL:** Client decides what data is returned.

Key trade-off: Flexibility vs simplicity.


## RPC

Used for intra-microservices communications.

Its lighter and faster than REST. Instead of working with resources and URLs, RPC is modeled around calling methods directly on a service. Its almost like making a normal function call but over a local network.

The messages in RPC are defined by data types using protocol bufferes (protobuff).


## Follow-up questions:

### Pagination

Pagination is useful when we have any endpoint thats going to return tons of resources
1. Page based or Offset based pagination
2. Cursor based pagination

### Security

We check authenticity either by JWT or Session Token.
- These are put on the header of the HTTP request

## Resources:

- https://www.hellointerview.com/learn/system-design/core-concepts/api-design
- https://www.hellointerview.com/learn/system-design/patterns/realtime-updates

