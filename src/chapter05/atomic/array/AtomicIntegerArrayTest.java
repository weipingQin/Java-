package chapter05.atomic.array;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 计算结果说明：
 * 10个线程并发找到
 * @author zhongyin.xy
 *
 */
public class AtomicIntegerArrayTest {

	/**
	 * 常见的方法列表
	 * @see AtomicIntegerArray#addAndGet(int, int) 执行加法，第一个参数为数组的下标，第二个参数为增加的数量，返回增加后的结果
	 * @see AtomicIntegerArray#compareAndSet(int, int, int) 对比修改，参数1：数组下标，参数2：原始值，参数3，修改目标值，修改成功返回true否则false
	 * @see AtomicIntegerArray#decrementAndGet(int) 参数为数组下标，将数组对应数字减少1，返回减少后的数据
	 * @see AtomicIntegerArray#incrementAndGet(int) 参数为数组下标，将数组对应数字增加1，返回增加后的数据
	 * 
	 * @see AtomicIntegerArray#getAndAdd(int, int) 和addAndGet类似，区别是返回值是变化前的数据
	 * @see AtomicIntegerArray#getAndDecrement(int) 和decrementAndGet类似，区别是返回变化前的数据
	 * @see AtomicIntegerArray#getAndIncrement(int) 和incrementAndGet类似，区别是返回变化前的数据
	 * @see AtomicIntegerArray#getAndSet(int, int) 将对应下标的数字设置为指定值，第二个参数为设置的值，返回是变化前的数据
	 */
	private final static AtomicIntegerArray ATOMIC_INTEGER_ARRAY = new AtomicIntegerArray(10);
	
	public static void main(String []args) throws InterruptedException {
		Thread []threads = new Thread[100];
		for(int i = 0 ; i < 100 ; i++) {
			final int index = i % 10;
			final int threadNum = i;
			threads[i] = new Thread() {
				public void run() {
					int result = ATOMIC_INTEGER_ARRAY.addAndGet(index, index + 1);
					System.out.println("线程编号为：" + threadNum + " , 对应的原始值为：" + (index + 1) + "，增加后的结果为：" + result);
				}
			};
			threads[i].start();
		}
		for(Thread thread : threads) {
			thread.join();
		}
		System.out.println("=========================>\n执行已经完成，结果列表：");
		for(int i = 0 ; i < ATOMIC_INTEGER_ARRAY.length() ; i++) {
			System.out.println(ATOMIC_INTEGER_ARRAY.get(i));
		}
	}
}
