package chapter05.base;

/**
 * 获取线程栈信息
 * @author xieyuooo
 *
 */
public class ThreadStackTest {

	public static void main(String []args) {
		printStack(getStackByThread());
		printStack(getStackByException());
	}
	
	private static void printStack(StackTraceElement []stacks) {
		for(StackTraceElement stack : stacks) {
			System.out.println(stack);
		}
		System.out.println("\n");
	}
	
	private static StackTraceElement[] getStackByThread() {
		return Thread.currentThread().getStackTrace();
	}
	
	private static StackTraceElement[] getStackByException() {
		return new Exception().getStackTrace();
	}
}
