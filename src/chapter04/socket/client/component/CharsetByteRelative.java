package chapter04.socket.client.component;

public class CharsetByteRelative {

	private String charset;
	
	private byte charsetByte;

	public CharsetByteRelative(String charset , byte charsetByte) {
		this.charset = charset;
		this.charsetByte = charsetByte;
	}
	
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public byte getCharsetByte() {
		return charsetByte;
	}

	public void setCharsetByte(byte charsetByte) {
		this.charsetByte = charsetByte;
	}
	
	public boolean isCharset(String charset) {
		if(charset == null) return false;
		charset = charset.toLowerCase().replace("-", "").trim();
		return charset.equals(this.charset);
	}
	
	public boolean isCharsetCode(byte charsetByte) {
		return this.charsetByte == charsetByte;
	}
}