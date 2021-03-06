package com.kingston.mmorpg.framework.net.socket.codec;

import com.kingston.mmorpg.framework.net.socket.codec.mina.CodecContext;
import com.kingston.mmorpg.framework.net.socket.message.Message;
import com.kingston.mmorpg.framework.net.socket.serializer.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class ReflectEncoder implements IMessageEncoder {

    private static Logger logger = LoggerFactory.getLogger(ReflectEncoder.class);

    @Override
    public byte[] writeMessageBody(Message message) {
        ByteBuffer out = ByteBuffer.allocate(CodecContext.WRITE_CAPACITY);
        //写入具体消息的内容
        try {
            Serializer messageCodec = Serializer.getSerializer(message.getClass());
            messageCodec.encode(out, message, null);
        } catch (Exception e) {
            logger.error("读取消息出错,模块号{}, 异常{}",
                    new Object[]{message.getCmd() ,e});
        }
        out.flip();

        byte[] body = new byte[out.remaining()];
        out.get(body);
        return body;
    }

}