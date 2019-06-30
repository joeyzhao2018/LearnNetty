# LearnNetty
learning netty + spring java framework

Following the tutorial
http://tinyurl.com/y2ydadzw


 **IDEA Intellij tips**
 1. Preferences => Maven => check "Import Maven Projects Automatically"
  and  "Automatically download sources & documentations"
  
 2. Define a live template http://tinyurl.com/y232ksoa
 
 **Knowledge Notes**
 1. HTTP 1.1 will open a CONNECTION: Keep-alive (long connection)
 2. Websocket client-side JavaScript API:
    a. var socket= new WebSocket("ws://[ip]:[port]")
    b. Lifecycle events : onopen() onmessage() onerror() onclose() 
    c. Proactive methods: Socket.send(), Socket.close()
    
 
 **Misc**
 1. Console println to show the life cycle of a channel
 to clearly show the output: use `curl {ip}:8088` 
    Channel adding handler class
    Channel registering
    Channel Active
    channelRead0
    DefaultHttpRequest(decodeResult: success, version: HTTP/1.1)
    GET / HTTP/1.1
    Host: 192.168.0.22:8088
    User-Agent: curl/7.54.0
    Accept: */*
    /192.168.0.22:57882
    after WriteAndFlush
    channelRead0
    EmptyLastHttpContent
    Channel Read Completed
    Channel Read Completed
    Channel INACTIVE
    Channel removing
    Channel removing handler class
    
    