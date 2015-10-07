package chapter03.oom;

import java.nio.ByteBuffer;

public class ByteBufferOOM {

	//-XX:MaxDirectMemorySize=256m
	public static void main(String []args) {
		ByteBuffer.allocateDirect(257 * 1024 * 1024);
		//DirectBuffer byteBuffer = (DirectBuffer)ByteBuffer.allocateDirect(256 * 1024 * 1024);
		//((DirectBuffer)byteBuffer).cleaner().clean();
		//ByteBuffer.allocateDirect(1024 * 1024);
	}
}
