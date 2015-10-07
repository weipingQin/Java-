package chapter05.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 结果说明：如果多个线程同时对同一个变量进行叠加操作，每个线程加1，最终结果为初始值+线程个数为成功
 * @author zhongyin.xy
 *
 */
public class AtomicIntegerTest {

	/**
	 * 常见的方法列表
	 * @see AtomicInteger#get()             直接返回值
	 * @see AtomicInteger#getAndAdd(int)    增加指定的数据，返回变化前的数据
	 * @see AtomicInteger#getAndDecrement() 减少1，返回减少前的数据
	 * @see AtomicInteger#getAndIncrement() 增加1，返回增加前的数据
	 * @see AtomicInteger#getAndSet(int)    设置指定的数据，返回设置前的数据
	 * 
	 * @see AtomicInteger#addAndGet(int)    增加指定的数据后返回增加后的数据
	 * @see AtomicInteger#decrementAndGet() 减少1，返回减少后的值
	 * @see AtomicInteger#incrementAndGet() 增加1，返回增加后的值
	 * @see AtomicInteger#lazySet(int)      仅仅当get时才会set
	 * 
	 * @see AtomicInteger#compareAndSet(int, int) 尝试新增后对比，若增加成功则返回true否则返回false
	 */
	public final static AtomicInteger TEST_INTEGER = new AtomicInteger(1);
	
	private static int index = 1;
	
	public static void main(String []args) throws InterruptedException {
		final CountDownLatch start_count_down = new CountDownLatch(1);
		final Thread []threads = new Thread[10];
		 for(int i = 0 ; i < 10 ; i++) {
			 threads[i] = new Thread() {
				 public void run() {
					try {
						start_count_down.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for(int j = 0 ; j < 100 ; j++) {
						index++;
						TEST_INTEGER.incrementAndGet();
					}
				 }
			 };
			 threads[i].start();
		 }
		 Thread.sleep(500);
		 start_count_down.countDown();
		 for(Thread t : threads) {
			 t.join();
		 }
		 System.out.println("最终运行结果：" + TEST_INTEGER.get());
		 System.out.println("最终运行结果：" + index);
	}
}
