package chapter04.socket.client.sender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import chapter04.socket.SocketWrapper;
import chapter04.socket.client.exceptions.DirectNotExistsException;

import static chapter04.socket.Commons.*;
/**
 * 这里为下载文件的请求类
 * @author Administrator
 *
 */
public class GetFileSender implements Sendable {
	
	private String saveFilePath;
	
	private String getFileName;

	@Override
	public byte getSendType() {
		return GET_FILE;
	}
	
	public GetFileSender(String []tokens) {
		if (tokens.length >= 3) {
			saveFilePath = tokens[2];
			File file = new File(saveFilePath);
			if(file.exists() && file.isDirectory()) {
				this.getFileName = tokens[1];
				this.saveFilePath = file.getAbsolutePath() + File.separator;
			}else {
				throw new DirectNotExistsException(saveFilePath);
			}
		}else {
			throw new RuntimeException("消息格式存在问题，请使用help命令查看输入格式。");
		}
	}

	@Override
	public void sendContent(SocketWrapper socketWrapper) throws IOException {
		println("准备下载文件：" + getFileName);
		byte[] fileNameBytes = getFileName.getBytes(DEFAULT_MESSAGE_CHARSET);
		socketWrapper.write((short) fileNameBytes.length);
		socketWrapper.write(fileNameBytes);
		int status = socketWrapper.readInt();
		if(status != 1) {
			processErrorStatus(status);
		}else {
			long fileLength = socketWrapper.readLong();
			int readLength = 0 , i = 0;
			FileOutputStream out = new FileOutputStream(saveFilePath + getFileName);
			try {
				byte []bytes = new byte[DEFAULT_BUFFER_LENGTH];
				println("开始下载文件，文件长度为：" + fileLength);
				while(readLength < fileLength) {
					int len = socketWrapper.read(bytes);
					readLength += len;
					out.write(bytes, 0, len);
					if(++i % 1024 == 0) {
						print(".");
					}
				}
				println("开始下载完毕.......");
			}finally {
				closeStream(out);
				println("");
			}
		}
	}
	
	private void processErrorStatus(int status) {
		if(status == -1) {
			println("ERROR：文件下载失败，失败原因为服务器端没有找到指定的文件....");
		}else {
			println("ERROR：文件下载失败，失败原因不确定，返回失败错误号为：" + status);
		}
	}

}
