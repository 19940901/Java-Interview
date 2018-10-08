package netty.HeartBeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class HeartClient {
    private int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    private String HOST = System.getProperty("host", "127.0.0.1");

    public void start() {
        final HeartClientHandler handler=new HeartClientHandler(this);
        EventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture f=null;
        try {
            Bootstrap b = new Bootstrap();

            b.group(group)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(handler);
                        }
                    });
           f=b.connect(HOST, PORT).addListener(new ClientListener(this));
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          // group.shutdownGracefully();

        }
    }

    public static void main(String[] args) {
        new HeartClient().start();
    }
}
