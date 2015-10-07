package chapter05.atomic.updater;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class AtomicLongFieldUpdaterTest {
	
	static class A {
		volatile long longValue = 100l;
	}

	/**
	 * 操作方法和AtomicIntegerFieldUpdater一致
	 */
	AtomicLongFieldUpdater <A>ATOMIC_LONG_FIELD_UPDATER = AtomicLongFieldUpdater.newUpdater(A.class , "longValue");
}
