import meggy.Meggy;

class lessThanByteToIntPromot {
    public static void main(String[] whatever){
       
        if((byte)2 < 3) {
            Meggy.setPixel((byte)3, (byte)2, Meggy.Color.GREEN);
        }

       if(2 < (byte)3) {
            Meggy.setPixel((byte)2, (byte)3, Meggy.Color.GREEN);
        }

    }
}

