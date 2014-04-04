import meggy.Meggy;

class callsUndefinedMethod {
    public static void main(String[] whatever){

     new A().foo1();
     new A().foo2();
    
}
}

class A {
    public void foo() {
    }
}

