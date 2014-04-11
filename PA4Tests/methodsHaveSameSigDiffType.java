import meggy.Meggy;

class methodsHaveSameSigDiffType {
    public static void main(String[] whatever){
    }
}

class A {
    public int foo1(byte x, int y) {
        return x;
    }

   public int foo2(int x, byte y) {
        return x;
    }

public byte foo3(int x, int y) {
        return (byte)x;
    }

}

