package chapter05.atomic.reference;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceTest {

	/**
	 * 使用方式基本和AtomicStampedReference一致
	 * 区别在于他更加简单描述是与否的关系，而它本身只有两种状态，所以在解决ABA问题的时候，要求运行的目标通常只有两个状态
	 * 因为ABA本身要求是单向的（人为确定），所以一旦从一种状态变成另一种状态后，就不能再变化回来，只是该类要求只有两个状态
	 * 一般类似：已删除、已失效等不可复用的内容用它比AtomicStampedReference更加具有可读性，因为AtomicStampedReference仅仅是一个类修改的版本号和计数器
	 */
	public final static AtomicMarkableReference <String>ATOMIC_MARKABLE_REFERENCE = new AtomicMarkableReference<String>("abc" , false);
	
	{
		ATOMIC_MARKABLE_REFERENCE.compareAndSet("abc", "abc2", false, true);
	}
}
