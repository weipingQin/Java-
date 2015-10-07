package chapter03.fullgc;

import java.util.ArrayList;
import java.util.List;

public class GCOverHead {
	/*这里先占用掉Old区超过14M的空间*/
	public final static byte[]DEFAULT_BYTES = new byte[12 * 1024 * 1024];
	
	public static void main(String []args) {	
		List<byte[]>temp = new ArrayList<byte[]>();
		while(true) {
			temp.add(new byte[1024 * 1024]);
			if(temp.size() > 3) {
				temp.clear();
			}
		}
	}
}
