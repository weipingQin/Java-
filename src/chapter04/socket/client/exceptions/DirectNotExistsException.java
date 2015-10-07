package chapter04.socket.client.exceptions;

public class DirectNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DirectNotExistsException(String directPath) {
		super("在本地没有找到目录：" + directPath);
	}

}
