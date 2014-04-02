import meggy.Meggy;

class methodsWithParams {
    public static void main(String[] whatever){
        new A().foo(1, 2);
        
    }
}

class A {
    public void foo(int x, int y) {
        Meggy.setPixel((byte)x, (byte)y, Meggy.Color.BLUE);
    }
}



