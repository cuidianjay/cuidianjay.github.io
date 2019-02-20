package org.service.hi;

public class B extends A{

	public void printZ() {
		System.out.println("b.z");
	}
	
	public void printB() {
		System.out.println("b.b");
	}
	
	public static void main(String args[]) {
		A a = new B();
		a.printA();
		a.printB();
	}
}
