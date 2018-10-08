package netty.TcpPackage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.protocol.MyProtocol;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    private byte[] data;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // super.channelRead(ctx, msg);
        MyProtocol body = (MyProtocol) msg;
        System.out.println("this is server  :  " + body.toString());

        MyProtocol buf= (MyProtocol) msg;

        //System.out.println("this is client : "+buf.get.toString());
      //  receiveImage(buf.getContent(),"E:\\code\\Netty\\src\\main\\resources\\images\\jin2.jpg");

        String res = "server have received message and send you a picture";
        MyProtocol response = new MyProtocol(res.getBytes().length, res.getBytes());
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

        cause.printStackTrace();
        ;
        ctx.close();
    }

    /**
     * 接收信息
     * @param data
     */
    public void receiveImage(byte[] data,String path) throws IOException {
        FileImageOutputStream fo = new FileImageOutputStream(new File(path));
        fo.write(data, 0, data.length);
        fo.close();
    }

}
