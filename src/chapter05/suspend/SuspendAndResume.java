package chapter05.suspend;

/**
 * 注意了，这是一个反面教材，大家可以看到线程正常结束，但是锁没有释放掉
 * @author xieyuooo
 *
 */
public class SuspendAndResume {

	private final static Object object =  new Object();

	static class ThreadA extends Thread {
		
		public void run() {
			synchronized(object) {
				System.out.println("start...");
				Thread.currentThread().suspend();
				System.out.println("thread end...");
			}
		}
	}
	
	public static void main(String []args) throws InterruptedException {
		ThreadA t1 = new ThreadA();
		ThreadA t2 = new ThreadA();
		t1.start();
		t2.start();
		Thread.sleep(1000);
		System.out.println(t1.getState());
		System.out.println(t2.getState());
		t1.resume();
		t2.resume();
	}
}
