package chapter03.oom;

import java.util.ArrayList;
import java.util.List;

public class StringInternPermGenOOM {

	/**
	 * 请在命令行下运行
	 * 运行方式：java -XX:PermSize10m -XX:MaxPermSize=10m chapter3.fullgc.StringInternPermGenFullGC
	 * 
	 * @param args
	 */
	public static void main(String []args) {
		int i = 0;
		List<String>list = new ArrayList<String>();
		while(true) {
			list.add(("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" + i++).intern());
		}
	}
}
