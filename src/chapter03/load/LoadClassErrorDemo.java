package chapter03.load;

abstract class A {
	
	public A() {
		list();
	}
	
	public void list() {
		test();
	}
	
	abstract void test();
}

class B extends A {
	
	private final static B instance = new B();
	
	public static B getInstance() {return instance;}
	
	public void test() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		instance.test2();
	}
	
	public void test2() {}
}

public class LoadClassErrorDemo {

	public static void main(String []args) {
		new Thread("t1") {
			public void run() {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				B.getInstance().test2();
			}
		}.start();
		
		new Thread("t2") {
			public void run() {
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("start2....");
				B.getInstance().test2();
			}
		}.start();
	}
}
