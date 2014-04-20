import meggy.Meggy;

class typeCheckLocalVar {
    public static void main(String[] whatever){
        Meggy.delay(1);
    }
}

class A {
    public void foo() {
        int i;
        byte b;

        b = i;
    }
}

