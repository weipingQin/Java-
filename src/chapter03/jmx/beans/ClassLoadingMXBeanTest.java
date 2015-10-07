package chapter03.jmx.beans;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

public class ClassLoadingMXBeanTest {

	public static void main(String []args) {
		ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
		System.out.println(classLoadingMXBean.getLoadedClassCount());
		System.out.println(classLoadingMXBean.getTotalLoadedClassCount());
		System.out.println(classLoadingMXBean.getUnloadedClassCount());
		System.out.println(classLoadingMXBean.isVerbose());
	}
}
