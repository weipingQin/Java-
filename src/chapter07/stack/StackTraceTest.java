package chapter07.stack;

import org.apache.commons.lang.exception.ExceptionUtils;

public class StackTraceTest {

	public static StackTraceElement[] findStack1() {
		return Thread.currentThread().getStackTrace();
	}
	
	public static StackTraceElement[] findStack2() {
		new Exception().printStackTrace();
		return new Exception().getStackTrace();
	}
	
	public static String getStackTrace() {
		return ExceptionUtils.getFullStackTrace(new Exception());
	}
	
	public StackTraceElement[] findNoStaticStack1() {
		return Thread.currentThread().getStackTrace();
	}
	
	public static void main(String []args) {
		StackTraceElement[] stacks = findStack1();
		System.out.println("==========================================");
		for(StackTraceElement stack : stacks) {
			System.out.println(stack);
		}
		
		System.out.println("==========================================");
		stacks = findStack2();
		for(StackTraceElement stack : stacks) {
			System.out.println(stack);
		}
		System.out.println("==========================================");
		System.out.println(getStackTrace());
		
		stacks = new StackTraceTest().findNoStaticStack1();
		for(StackTraceElement stack : stacks) {
			System.out.println(stack);
		}
	}
}
