package chapter03.inst.sizeof;

import java.lang.instrument.Instrumentation;

public class NormalObjectSizeOf {

	private static Instrumentation inst;

	public static void premain(String agentArgs, Instrumentation instP) {
		inst = instP;
	}
	
	public static long sizeOf(Object object) {
		return inst.getObjectSize(object);
	}
}
