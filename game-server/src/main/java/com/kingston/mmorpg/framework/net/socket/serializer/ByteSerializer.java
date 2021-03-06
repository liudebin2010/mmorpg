package com.kingston.mmorpg.framework.net.socket.serializer;

import com.kingston.mmorpg.framework.net.socket.codec.ByteBuffUtil;

import java.nio.ByteBuffer;

public class ByteSerializer extends Serializer {

	@Override
	public Byte decode(ByteBuffer in, Class<?> type, Class<?> wrapper) {
		return ByteBuffUtil.readByte(in);
	}

	@Override
	public void encode(ByteBuffer out, Object value, Class<?> wrapper) {
		ByteBuffUtil.writeByte(out, (byte)value);
	}

}
