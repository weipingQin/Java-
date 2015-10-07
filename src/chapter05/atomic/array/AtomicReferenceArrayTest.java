package chapter05.atomic.array;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicReferenceArrayTest {

	/**
	 * 常见方法列表：
	 * @see AtomicReferenceArray#compareAndSet(int, Object, Object) 参数1：数组下标；参数2：修改原始值对比；参数3：修改目标值 修改成功返回true，否则返回false
	 * 
	 * @see AtomicReferenceArray#getAndSet(int, Object) 参数1：数组下标，参数2：修改的目标，修改成功为止，返回修改前的数据
	 * 
	 */
	public final static AtomicReferenceArray<String> ATOMIC_REFERENCE_ARRAY = new AtomicReferenceArray<String>(
			new String [] {"a" , "b" , "c"}
	);
}
