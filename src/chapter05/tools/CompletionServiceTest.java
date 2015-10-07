package chapter05.tools;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest {

	public static void main(String []args) throws InterruptedException, ExecutionException {
		final Random random = new Random();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		CompletionService<String>completionService = new ExecutorCompletionService<String>(executorService);
		for(int i = 0 ; i < 100 ; i++) {
			final int num = i;
			completionService.submit(new Callable<String>() {
				public String call() {
					try {
						Thread.sleep((random.nextLong()) & 5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return "a" + num;
				}
			});
		}
		for(int i = 0 ; i < 100 ; i++) {
			Future<String> f = completionService.take();
			System.out.println(f.get());
		}
		executorService.shutdown();
	}
}
