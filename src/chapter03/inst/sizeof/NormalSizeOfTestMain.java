package chapter03.inst.sizeof;

public class NormalSizeOfTestMain {

	public static void main(String []args) {
		System.out.println(NormalObjectSizeOf.sizeOf(new Integer(1)));
		System.out.println(NormalObjectSizeOf.sizeOf(new String()));
		System.out.println(NormalObjectSizeOf.sizeOf(new char[1]));
	}
}
