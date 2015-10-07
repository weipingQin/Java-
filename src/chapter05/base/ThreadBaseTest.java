package chapter05.base;

public class ThreadBaseTest {

	public static void main(String []args) {
		System.out.println("././...");
		new Thread() {
			public void run() {
				System.out.println(Thread.currentThread().getName());
				System.out.println("我是被创建的线程，我执行了...");
			}
		}.start();
		System.out.println(Thread.currentThread().getName());
		System.out.println("main process end...");
	}
}
