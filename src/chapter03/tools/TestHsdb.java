package chapter03.tools;

import java.io.IOException;

class Node {
	String a = "aaaaa";
	String b = "bbbbb";
}

public class TestHsdb {

	public Node node1;
	
	public Node node2;

	public static void main(String[] args) throws IOException {
		TestHsdb testHsdb = new TestHsdb();
		testHsdb.node1 = new Node();
		testHsdb.node2 = new Node();
		System.in.read();
	}
}
