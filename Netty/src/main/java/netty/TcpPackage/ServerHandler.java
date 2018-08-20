package netty.TcpPackage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.MyProtocol;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       // super.channelRead(ctx, msg);
        MyProtocol body= (MyProtocol) msg;
        System.out.println("this is server  :  "+body.toString());

        String res="server have received message";
        MyProtocol response=new MyProtocol(res.getBytes().length,res.getBytes());
        ctx.writeAndFlush(response);
       // ctx.channel().close();

//        String body= (String) msg;
//        System.out.println(" receive msg : "+body+ " counter is :" + ++counter);
//        ctx.write("received your message");
//        ctx.flush();
//        System.out.println("已经发送");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        cause.printStackTrace();;
        ctx.close();
    }
}
