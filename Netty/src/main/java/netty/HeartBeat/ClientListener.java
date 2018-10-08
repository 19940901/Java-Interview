package netty.HeartBeat;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;

import java.util.concurrent.TimeUnit;

public class ClientListener implements ChannelFutureListener {
    private HeartClient client;

    public ClientListener(HeartClient client) {
        this.client = client;
    }

    public void operationComplete(ChannelFuture future) throws Exception {

        if (!future.isSuccess()){
            System.out.println("client is going to reconnect");
            final EventLoopGroup loop=future.channel().eventLoop();
            loop.schedule(new Runnable() {
                public void run() {
                    client.start();
                }
            },1L, TimeUnit.SECONDS);
        }
    }
}
