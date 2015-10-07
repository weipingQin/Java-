package chapter05.base;

public class ThreadDaemonTest {

	static class A extends Thread {
		
		public void run() {
			while(true) {
				Thread.yield();
			}
		}
	}
	
	static class B extends Thread {
		
		A a = new A();
		
		public void run() {
			//a.setDaemon(true);
			a.start();
			System.out.println("B is end....");
		}
	}
	
	public static void main(String []args) throws InterruptedException {
		B b = new B();
		b.start();
		Thread.sleep(1000);
		System.out.println(b.a.getState());//输出RUNNING，说明还未结束
	}
} 
