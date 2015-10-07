package chapter02.samplesocket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

	public static void main(String []args) throws IOException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		SocketWrapper socket = new SocketWrapper(new Socket("localhost" , 8888));
		try {
			System.out.println("已经连接上服务器端，现在可以输入数据开始通信了");
			String sendMsg = scanner.nextLine();
			socket.writeLine(sendMsg);//发送消息
			String recivedMsg = socket.readLine();
			while(!"close".equals(recivedMsg)) {
				System.out.println("===【服务器返回】===>" + recivedMsg);
				sendMsg = scanner.nextLine();
				socket.writeLine(sendMsg);//发送消息
				recivedMsg = socket.readLine();
			}
			//胖哥注释掉这部分代码，如果放开就会报错哦，错误原因自然明了
			//socket.writeLine("消息测试消息测试消息测试消息测试消息测试消息测");
			//socket.writeLine(" 测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试");
			System.out.println("我是客户端，结束了!");
		}finally {
			if(socket != null)
				socket.close();
		}
	}
}
