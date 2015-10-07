package chapter05.atomic.reference;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 首先说明为什么要有引用的Atomic：
 * 		因为引用在赋值的瞬间也会存在读写的并发，它在内存里面类似于int和long等类型数据的变化
 *      一般来说对象作为全局信息的情况较少，但是也难免有并发的情况存在，当然有些集合类本身解决了这些问题，但是如果是单个对象的替换，就会有这些问题了
 *      例如：内存中多个缓冲区做来回切换写操作，切换的瞬间以及其他线程获取读的瞬间需要得到最准确的信息，使得切换时是有效的。
 * 
 * 例子结果说明：100个线程随机访问这个代码，只有一个线程能获取到锁对对象进行修改
 * 若将abc2的目标结果改为abc，那么每个线程都会得到执行，因为都能对不成功，abc将会在常量池中唯一地址
 * 而将目标改为new String("abc")也会只有一个修改成功，说明比较的是引用而不是内容
 * @author zhongyin.xy
 *
 */
public class AtomicReferenceTest {

	/**
	 * 相关方法列表
	 * @see AtomicReference#compareAndSet(Object, Object) 对比设置值，参数1：原始值，参数2：修改目标引用
	 * @see AtomicReference#getAndSet(Object) 将引用的目标修改为设置的参数，直到修改成功为止，返回修改前的引用
	 */
	public final static AtomicReference <String>ATOMIC_REFERENCE = new AtomicReference<String>("abc");
	
	private final static Random RANDOM_OBJECT = new Random();
	
	public static void main(String []args) throws InterruptedException {
		final CountDownLatch startCountDownLatch = new CountDownLatch(1);
		Thread []threads = new Thread[20];
		for(int i = 0 ; i < 20 ; i++) {
			final int num = i;
			threads[i] = new Thread() {
				public void run() {
					String oldValue = ATOMIC_REFERENCE.get();
					try {
						startCountDownLatch.await();
						Thread.sleep(RANDOM_OBJECT.nextInt() & 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(ATOMIC_REFERENCE.compareAndSet(oldValue , oldValue + num)) {
						System.out.println("我是线程：" + num + ",我获得了锁进行了对象修改！");
					}
				}
			};
			threads[i].start();
		}
		Thread.sleep(200);
		startCountDownLatch.countDown();
		for(Thread thread : threads) {
			thread.join();
		}
		System.out.println("最终结果为：" + ATOMIC_REFERENCE.get());
	}
}
