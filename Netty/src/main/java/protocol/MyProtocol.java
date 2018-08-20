package protocol;


import java.util.Arrays;

/**
 * 自定义传输协议
 */
public class MyProtocol {
/**
 *head
 */
private int head_data= ConstantValue.HEAD_DATA;

/**
 * 消息的长度
 */
private int contentLength;

/**
 * 消息的内容
 */

private byte[] content;

    public MyProtocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }

    public int getHead_data() {
        return head_data;
    }

    public int getContentLength() {
        return contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setHead_data(int head_data) {
        this.head_data = head_data;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MyProtocol{" +
                "head_data=" + head_data +
                ", contentLength=" + contentLength +
                ", content=" + new String(content) +
                '}';
    }
}
