package chapter03.jmx.beans;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RuntimeMXBeanTest {

	public static void main(String []args) {
		RuntimeMXBean runTimeMXBean = ManagementFactory.getRuntimeMXBean();
		System.out.println(runTimeMXBean.getBootClassPath());
		System.out.println(runTimeMXBean.getClassPath());
		System.out.println(runTimeMXBean.getLibraryPath());
		System.out.println(runTimeMXBean.getManagementSpecVersion());
		System.out.println(runTimeMXBean.getName());
		System.out.println(runTimeMXBean.getSpecName());
		System.out.println(runTimeMXBean.getSpecVendor());
		System.out.println(runTimeMXBean.getStartTime());
		System.out.println(runTimeMXBean.getUptime());
		System.out.println(runTimeMXBean.getVmName());
		System.out.println(runTimeMXBean.getVmVendor());
		System.out.println(runTimeMXBean.getVmVersion());
		System.out.println(runTimeMXBean.getInputArguments());
		System.out.println(runTimeMXBean.getSystemProperties());
	}
}
