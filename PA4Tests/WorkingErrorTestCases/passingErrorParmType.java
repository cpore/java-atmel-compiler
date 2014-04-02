import meggy.Meggy;

class passingErrorParmType {
    public static void main(String[] whatever){
   
    new A().valGreen(Meggy.Tone.C3);
    
    }
}

class A {
    public void valGreen(byte x) {
}

}

