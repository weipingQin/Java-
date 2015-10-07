package chapter05.security;

public class NoVisiabilityTest {
	
	private static class ReadThread extends Thread {
		
		private boolean ready;
		
		private int number;
		
		public void run() {
			while(!ready) {
				number++;	
				Thread.yield();
			}
			System.out.println(ready);
		}
		
		public void readyOn() {
			this.ready = true;
		}
	}
	
	public static void main(String []args) throws InterruptedException {
		ReadThread readThread = new ReadThread();
		readThread.start();
		Thread.sleep(200);
		readThread.readyOn();
		System.out.println(readThread.ready);
	}
}
