package chapter03.load;

import java.util.ArrayList;
import java.util.List;

public class LoadClassErrorDataDemo {
	
	private final static LoadClassErrorDataDemo INIT_OBJECT = new LoadClassErrorDataDemo();
   	
	//直接给LIST赋值试一试看，结果是什么？
	//private static List<String> LIST = new ArrayList<String>();
	private static List<String> LIST;
	
   	static {
   		LIST = new ArrayList<String>();
   		LIST.add("1");
   	}
   	
   	private LoadClassErrorDataDemo() {
   	  	if(LIST == null) {
   	  		LIST = new ArrayList<String>();	
   	  	}
   	  	LIST.add("2");
   	  	LIST.add("3");
   	}
   	
   	public void displayCacheSize() {
			for(String str : LIST) {
			   System.out.println(str);	
			}
		}
   	
   	public static LoadClassErrorDataDemo getInstance() {
   	   return INIT_OBJECT;
   	}

   	/**
   	 * 理论上我们希望的ArrayList的赋值是来自于构造方法中，因此应该包含2、3两个值
   	 * 但是实际结果确实1
   	 * @param args
   	 */
    public static void main(String []args) {
    	LoadClassErrorDataDemo forInit = LoadClassErrorDataDemo.getInstance();
       	forInit.displayCacheSize();
    }
}
