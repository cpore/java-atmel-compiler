import meggy.Meggy;

class MeggyGetPixel {
    public static void main(String[] whatever){
            
        if (Meggy.getPixel((byte)1, (byte)1) == Meggy.Color.BLUE) {
            Meggy.setPixel( (byte)1, (byte)1, Meggy.Color.WHITE );
        }
    }
}

