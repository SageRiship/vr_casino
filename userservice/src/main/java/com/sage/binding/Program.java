package com.sage.binding;

class Test {
	public void foo(String f) {
		System.out.println("Test.Foo");
	}

	public void bar() {
		System.out.println("Test.Bar");
	}
}

public class Program extends Test {
	public void foo(String... f) {
		System.out.println("Program.Foo");
	}

	public void bar() {
		System.out.println("Program.Bar");
	}

	public void xyz() {
		System.out.println("Program.xyz");
	}

	public static void main(String[] args) {
		Test t = new Program();
		Program p = (Program) t;
		t.bar();
		t.foo("foo");
		p.bar();
		p.xyz();

	}
}
