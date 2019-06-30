package server.simple.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.time.LocalDateTime;

/**
 * message handler
 * TextWebSocketFrame is the media of text message
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private  static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        String content=msg.text(); //get the message from client
        System.out.println("Received the message: "+content);

//        for (Channel channel: clients){
//            channel.writeAndFlush(new TextWebSocketFrame(
//                    String.format("[At %s got msg: %s", LocalDateTime.now(), content)
//                    )); //cannot use String here because in websocket the medium is the corresponding 'frame'
//        }

        //same as above
        clients.writeAndFlush(new TextWebSocketFrame(
                String.format("[At %s got msg: %s", LocalDateTime.now(), content)
        ));


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        super.handlerAdded(ctx);
        clients.add(ctx.channel()); //got the channel for client and put it in clients channel group

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

//        super.handlerRemoved(ctx);

        //when handlerremoved is triggered, channelgroup will automatically remove the channel,
        // i.e. the line below is redundant
//        clients.remove(ctx.channel());
        System.out.println("Client is dropped ID: "+ctx.channel().id().asLongText()); //id has a long and shorten version
        System.out.println("Shorten ID:"+ctx.channel().id().asShortText());
    }

}
