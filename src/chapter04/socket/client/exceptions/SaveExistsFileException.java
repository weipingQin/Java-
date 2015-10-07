package chapter04.socket.client.exceptions;

public class SaveExistsFileException extends RuntimeException {

	private static final long serialVersionUID = -1026575092082314002L;
	
	public SaveExistsFileException(String path) {
		super("保存文件:" + path + "失败，因为文件已经存在了。");
	}
	
}
