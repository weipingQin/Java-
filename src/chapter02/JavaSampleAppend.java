package chapter02;

public class JavaSampleAppend {

	/**
	 * 此代码只是简单的加法，在命令行中，编译后
	 * 使用 javap -verbose chapter2.JavaSampleAppend
	 * 即可得到编译后的指令集，反编译工具也是这样的道理
	 * @param args
	 */
	public static void main(String []args) {
		int a = 2;//[-1~5]用iconst_m1、iconst_[0-5]、否则用bipush指令
		int b = 2;
		int c = a + b;
	}
	
	/**
	 * 同样的代码对比一下字节码，要多一个本地变量this
	 */
	public void test() {
		int a = 2;
		int b = 2;
		int c = 100;
		int d = 100;
		int e = 100;
		int f = 100;
		int g = 100;
		int m = 100;
		int t = 100;
		int ff = 100;
		int aaa = 100;
		int bb = 100;
		int x = 100;
		int y = 100;
		//int c = a + b;
	}
	
	/**
	 * 同样的代码对比一下字节码
	 */
	public static void testStatic() {
		int a = 2;//[-1~5]用iconst_m1、iconst_[0-5]、否则用bipush指令
		int b = 2;
		int c = a + b;
	}
}
