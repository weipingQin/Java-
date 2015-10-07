package chapter02.samplesocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketWrapper {

	private Socket socket;
	
	private InputStream inputStream;
	
	private BufferedReader inputReader;
	
	private BufferedWriter outputWriter;
	
	public SocketWrapper(Socket socket) throws IOException {
		this.socket = socket;
		this.inputStream = socket.getInputStream();
		this.inputReader = new BufferedReader(new InputStreamReader(inputStream , "GBK"));
		this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream() , "GBK"));
	}
	
	public String readLine() throws IOException {
		return inputReader.readLine();
	}
	
	public void writeLine(String line) throws IOException {
		outputWriter.write(line + '\n');
		outputWriter.flush();
	}
	
	public void close() {
		try {
			this.socket.close();
		}catch(Exception e) {
			/*this body code need you write*/
		}
	}
}
