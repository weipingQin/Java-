package chapter03.oom;

public class StackOverFlowTest {

	public static void main(String []args) {
		new StackOverFlowTest().testStackOver();
	}
	
	public void testStackOver() {
		testStackOver();
	}
}
