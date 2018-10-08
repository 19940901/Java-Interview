package netty.TcpPackage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.protocol.MyProtocol;

import javax.imageio.stream.FileImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private byte[] req; //接收msg
    private int counter;

    private byte[] data=new byte[2048];

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
       // ctx.writeAndFlush(new MyProtocol(getImage("E:\\code\\Netty\\src\\main\\resources\\images\\jin.jpg").length, data));

        ctx.writeAndFlush(req);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       // super.channelRead(ctx, msg);
        System.out.println("msg");



    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        ctx.close();
    }


    /**
     * 获取image
     */
    public byte[] getImage(String path) throws IOException {
        FileImageInputStream fin = new FileImageInputStream(new File(path));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buf = new byte[2048];
        int temp = 0;
        while ((temp = fin.read(buf)) != -1) {
            out.write(buf, 0, temp);
        }
        data = out.toByteArray();

        out.close();

        return data;
    }
}
