import meggy.Meggy;

class duplicateInstanceVar {
    public static void main(String[] whatever){
        Meggy.delay(1);
    }
}

class A {
    int a;
    int a;

    public void foo() {
        Meggy.delay(1);
    }
}

