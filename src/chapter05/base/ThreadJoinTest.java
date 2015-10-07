package chapter05.base;

import java.util.Random;

public class ThreadJoinTest {

	static class Computer extends Thread {
		private int start;
		private int end;
		private int result;
		private int []array;
		
		public Computer(int []array , int start , int end) {
			this.array = array;
			this.start = start;
			this.end = end;
		}
		
		public void run() {
			for(int i = start; i < end ; i++) {
				result += array[i];
				if(result < 0) result &= Integer.MAX_VALUE;
			}
		}
		
		public int getResult() {
			return result;
		}
	}
	
	private final static int COUNTER = 100000001;
	
	public static void main(String []args) throws InterruptedException {
		int []array = new int[COUNTER];
		Random random = new Random();
		for(int i = 0 ; i < COUNTER ; i++) {
			array[i] = Math.abs(random.nextInt());
		}
		long start = System.currentTimeMillis();
		Computer c1 = new Computer(array , 0 , COUNTER);
		Computer c2 = new Computer(array , COUNTER / 2 , COUNTER);
		c1.start();
		c2.start();
		c1.join();
		c2.join();
		System.out.println(System.currentTimeMillis() - start);
		//System.out.println(c1.getResult());
		System.out.println((c1.getResult() + c2.getResult()) & Integer.MAX_VALUE);
	}
}
