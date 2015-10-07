package chapter03.jmx.beans;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class OperatingSystemMXBeanTest {

	public static void main(String []args) {
		OperatingSystemMXBean mxBean = ManagementFactory.getOperatingSystemMXBean();
		System.out.println(mxBean.getArch());
		System.out.println(mxBean.getAvailableProcessors());
		System.out.println(mxBean.getName());
		System.out.println(mxBean.getSystemLoadAverage());
		System.out.println(mxBean.getVersion());
	}
}
