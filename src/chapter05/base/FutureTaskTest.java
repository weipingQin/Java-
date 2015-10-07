package chapter05.base;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTaskTest {
	
	static class CallImpl implements Callable<String> {
		
		private String input;
		
		public CallImpl(String input) {
			this.input = input;
		}
		public String call() {
			try {//延迟一点点
				Random random = new Random();
				Thread.sleep((random.nextInt() + this.input.hashCode()) & 10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "输入参数:" + input;
		}
	}

	public static void main(String []args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		@SuppressWarnings("unchecked")
		List<Future<String>> list = Arrays.asList(
			executorService.submit(new CallImpl("t1")) , 
			executorService.submit(new CallImpl("t2")) ,
			executorService.submit(new CallImpl("t3"))
		);
		/*List<Future<String>> list = executorService.invokeAll(Arrays.asList(
			new CallImpl("t1"),
			new CallImpl("t2"),
			new CallImpl("t3")
		));*/
		for(Future<String> future : list) {
			String result = future.get();//如果没返回，会阻塞
			System.out.println(result + "\t" + System.currentTimeMillis());
		}
		executorService.shutdown();
	}
}
