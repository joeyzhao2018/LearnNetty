package server.simple.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerInitializer extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline=ch.pipeline();

        pipeline.addLast(new HttpServerCodec()); //Websocket is based on HTTP Protocol, need an http encoder/decoder

        pipeline.addLast(new ChunkedWriteHandler()); //Support for writing a large data stream

        pipeline.addLast(new HttpObjectAggregator(1024*64)); //Very important handler: Aggreate HTTP messages --> HttpFullRequest/HttpFullResponse

        // ^^^^^^^^^^^^^^^^^^^^^^^ above 3 are handlers for HTTP^^^^^^^^^^^^^^^^^^^^^

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        /**
         * routing client /ws requests, this is the handler used for websocket protocol
         * handles  heavy lifting: handshaking (close, ping, pong) (ping+pong =heartbeat)
         * In websocket, data is transmitted by frames. Different data types=>different frames
         */

        pipeline.addLast(new ChatHandler());








    }
}
