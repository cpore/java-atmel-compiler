import meggy.Meggy;

class duplicateLocalVar {
    public static void main(String[] whatever){
        Meggy.delay(1);
    }
}

class A {
    public void foo() {
        int a;
        int a;
    }
}

