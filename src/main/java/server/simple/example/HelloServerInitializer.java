package server.simple.example;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * when channel is registered , the Initializer.initChannel will be called
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        //get the pipeline (the broader receiver/interceptor)
        ChannelPipeline pipeline=channel.pipeline();

        // HttpServerCodec : netty provided encoder/decoder
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        //
        pipeline.addLast("customHandler",new CustomHandler());

    }
}
