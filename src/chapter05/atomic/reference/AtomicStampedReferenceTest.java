package chapter05.atomic.reference;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决问题：
 * 解决ABA问题，通过一个引用和版本号为基础解决ABA问题，
 * 当一个对象从A变成B，再从B变成A，此时若并发中，某个线程发现对象还是A，那么此时就会对比成功
 * 为了解决这个问题，AtomicStamped使用了版本号来解决这个问题
 * ABA问题的模拟你参看模拟代码：
 * @author zhongyin.xy
 *
 */
public class AtomicStampedReferenceTest {
	
	/**
	 * 常见的方法：
	 * @see AtomicStampedReference#compareAndSet(Object, Object, int, int) 对比修改
	 * @see AtomicStampedReference#attemptStamp(Object, int) 用于绝对判定是否一致，返回true|false
	 * @see AtomicStampedReference#getReference() 返回引用
	 * @see AtomicStampedReference#getStamp() 返回版本
	 * @see AtomicStampedReference#get(int[]) 传入一个数组，返回数组的0号元素为版本号
	 */
	public final static AtomicStampedReference <String>ATOMIC_REFERENCE = new AtomicStampedReference<String>("abc" , 0);
	
	private final static Random RANDOM_OBJECT = new Random();

	public static void main(String []args) throws InterruptedException {
		final CountDownLatch startCountDownLatch = new CountDownLatch(1);
		Thread []threads = new Thread[20];
		for(int i = 0 ; i < 20 ; i++) {
			final int num = i;
			threads[i] = new Thread() {
				public void run() {
					String oldValue = ATOMIC_REFERENCE.getReference();
					int stamp = ATOMIC_REFERENCE.getStamp();
					try {
						startCountDownLatch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(RANDOM_OBJECT.nextInt() & 500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(ATOMIC_REFERENCE.compareAndSet(oldValue , oldValue + num , stamp , stamp + 1)) {
						System.out.println("我是线程：" + num + ",我获得了锁进行了对象修改！");
					}
				}
			};
			threads[i].start();
		}
		Thread.sleep(200);
		startCountDownLatch.countDown();
		new Thread() {
			public void run() {
				try {
					Thread.sleep(RANDOM_OBJECT.nextInt() & 200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int stamp = ATOMIC_REFERENCE.getStamp();
				while(!ATOMIC_REFERENCE.compareAndSet(ATOMIC_REFERENCE.getReference(), "abc" , stamp , stamp + 1)) {
					stamp = ATOMIC_REFERENCE.getStamp();
				}
				System.out.println("已经改为原始值！");
			}
		}.start();
	}
}
