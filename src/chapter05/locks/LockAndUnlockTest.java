package chapter05.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndUnlockTest {
	
	private static int counter = 0;

	private final static Lock PUBLIC_LOCK = new ReentrantLock();
	
	static class ThreadTest extends Thread {
		public void run() {
			try {
				PUBLIC_LOCK.lock();
				//PUBLIC_LOCK.lock();//如果做两次lock，如果只做一次unlock就会有问题哦
				counter++;
			}finally {
				PUBLIC_LOCK.unlock();
			}
		}
	}
	
	public static void main(String []args) throws InterruptedException {
		ThreadTest [] threads = new ThreadTest[100];
		for(int i = 0 ; i < 100 ; i++) {
			threads[i] = new ThreadTest();
			threads[i].start();
		}
		for(Thread thread : threads) {
			thread.join();
		}
		System.out.println(counter);
	}
}
