import meggy.Meggy;

class instanceVars {
    public static void main(String[] whatever){
        new A().foo();
    }
}

class A {
    int i1;
    byte b1;
    int i2;
    byte b2;

    public void foo() {
        i1 = 1;
        b1 = (byte)2;
        i2 = 3;
        b2 = (byte)4;

        Meggy.delay(i1 + b1);
        Meggy.delay(i2 + b2);
    }
}

