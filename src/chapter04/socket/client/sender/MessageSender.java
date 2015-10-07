package chapter04.socket.client.sender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import chapter04.socket.SocketWrapper;
import static chapter04.socket.Commons.*;

public class MessageSender implements Sendable {
	
	private String message;//普通的message消息
	
	private byte []messageBytes;//消息发送时使用
	
	private int length = 0;
	
	public MessageSender(String []tokens) throws UnsupportedEncodingException {
		if(tokens.length >= 2) {
			message = tokens[1];
			this.messageBytes = message.getBytes(DEFAULT_MESSAGE_CHARSET);
			this.length = messageBytes.length;
		}else {
			throw new RuntimeException("请在sendMsg后面添加内容。");
		}
	}

	/**
	 * 发送内容处理
	 * @throws IOException 
	 */
	@Override
	public void sendContent(SocketWrapper socketWrapper) throws IOException {
		println("我此时想服务器端发送消息：" + message);
		socketWrapper.write(length);
		socketWrapper.write(messageBytes);
		println("发送消息完毕。");
	}

	@Override
	public byte getSendType() {
		return SEND_MESSAGE;
	}

}
