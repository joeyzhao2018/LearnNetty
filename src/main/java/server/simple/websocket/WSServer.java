package server.simple.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WSServer {
    public static void main(String[] args) throws  Exception{

        EventLoopGroup mainGroup =new NioEventLoopGroup();
        EventLoopGroup subGroup =new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();

        server.group(mainGroup,subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitializer());
        try{
            ChannelFuture channelFuture=server.bind(8088).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();

        }

    }
}
