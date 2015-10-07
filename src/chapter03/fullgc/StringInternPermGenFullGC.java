package chapter03.fullgc;



public class StringInternPermGenFullGC {

	/**
	 * 请在命令行下运行，注意是在JDK 1.6下运行，在JDK 1.7中运行的结果将完全不同
	 * 运行方式为java -XX:+PrintGCDetials -XX:PermSize10m -XX:MaxPermSize=10m chapter3.fullgc.StringInternPermGenFullGC
	 * @param args
	 */
	public static void main(String []args) {
		int i = 0;
		while(true) {
			("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" + i++).intern();
		}

	}
}
