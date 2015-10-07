package chapter05.atomic.updater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

	static class A {
		volatile int intValue = 100;
	}
	
	/**
	 * 可以直接访问对应的变量，进行修改和处理
	 * 条件：要在可访问的区域内，如果是private或挎包访问default类型以及非父亲类的protected均无法访问到
	 * 其次访问对象不能是static类型的变量（因为在计算属性的偏移量的时候无法计算），也不能是final类型的变量（因为根本无法修改），必须是普通的成员变量
	 * 
	 * 方法（说明上和AtomicInteger几乎一致，唯一的区别是第一个参数需要传入对象的引用）
	 * @see AtomicIntegerFieldUpdater#addAndGet(Object, int)
	 * @see AtomicIntegerFieldUpdater#compareAndSet(Object, int, int)
	 * @see AtomicIntegerFieldUpdater#decrementAndGet(Object)
	 * @see AtomicIntegerFieldUpdater#incrementAndGet(Object)
	 * 
	 * @see AtomicIntegerFieldUpdater#getAndAdd(Object, int)
	 * @see AtomicIntegerFieldUpdater#getAndDecrement(Object)
	 * @see AtomicIntegerFieldUpdater#getAndIncrement(Object)
	 * @see AtomicIntegerFieldUpdater#getAndSet(Object, int)
	 */
	public final static AtomicIntegerFieldUpdater <A>ATOMIC_INTEGER_UPDATER = AtomicIntegerFieldUpdater.newUpdater(A.class, "intValue");
	
	public static void main(String []args) {
		final A a = new A();
		for(int i = 0 ; i < 100 ; i++) {
			final int num = i;
			new Thread() {
				public void run() {
					if(ATOMIC_INTEGER_UPDATER.compareAndSet(a, 100, 120)) {
						System.out.println("我是线程：" + num + " 我对对应的值做了修改！");
					}
				}
			}.start();
		}
	}
}
