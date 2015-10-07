package chapter05.security;

import java.util.ArrayList;
import java.util.List;

public class FinalConstructorTest {

	static abstract class A {
		
		public A() {
			display();
		}
		
		public abstract void display();
	}
	
	static class B extends A {
		
		private int INT = 100;
		
		private final int FINAL_INT = 100;
		
		private final Integer FINAL_INTEGER = 100;
		
		private String STR1 = "abc";
		
		private final String FINAL_STR1 = "abc";
		
		private final String FINAL_STR2 = new String("abc");
		
		private final List<String> FINAL_LIST = new ArrayList<String>();
		
		public B() {
			super();
			System.out.println("abc");
		}
		
		public void display() {
			System.out.println(INT);
			System.out.println(FINAL_INT);
			System.out.println(FINAL_INTEGER);
			System.out.println(STR1);
			System.out.println(FINAL_STR1);
			System.out.println(FINAL_STR2);
			System.out.println(FINAL_LIST);
		}
	}
	
	public static void main(String []args) {
		new B();
	}
}
