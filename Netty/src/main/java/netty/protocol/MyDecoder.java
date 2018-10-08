package netty.protocol;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * <pre>
 * 自己定义的协议
 *  数据包格式
 * +——----——+——-----——+——----——+
 * |协议开始标志|  长度             |   数据       |
 * +——----——+——-----——+——----——+
 * 1.协议开始标志head_data，为int类型的数据，16进制表示为0X76
 * 2.传输数据的长度contentLength，int类型
 * 3.要传输的数据
 * </pre>
 */
public class MyDecoder extends ByteToMessageDecoder {

    //基本数据长度，头数据+contentLength
    private final int baseLength = 4 + 4;


    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {


    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
//可读长度需要大于基本长度
        if(in.readableBytes()>=baseLength){
            //防止数据过长，socket攻击
            if(in.readableBytes()>2048){
                in.skipBytes(in.readableBytes());
            }

            //begin index
            int beginIndex;

            while (true){

                beginIndex=in.readerIndex();
                in.markReaderIndex();

                if (in.readInt()==ConstantValue.HEAD_DATA){
                    break;
                }

                //not touch head
                in.resetReaderIndex();
                in.readByte();
                if(in.readableBytes()<baseLength){
                    return;
                }

            }
            // message length
            int length=in.readInt();
            if (in.readableBytes()<length){
                in.readerIndex(beginIndex);
                return;
            }

            //read byte length

            byte[] buf=new byte[length];
            in.readBytes(buf);
            MyProtocol protocol=new MyProtocol(buf.length,buf);
            out.add(protocol);
        }

    }
}
