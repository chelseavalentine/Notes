# HTTP API Design Guide

Notes taken on the [_HTTP API Design Guide](https://github.com/interagent/http-api-design).

### Foundations

* require connections w/ TLS to access the API
  - return `403 Forbidden`

* require versioninig in the `Accept` header


### Requests

* accept serialized JSON in request bodies

* naming resources:
  - use the plural version of a resource name unless the resource is a singleton within the system (eg. `/status`)

* actions
  - use endpoint layouts that don't need special actions for individual resources
  - if you need special actions, put them under a standard `actions` prefix
  - ie. `/resources/:resource/action/:action`

* support non-id dereferencing for convenience
  - eg. can use name or id


### Responses

* provide `created_at` and `updated_at` timestamps for resources by default, unless they don't make sense for the resource

* generate structured errors, with an `id`, `message`, and `url` (to docs describing the error)

* show rate limit status in the `RateLimit-Remaining` response header


### Artifacts

* provide a machine-readable JSON schema
  - tool: [prmd](https://github.com/interagent/prmd)

* provide human-readable docs
  - easier to do this if you use prmd

* describe stability of your API
