import meggy.Meggy;

class recursive {
    public static void main(String[] whatever){
        new A().countdown(3);
    }
}

class A {
    public void countdown(int n) {
        if(n == 1) {
            Meggy.delay(n);
        } else {
            this.countdown(n-1);
        }
    }
}
