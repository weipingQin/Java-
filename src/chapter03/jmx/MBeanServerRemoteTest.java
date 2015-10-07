package chapter03.jmx;

import java.io.IOException;
import java.util.HashMap;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class MBeanServerRemoteTest {

	/**
	 * 远程地址需要开启：
	 * -Dcom.sun.management.jmxremote 
	 * -Dcom.sun.management.jmxremote.port=9000 
	 * -Dcom.sun.management.jmxremote.authenticate=true
	 */
	final static String RMI_URL = "service:jmx:rmi:///jndi/rmi://10.232.31.53:9000/jmxrmi";

	public static void main(String []args) 
		throws IOException, MalformedObjectNameException, IntrospectionException, 
			InstanceNotFoundException, AttributeNotFoundException, ReflectionException, MBeanException {
		JMXServiceURL serviceURL = new JMXServiceURL(RMI_URL);
		JMXConnector jmxc = JMXConnectorFactory.connect(serviceURL , new HashMap<String , Object>() {
			private static final long serialVersionUID = 1L;
			{
				put(JMXConnector.CREDENTIALS , new String[] {"controlRole" , "FUCK"});
			}
		});
		MBeanServerConnection mBeanServer = jmxc.getMBeanServerConnection();
		System.out.println("MBean count = " + mBeanServer.getMBeanCount());
		String[] domains = mBeanServer.getDomains();
		for (String doMain : domains) {
			System.out.println("============>" + doMain);
		}
		System.out.println(JMXUtils.getYoungGC(mBeanServer));
		System.out.println(JMXUtils.getFullGC(mBeanServer));
		System.out.println(mBeanServer.getAttribute(new ObjectName("JMImplementation:type=MBeanServerDelegate"), "ImplementationVersion"));
		System.out.println(mBeanServer.getAttribute(new ObjectName("java.lang:type=Runtime"), "BootClassPath"));
		//其余的可以自己遍历出来
	}
}
