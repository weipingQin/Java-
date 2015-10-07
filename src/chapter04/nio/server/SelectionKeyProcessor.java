package chapter04.nio.server;

import static chapter04.socket.Commons.DEFAULT_MESSAGE_CHARSET;
import static chapter04.socket.Commons.closeStreams;
import static chapter04.socket.Commons.logInfo;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SelectionKeyProcessor {

	private SelectionKey selectionKey;

	private Selector selector;

	public SelectionKeyProcessor(SelectionKey selectionKey, Selector selector) {
		this.selectionKey = selectionKey;
		this.selector = selector;
	}

	public void processKey() throws IOException {
		try {
			if (selectionKey.isAcceptable()) {//接收
				processAccept();
			} else if (selectionKey.isReadable()) {//读取
				processRead();
			} else if (selectionKey.isWritable()) {//写
				processWrite();
			}
		}catch(IOException e) {//IO异常需要关闭，否则会导致无限制地报错，因为通道没结束，就会一直被检测
			SelectableChannel channel = selectionKey.channel();
			if(channel instanceof SocketChannel) {
				DownloadFileProcessor downloadFileProcessor = (DownloadFileProcessor)selectionKey.attachment();
				closeStreams(downloadFileProcessor , channel);
				logInfo("下载由于IO异常，结束.....");
			}
		}
	}

	private void processAccept() throws IOException {
		//System.out.println("一个新的连接................");
		ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();//这里获取到Server打开的通道
		SocketChannel channel = server.accept();//接受一个请求的Channel的通道
		channel.configureBlocking(false);//将接受请求的通道配置为非阻塞模式
		//System.out.println(channel.socket().getSendBufferSize());
		//channel.socket().setSendBufferSize(16 * 1024);
		channel.register(selector, SelectionKey.OP_READ);//将这个通道注册一个读取事件
	}

	private void processRead() throws IOException {
		SocketChannel channel = (SocketChannel)selectionKey.channel();//此时取出的为processAccept()注册了的通道
		channel.read(NIOServer.CLIENT_BYTE_BUFFER);
		//以下转换字符集过程也可以自己使用转换器：CharsetDecoder来完成
		byte[]bytes = new byte[NIOServer.CLIENT_BYTE_BUFFER.position()];
		NIOServer.CLIENT_BYTE_BUFFER.flip();//将指针重新偏移，才能得到准确的数据
		NIOServer.CLIENT_BYTE_BUFFER.get(bytes);//读取到Byte中
		NIOServer.CLIENT_BYTE_BUFFER.clear();
		logInfo("客户端消息 >>" + new String(bytes , DEFAULT_MESSAGE_CHARSET));//转换输出字符集

		SelectionKey writeSelectionKey = channel.register(selector , SelectionKey.OP_WRITE);//注册一个写的选择器
		writeSelectionKey.attach(new DownloadFileProcessor());//这里可以绑定一个对象到selectionKey当中，触发相应事件的时候，可以将其取出
	}

	private void processWrite() throws IOException {
		//System.out.println("处理写操作");
		SocketChannel channel = (SocketChannel)selectionKey.channel();
		DownloadFileProcessor downloadFileProcessor = (DownloadFileProcessor)selectionKey.attachment();//获取注册WRITE事件的对象
		int count = downloadFileProcessor.read();
		if(count <= 0) {
			closeStreams(downloadFileProcessor , channel);
			logInfo("下载结束.....");
		}else {
			channel.write(downloadFileProcessor.getFileByteBuffer());
		}
	}

}
