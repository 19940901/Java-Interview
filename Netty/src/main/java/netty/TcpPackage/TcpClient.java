package netty.TcpPackage;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.protocol.MyDecoder;
import netty.protocol.MyEncoder;

public class TcpClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception{

        EventLoopGroup group=new NioEventLoopGroup();
        try {
            Bootstrap b=new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                           // ChannelPipeline p=socketChannel.pipeline();
                            ch.pipeline().addLast(new MyEncoder());
                            ch.pipeline().addLast(new MyDecoder());
                           // ch.pipeline().addLast(new StringEncoder());
                          //  ch.pipeline().addLast(new StringDecoder());

                           ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            ChannelFuture future=b.connect(HOST,PORT).sync();
            future.channel().writeAndFlush("hello server im clientTCP");
            future.channel().closeFuture().sync();  //如果连接没有断开，则一直会执行下去
            System.out.println("t=========================");
        }finally {
            group.shutdownGracefully();
        }

    }

}
