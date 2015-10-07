package chapter05.atomic.array;

import java.util.concurrent.atomic.AtomicLongArray;

public class AtomicLongArrayTest {

	/**
	 * 方法基本和AtomicIntegerArray类似，请参看AtomicIntegerArrayTest测试类的说明以及使用范例
	 */
	public final static AtomicLongArray ATOMIC_LONG_ARRAY = new AtomicLongArray(new long[] {1l,2l,3l});
}
