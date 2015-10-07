package chapter05.base;

public class AssemTest {
	int a, b;
	volatile int c, d;
	
	

	public static void main(String[] args) throws Exception {
		new AssemTest().test();
	}

	public void test() {
		a = 1;
		b = 2;
		c = a;
		d = b;
		b = 3;
		c = 2;
	}
}
