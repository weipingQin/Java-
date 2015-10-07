package chapter04.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

import static chapter04.socket.Commons.*;

public class NIOServer {

	public final static int BYTE_BUFFER_SIZE = 8192;
	
	public final static ByteBuffer CLIENT_BYTE_BUFFER = ByteBuffer.allocate(BYTE_BUFFER_SIZE);
	
	public final static int DEFAULT_PORT = 8888;
	
	public static void main(String []args) throws IOException {
		Selector selector = createSelector();
		logInfo("服务器端已经已经打开端口：" + DEFAULT_PORT);
		try {
			while (true) {
				selector.select();
				Set<SelectionKey> selectionKeySet = selector.selectedKeys();
				for (SelectionKey selectionKey : selectionKeySet) {
					SelectionKeyProcessor selectionKeyProcessor = new SelectionKeyProcessor(
								selectionKey, selector);
					selectionKeyProcessor.processKey();
				}
				selectionKeySet.clear();
			}
		}finally {
			selector.close();
		}
	}
	
	private static Selector createSelector() throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();  //打开ServerSocketChannel
		Selector selector = Selector.open();                      //创建一个选择器
		server.socket().bind(new InetSocketAddress(DEFAULT_PORT)); //Channel中绑定一个端口
		server.configureBlocking(false);                           //绑定为非阻塞模式
		server.register(selector, SelectionKey.OP_ACCEPT);         //选择器注册在Channel上，  注册接收事件(因为服务器端首先是接收请求)
		return selector;
	}
}
