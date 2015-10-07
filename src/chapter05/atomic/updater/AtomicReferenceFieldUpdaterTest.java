package chapter05.atomic.updater;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaterTest {
	
	static class A {
		volatile String stringValue = "abc";
	}

	/**
	 * 使用方式如AtomicReference一致，使用范围和AtomicIntegerFieldUpdater一致
	 * 
	 */
	AtomicReferenceFieldUpdater <A , String>ATOMIC_REFERENCE_FIELD_UPDATER = AtomicReferenceFieldUpdater.newUpdater(A.class, String.class, "stringValue");
}
