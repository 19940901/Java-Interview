package netty.TcpPackage;

import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import protocol.MyDecoder;
import protocol.MyEncoder;

import java.net.InetSocketAddress;

public class TcpServer {
    private int port;

    public TcpServer(int port){
        this.port = port;
    }

    public void start() throws  Exception{
        EventLoopGroup boss=new NioEventLoopGroup(1);
        EventLoopGroup worker=new NioEventLoopGroup();
        try {
            ServerBootstrap sbs=new  ServerBootstrap()
                    .group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))

                   .childHandler(new ChannelInitializer<SocketChannel>() {
                       protected void initChannel(SocketChannel ch) throws Exception {
                           ch.pipeline().addLast(new MyEncoder());
                           ch.pipeline().addLast(new MyDecoder());

                           ch.pipeline().addLast(new ServerHandler());
                       }
                   })
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture future=sbs.bind(port).sync();
            //future.channel().writeAndFlush("hdsakjhfdjshakjfdhsak").sync();
            System.out.println("server listening at port :"+ port);
            future.channel().closeFuture().sync();

        }finally {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
        }

    }
    public static void main(String[] args) throws Exception {
    int port;
    if(args.length>0){
        port=Integer.parseInt(args[0]);
    }else
    {
        port=8080;
    }
    new TcpServer(port).start();
    }
}
