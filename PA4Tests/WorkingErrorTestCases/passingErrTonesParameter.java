import meggy.Meggy;

class passingErrTonesParameter {
    public static void main(String[] whatever){
   
    new A().valTone(Meggy.Tone.CC9);
    
    }
}

class A {
    public Meggy.Tone valTone(Meggy.Tone t) {
        return t;
    }

}

