import meggy.Meggy;

class callsToThis {
    public static void main(String[] whatever){
        new A().foo();
    }
}

class A {
    public void foo() {
        this.bar();
        this.baz();

        Meggy.delay(1);
    }

    public void bar() {
        Meggy.delay(2);
    }

    public void baz() {
        this.boom();
        Meggy.delay(3);
    }

    public void boom() {
        Meggy.delay(4);
    }
}
