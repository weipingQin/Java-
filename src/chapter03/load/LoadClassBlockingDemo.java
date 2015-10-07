package chapter03.load;	

class Node {
	
	private String name;
	
	private String email;
	
	public String toString() {
		return this.name + "\t" + this.email;
	}
	
	public static Node node;
	
	static {
		node = new Node();
		node.name = "ffff";
		node.email = "xxxxx";
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
public class LoadClassBlockingDemo {

	public static void main(String []args) {
		Thread t1 = new Thread() {
			public void run() {
				System.out.println(Node.node);
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				System.out.println(Node.node);
			}
		};
		t1.start();
		t2.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(t2.isAlive() || t1.isAlive()) {
			System.out.println("t1:" + t1.getState());
			System.out.println("t2:" + t2.getState());
		}
		
	}
}
