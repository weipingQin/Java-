package chapter05.tools;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 如果要运行这个例子，请将JDK换到1.7以上的版本
 * @author zhongyin.xy
 *
 */
public class ForkJoinTest {
	
	private final static int SIZE = 10000000;
	
	private final static int FORK_NUM_PER = 5;
	
	private final static int PER_SIZE = 1000;
	
	private final static int [] TEST_INT_ARRAY = new int[SIZE];
	
	static {
		Random random = new Random();
		for(int i = 0 ; i < SIZE ; i++) {
			TEST_INT_ARRAY[i] = random.nextInt();// = i
		}
	}

	static class MaxComputer extends RecursiveTask<Integer> {
		
		private static final long serialVersionUID = 1L;
		
		private int startIndex;
		
		private int endIndex;
		
		public MaxComputer(int startIndex , int endIndex) {
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public Integer compute() {
			int count = (endIndex - startIndex);
			if((endIndex - startIndex) < PER_SIZE) {
				int v = TEST_INT_ARRAY[startIndex];
				for(int i = startIndex + 1 ; i < endIndex ; i++) {
					/*
					//这里可以通过休眠来代表一个动作需要花费一点时间，否则看不出太多效果
					try {
						Thread.sleep(DEFAULT_RANDOM.nextInt() & 5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}*/
					v = Math.max(v, TEST_INT_ARRAY[i]);
				}
				return v;
			}
			//每次分解为最多5个子任务
			MaxComputer []maxComputer = new MaxComputer[5];
			int perConnt = count / FORK_NUM_PER;//每个任务的数量
			int min = startIndex , max = startIndex;
			for(int i = 0 ; i < FORK_NUM_PER ; i++) {
				max += perConnt;
				max = Math.min(max, endIndex);
				maxComputer[i] = new MaxComputer(min , max);
				maxComputer[i].fork();
				min = max;
			}
			int result = Integer.MIN_VALUE;
			for(int i = 0 ; i < FORK_NUM_PER ; i++) {
				result = Math.max(result, maxComputer[i].join());
			}
			return result;
		}
	}
	
	public static void main(String []args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		MaxComputer maxComputer = new MaxComputer(0 , SIZE);
		pool.submit(maxComputer);
		System.out.println(maxComputer.get());
	}
}
