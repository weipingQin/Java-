package chapter05.base;

public class ThreadStop {

	private final static Object object = new Object();
	
	static class MyThread extends Thread {
		
		private int i = 0;
		
		public void run() {
			try {
				synchronized(object) {
					while(true) {
						i++;
					}
				}
			}catch(Throwable e) {
				System.out.println(Thread.currentThread().getStackTrace()[0]);
				System.out.println(Thread.currentThread().getStackTrace()[1]);
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String []args) throws InterruptedException {
		MyThread myThread1 = new MyThread();
		MyThread myThread2 = new MyThread();
		myThread1.start();
		Thread.sleep(100);
		myThread1.stop();
		Thread.sleep(1000);
		myThread2.start();
		Thread.sleep(100);
		System.out.println(myThread1.getState());
		System.out.println(myThread2.getState());
	}
}
