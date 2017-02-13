# HTTP/2 Explained

Notes from reading [http2 explained](https://github.com/bagder/http2-explained).


### HTTP today

* HTTP pipelining = way to send another request while waiting on a previous request
  - disabled by default in browsers
  - performance and resource penalty with each new line creation


### Things done to overcome latency pains

* __sharding__ _(in the context of HTTP)_: serving aspects of your service on as many different hosts as possible
  - allows you to increase the number of TCP connections


### Updating HTTP

* __Internet Engineering Task Force (IETF)__: organization that develops & promotes Internet standards, mostly on the protocol-level

* http2 is based on a draft of SPDY, a protocol developed by Google


### http2 concepts

* for plain-text HTTP 1.1, you can negotiate http2 by using an `Upgrade:` header
  - server responds with a "101 Switching" status, and then speaks http2 from then onwards
  - can keep http2 connections alive longer and re-use it more than typical HTTP1 connections

* Next Protocol Negotiation (NPN) is used to negotiate SPDY w/ TLS servers
  - not a proper standard, so IETF came up with ALPN (Application Layer Protocol Negotiation)
  - protocol negotiation is the way of asking the server to use http2 instead of older protocols
    + usually requires an additional round-trip to get it to give you the response using the newer protocol

+ ALPN vs. NPN
  - ALPN: client gives server a list of protocols in order of preference, and server picks the one it wants
  - NPN: the client makes the final choice


### The http2 protocol

* http2 is a binary protocol
  - significant because implementation is simpler since you don't have to deal with text parsing
  - makes it easier to separate actual protocol parts from the framing
  - it sends binary frames, with the following setup:
    + length, type, flags, stream identifier, and frame payload

* DATA and HEADERS are the two most fundamental binary frames that map to HTTP 1.1 features

#### Streams

* each binary frame is associated with a stream
  - __stream__: an independent, bidirectional sequence of frames exchanged b/t the client and a server w/i a http2 connection
  - 1 http2 connection can have multiple concurrently-open streams

* streams can be established & used unilaterally/shared by either the client/server, and be closed by either endpoint

* the order frames are sent is significant since recipients process frames in order of receival

* streams have weights, which tell the peer how to prioritize streams
  - in case of resource restraints, forcing server to select which streams to send first

* client can tell server which other streams this stream depends on via the PRIORITY frame

#### Header compression

* HTTP is stateless and as such needs all the info to service one request to be self-contained
  - leads to a lot of duplicate info being sent in several requests

* HTTPS & SPDY compression were vulnerable to BREACH and CRIME attacks
  - so http2 uses HPACK (Header Compression for HTTP/2), which makes it harder to exploit compression

#### Reset

* with HTTP/1 you can't stop a HTTP message once it's been sent

* HTTP/2 makes it easier to tear down connections, and prevent wasted bandwidth, because you can stop the message
  - can use http2's RST_STREAM frame

#### Server push

* cache push: when the server sends more resources than the client asks for, because it predicts that it'll ask for the additional resources as well
  - client must explicitly allow the server to do this
    + even still, the client can terminate a pushed stream any time w/ RST_STREAM

* each individual http2 stream has its own advertised flow window in which it accepts data
  - for each stream, client and server need to tell each other that it has enough room to handle incoming data
  - only DATA frames are flow controlled


### Extensions

* http2 protocol: receiver must read & ignore all unknown frames (frames w/ unknown frame type)
  - 2 parties can negotiate the use of new frame types on a hop-by-hop basis, but those frames can't change state or be flow controlled


### http2 in Firefox

* http2 is enabled by default

* Firefox only implements http2 over TLS, so only https:// sites

* servers and clients need to agree on what draft version of the protocol they implement

* headers in the Network tool when talking http2 were converted from http2's binary format into the old-style HTTP 1.x look-alike headers


### http2 in Chromium

* Chrome only implements http2 over TLS, so only https:// sites


### http2 in `curl`

* `curl` converts incoming http2 headers to HTTP 1.x style headers, so the user can work with that style
