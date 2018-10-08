package netty.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MyDecoder2 extends LengthFieldBasedFrameDecoder {
    public MyDecoder2(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                      int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment,
                initialBytesToStrip, failFast);
    }
}
