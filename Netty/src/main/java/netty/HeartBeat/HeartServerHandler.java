package netty.HeartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartServerHandler extends ChannelInboundHandlerAdapter {
    private int loss_count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server active.....");
        System.out.println("message   :  "+msg.toString());

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object msg) throws Exception {


        System.out.println("server active........");
        System.out.println();
        if (msg instanceof IdleStateEvent) {
            IdleStateEvent evt = (IdleStateEvent) msg;
            if (evt.state() == IdleState.READER_IDLE) {
                System.out.println("lose connect");
                loss_count++;
                if (loss_count > 2) {
                    System.out.println("shutdown this inactive channel");
                    ctx.channel().close();
                }
            }
        } else {
            super.userEventTriggered(ctx, msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
