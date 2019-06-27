package server.simple.example;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {
    public static void main(String[] args) throws Exception{
        //main thread group : only accept requests, doesn't handle
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        // sub (child) thread group : only handles
        EventLoopGroup subGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(mainGroup,subGroup) // set main/sub goups for the server
                .channel(NioServerSocketChannel.class) // set the class for NIO two-way channel
                .childHandler(new HelloServerInitializer()); //set the handler for subGroup
        try{
            ChannelFuture channelFuture = bootstrap.bind(8088).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }

    }
}
