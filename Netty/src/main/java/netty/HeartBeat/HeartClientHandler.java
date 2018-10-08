package netty.HeartBeat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HeartClientHandler extends ChannelInboundHandlerAdapter {
    private int TRY_TIME = 3;
    private ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("heart beat", CharsetUtil.UTF_8));
    private int correnttime = 0;

    private HeartClient client;

    public HeartClientHandler(HeartClient client) {
        this.client = client;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        System.out.println("start time : " + new Date());
        ctx.fireChannelActive();

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        final EventLoopGroup loop = ctx.channel().eventLoop();
        loop.schedule(new Runnable() {
            public void run() {
                client.start();
            }
        }, 1L, TimeUnit.SECONDS);
        System.out.println("shutdown time :  " + new Date());

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        System.out.println("loop triggered ");
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.WRITER_IDLE) {
                if (correnttime <= TRY_TIME) {
                    System.out.println(" send message");
                    correnttime++;
                    ctx.channel().writeAndFlush(buf.duplicate());
                }
            }

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
