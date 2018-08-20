package netty.TcpPackage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import protocol.MyProtocol;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private byte[] req; //接收msg
    private int counter;

    public ClientHandler() {
//        req = ("BazingaLyncc is learner" + System.getProperty("line.separator"))
//            .getBytes();
        req = ("In this chapter you general, we recommend Java Concurrency in Practice by Brian Goetz. His book w"
                + "sdsa ddasd asdsadas dsadasdas").getBytes();
    }


    //
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf message=null;  //创建一个容器来contain req的byte
//
//        message= Unpooled.buffer(req.length);
//        message.writeBytes(req);
//        ctx.writeAndFlush(message);
//        message= Unpooled.buffer(req.length);
//        message.writeBytes(req);
//        ctx.writeAndFlush(message);
        String ress = "i am client ,if you receive please reply";
        MyProtocol req = new MyProtocol(ress.getBytes().length, ress.getBytes());
        ctx.writeAndFlush(req);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       // super.channelRead(ctx, msg);
        System.out.println("msg");
        MyProtocol buf = (MyProtocol) msg;
        System.out.println("this is client : "+buf.toString());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        ctx.close();
    }
}
