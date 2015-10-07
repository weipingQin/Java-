package chapter04.file;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import sun.nio.ch.DirectBuffer;

public class FileUtils {

	public final static int COPY_FILE_SIZE = 1024 * 1024;

	public static void copyFile(String srcFileName, String dstFileName) throws IOException {
		File srcFile = new File(srcFileName);
		File dstFile = new File(dstFileName);
		if (!srcFile.exists())
			throw new RuntimeException("src file not exists!");
		if (dstFile.exists())
			throw new RuntimeException("dst file exists!");
		DataInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = new DataInputStream(new FileInputStream(srcFile));
			outputStream = new FileOutputStream(dstFile);
			int fileAvailable = inputStream.available();// 文件小于2G情况下一般不存在问题
			if (fileAvailable <= COPY_FILE_SIZE) {
				byte[] bytes = new byte[fileAvailable];
				inputStream.readFully(bytes);
				outputStream.write(bytes);
			} else {
				byte[] bytes = new byte[COPY_FILE_SIZE];
				int len = inputStream.read(bytes);
				while (len > 0) {
					outputStream.write(bytes, 0, len);
					len = inputStream.read(bytes);
				}
			}
		}finally {
			closeStream(inputStream, outputStream);
		}
	}
	
	public static void copyFileByByteBuffer(String srcFileName , String dstFileName , 
			ByteBuffer byteBuffer , boolean cleanDirectBuffer) throws IOException {
		File srcFile = new File(srcFileName);
		File dstFile = new File(dstFileName);
		if(!srcFile.exists()) throw new RuntimeException("src file not exists!");
		if(dstFile.exists()) throw new RuntimeException("dst file exists!");
		FileChannel inFileChannel = null;
		FileChannel outFileChannel = null;
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(dstFile);
		try {
			inFileChannel = in.getChannel();
			outFileChannel = out.getChannel();
			int size = inFileChannel.read(byteBuffer);
			while(size > 0) {
				byteBuffer.flip();
				outFileChannel.write(byteBuffer);
				byteBuffer.clear();
				size = inFileChannel.read(byteBuffer);
			}
			//inFileChannel.transferTo(0, count, target)
		}finally {
			closeStream(in , out);
			byteBuffer.clear();
			if(byteBuffer.isDirect() && cleanDirectBuffer) {
				((DirectBuffer)byteBuffer).cleaner().clean();
			}
		}
	}
	
	public static void copyFileByChannel(String srcFileName , String dstFileName) throws IOException {
		File srcFile = new File(srcFileName);
		File dstFile = new File(dstFileName);
		if(!srcFile.exists()) throw new RuntimeException("src file not exists!");
		if(dstFile.exists()) throw new RuntimeException("dst file exists!");
		FileChannel inFileChannel = null;
		FileChannel outFileChannel = null;
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(dstFile);
		try {
			long position = 0;
			inFileChannel = in.getChannel();
			outFileChannel = out.getChannel();
			while(position < inFileChannel.size()) {
				long realCopy = outFileChannel.transferFrom(inFileChannel , position , COPY_FILE_SIZE);
				position += realCopy;
			}
			inFileChannel.transferTo(0, srcFile.length(), outFileChannel);
		}finally {
			closeStream(in , out);
		}
	}
	
	public static void copyFileByMappedByteBuffer(String srcFileName , String dstFileName) throws IOException {
		FileChannel inFileChannel = new RandomAccessFile(srcFileName , "r").getChannel();
		FileChannel outFileChannel = new RandomAccessFile(dstFileName , "rw").getChannel();
		try {
			long fileSize = inFileChannel.size();
			long position = 0;
			//MappedByteBuffer mappedByteBuffer = outFileChannel.map(MapMode.READ_WRITE, 0, fileSize);
			//((DirectBuffer)mappedByteBuffer).cleaner().clean();
			while(position < fileSize) {
				long copyFileSize = Math.min((fileSize - position), COPY_FILE_SIZE);
				MappedByteBuffer mappedByteBuffer = outFileChannel.map(MapMode.READ_WRITE,  position ,  copyFileSize);
				inFileChannel.read(mappedByteBuffer);
				position += mappedByteBuffer.position();
			}
		}finally {
			closeStream(outFileChannel);
		}
	}

	public static void closeStream(Closeable... streams) {
		if (streams == null)
			return;
		for (Closeable stream : streams) {
			CloseOneStream(stream);
		}
	}

	private static void CloseOneStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String []args) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
		long start = System.currentTimeMillis();
		//copyFile("d:/TortoiseSVN_1.7.2.22327_x64_XiaZaiBa.zip" , "d:/TortoiseSVN_1.7.2.22327_x64_XiaZaiBa_2222.zip");
		copyFileByByteBuffer("d:/淘宝Java工具安装包.exe" , "d:/淘宝Java工具安装包2222.exe" , byteBuffer , false);
		System.out.println(System.currentTimeMillis() - start);
	}
	
	public static void copyInputStreamToOutputStream(
            InputStream inputStream, OutputStream outputStream,
            int fileAvailable) throws IOException {
        if (fileAvailable <= COPY_FILE_SIZE) {
            byte[] bytes = new byte[fileAvailable];
            new DataInputStream(inputStream).readFully(bytes);
            outputStream.write(bytes);
            outputStream.flush();
        } else {
            byte[] bytes = new byte[COPY_FILE_SIZE];
            int len = inputStream.read(bytes);
            while (len > 0) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
                len = inputStream.read(bytes);
            }
        }
    }
}
