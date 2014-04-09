import meggy.Meggy;

class duplicateMethodName {
   public static void main(String[] whatever){
        new A().foo1();
        
    }
}


class A {
    public void foo1() { }
    public void foo1() { }
}

