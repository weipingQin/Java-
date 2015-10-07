package chapter04.nio.server;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DownloadFileProcessor implements Closeable {

	private final static String FILE_PTH = "d:/javaA/upload/Hadoop.PDF";
	
	private FileChannel fileChannel;
	
	protected ByteBuffer fileByteBuffer = ByteBuffer.allocate(8192);
	
	public DownloadFileProcessor() throws FileNotFoundException {
		fileChannel = new FileInputStream(FILE_PTH).getChannel();
	}
	
	public int read() throws IOException {
		fileByteBuffer.clear();
		int count = fileChannel.read(fileByteBuffer);
		fileByteBuffer.flip();
		return count;
	}

	@Override
	public void close() throws IOException {
		fileChannel.close();
	}

	public ByteBuffer getFileByteBuffer() {
		return fileByteBuffer;
	}
}
