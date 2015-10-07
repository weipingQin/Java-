package chapter04.aio.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;

import static chapter04.socket.Commons.*;

public class FileReadCompletion implements
		CompletionHandler<Integer, FileChannel> {
	
	private ByteBuffer byteBuffer;
	
	private long nowPosition;
	
	private AsynchronousFileChannel readFileChannel;
	
	public FileReadCompletion(ByteBuffer byteBuffer , AsynchronousFileChannel readFileChannel) {
		this.byteBuffer = byteBuffer;
		this.readFileChannel = readFileChannel;
	}

	@Override
	public void failed(Throwable e, FileChannel writeChannel) {
		e.printStackTrace();
	}

	@Override
	public void completed(Integer result, FileChannel writeChannel) {
		if(result > 0) {
			byteBuffer.flip();
			try {
				int writeLength = writeChannel.write(byteBuffer, nowPosition);
				nowPosition += writeLength;
			} catch (IOException e) {
				/*可以加入自己的处理哦*/
			}
			byteBuffer.clear();
			readFileChannel.read(byteBuffer, nowPosition , writeChannel , this);
		}else {
			closeStreams(readFileChannel , writeChannel);
		}
	}

	public long getNowPosition() {
		return nowPosition;
	}

}
