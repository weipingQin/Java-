package chapter03.jmx.beans;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

public class CompilationMXBeanTest {

	public static void main(String []args) {
		CompilationMXBean mxBean = ManagementFactory.getCompilationMXBean();
		System.out.println(mxBean.getTotalCompilationTime());
		System.out.println(mxBean.getName());
		System.out.println(mxBean.isCompilationTimeMonitoringSupported());
	}
}
