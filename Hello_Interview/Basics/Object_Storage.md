# Object Storage

- [Overview](#overview)
- [Working of Object Storage](#how-does-object-storage-work)
- [Why is it ideal](#how-is-object-storage-ideal)
- [Things to know for Interview](#things-to-know-in-the-context-of-interview)


## Overview:

A database specifically designed for large files (Binary Large OBjects - BLOB) like .mov, .jpeg, .mpg, .json, .txt, .wav.

Why don't we just store them in a regular database:
- Big files bloat pages, slow every query
- Replicas and copy backup blobs, huge lag and downtime
- DB storage costs more than object store

## How does Object Storage work?

When a client wants to get or view a file, they make request to a metadata server (these metadata servers are part of the bob storage). 

These metadata servers are responsible for using an index to lookup where that file is located.

## How is Object Storage ideal?

Unlike a folder structure which is used in most of the storage mechanism, Object Storage uses just a flat structure.

In Object Storage we cannot modify files (Immutable writes). We can create new versions or overwrite the file entirely.

## Things to know in the context of Interview:

1. Large files stored in Object Storage, but metadata should be stored in traditional database.
- Examples:
    - Photos/videos for social media or messaging app
    - User uploaded documents for file sharing or collaboration tools
    - Static web assests (image, CSS, JS) served behind a CDN
    - Log and event archieves feeding analytics or security pipelines
    - Backup snapshots or database dumps for disaster recovery
    - Machine learning training dataset

2. We can upload/download directly to/from object store via pre-signed URLs.
- In case of upload, we don't need to do it via metadata server. It uses extra bandwidth. 
- In general case, we are going to have server request from the object store (called pre-signed url). This is a url which the client can use to HTTP put request.

3. Large files are going to be uploaded in chuncks and object storage will stitch the file back together
- There is a limit to the size of the file that can be posted on the internet at either browser level, gateways, servers, etc.

Examples of BLOB storage: Amazon S3, Google Cloud Storage, Azure BLOB Storage.