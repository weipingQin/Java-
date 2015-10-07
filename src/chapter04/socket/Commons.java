package chapter04.socket;

import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.reflect.Reflection;
import chapter04.socket.client.component.CharsetByteRelative;
import chapter04.socket.client.sender.BFileSender;
import chapter04.socket.client.sender.DefaultSender;
import chapter04.socket.client.sender.FileSender;
import chapter04.socket.client.sender.GetFileSender;
import chapter04.socket.client.sender.MessageSender;
import chapter04.socket.client.sender.Sendable;

public class Commons {
	
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private final static SimpleDateFormat DATE_FORMAT_OBJECT = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
	
	public final static String HELP_STR = "help";
	
	public final static String EXIT_STR = "exit";

	public final static int SEND_MESSAGE = 1;
	
	public final static String SEND_MESSAGE_STR = "sendMsg";
	
	public final static byte SEND_FILE = 2;
	
	public final static String SEND_FILE_STR = "sendFile";
	
	public final static byte SEND_B_FILE = 3;
	
	public final static String SEND_B_FILE_STR = "sendBFile";
	
	public final static byte GET_FILE = 4;
	
	public final static String GET_FILE_STR = "getFile";
	
	public final static String DEFAULT_MESSAGE_CHARSET = "utf-8";
	
	public final static String CHARSET_START = "charset=";
	
	public final static String SERVER_SAVE_BASE_PATH = "/javaA/upload/";
	
	public final static int DEFAULT_BUFFER_LENGTH = 8 * 1024;
	
	public final static String HELP_SHOW = "\n\n\t\t操作方式：\n" +
	"\t\t1、传送普通的字符串，例如：\"sendMsg 你好啊！\" 即可。\n" +
	"\t\t2、传送文本文件，例如：\"sendFile /home/xieyuooo/a.sql charset=utf-8\"\n" +
	"\t\t3、传送非文本文件，例如：\"sendBFile /home/xieyuooo/aaa.jpg\"\n" +
	"\t\t4、下载文件，例如：\"getFile aaa.jpg d:/download/\" 其中d:/download/为下载到本地的目录，若未指定，则下载到user.dir下面\n" +
	"\t\t5、help 输出相关的使用帮助\n" +
	"\t\t6、exit 退出客户端";
	
	public final static String ERROR_MESSAGE_FORMAT = "错误的消息格式，请参看 demo：\n" + HELP_SHOW;
	
	private final static List<CharsetByteRelative> CHASET_BYTE_LIST = Arrays.asList(
			new CharsetByteRelative("utf8" , (byte)1) , 
			new CharsetByteRelative("gbk" , (byte)2) ,
			new CharsetByteRelative("utf16" , (byte)3)
	);
	
	private final static Map<String , Class<? extends Sendable>> ORDER_CLASS_MAP 
						    = new HashMap<String , Class<? extends Sendable>>() {
		private static final long serialVersionUID = 3431099761909680054L;
		{
			put(SEND_MESSAGE_STR.toLowerCase() , MessageSender.class);
			put(SEND_FILE_STR.toLowerCase() , FileSender.class);
			put(SEND_B_FILE_STR.toLowerCase() , BFileSender.class);
			put(GET_FILE_STR.toLowerCase() , GetFileSender.class);
		}
	};
	
	public static void print(String str) {
		System.out.print(str);
	}
	
	public static void println(String str) {
		System.out.println(str);
	}
	
	public static void logInfo(String message) {
		Class <?>clazz = Reflection.getCallerClass();
		String date = DATE_FORMAT_OBJECT.format(Calendar.getInstance().getTime());
		println(date + " [] INFO " + clazz.getName() + " - " + message);
	}
	
	public static void closeStream(Closeable closeable) {
		try {
			if(closeable != null) {
				closeable.close();
			}
		}catch(IOException e) {
			/*可以自己增加其他的处理*/
		}
	}
	
	public static void closeStreams(Closeable ...closeables) {
		if(closeables != null) {
			for(Closeable closeable : closeables) {
				closeStream(closeable);
			}
		}
	}
	
	public static byte getCharsetByteByName(String charset) {
		for(CharsetByteRelative charsetByteRelative : CHASET_BYTE_LIST) {
			if(charsetByteRelative.isCharset(charset)) {
				return charsetByteRelative.getCharsetByte();
			}
		}
		throw new RuntimeException("不支持字符集：" + charset);
	}
	
	public static String getCharsetNameByCode(byte charsetCode) {
		for(CharsetByteRelative charsetByteRelative : CHASET_BYTE_LIST) {
			if(charsetByteRelative.isCharsetCode(charsetCode)) {
				return charsetByteRelative.getCharset();
			}
		}
		throw new RuntimeException("不支持字符集编号：" + charsetCode);
	}
	
	public static Class<? extends Sendable> findSendableClassByOrder(String order) {
		Class<? extends Sendable>clazz = ORDER_CLASS_MAP.get(order.toLowerCase());
		return clazz == null ? DefaultSender.class : clazz;
	}
}
