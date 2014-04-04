/**
 *
 */

import meggy.Meggy;

class LtExp {

    public static void main(String[] whatever){
        if(1 < 2)
          Meggy.toneStart(Meggy.Tone.B3, 50);
        if(2 < 1)
          Meggy.toneStart(Meggy.Tone.C3, 50);

        if((byte)1 < 2)
          Meggy.toneStart(Meggy.Tone.B3, 50);
        if(2 < (byte)1)
          Meggy.toneStart(Meggy.Tone.C3, 50);

        if(1 < (byte)2)
          Meggy.toneStart(Meggy.Tone.B3, 50);
        if((byte)2 < 1)
          Meggy.toneStart(Meggy.Tone.C3, 50);
    }
}

