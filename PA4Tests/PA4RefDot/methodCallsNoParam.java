import meggy.Meggy;

class methodCallsNoParam {
    public static void main(String[] whatever){
        new A().foo1();
        new A().foo2();
        new B().bar();
    }
}

class A {
    public void foo1() { }
    public void foo2() { }
}

class B {
    public void bar() { }
}
