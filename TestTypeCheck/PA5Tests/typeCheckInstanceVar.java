import meggy.Meggy;

class typeCheckInstanceVar {
    public static void main(String[] whatever){
        Meggy.delay(1);
    }
}

class A {
    int i;
    byte b;

    public void foo() {
        b = i;
    }
}

