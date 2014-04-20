    .file  "main.java"
__SREG__ = 0x3f
__SP_H__ = 0x3e
__SP_L__ = 0x3d
__tmp_reg__ = 0
__zero_reg__ = 1
    .global __do_copy_data
    .global __do_clear_bss
    .text
.global main
    .type   main, @function
main:
    push r29
    push r28
    in r28,__SP_L__
    in r29,__SP_H__
/* prologue: function */
    call _Z18MeggyJrSimpleSetupv 
    /* Need to call this so that the meggy library gets set up */

    #### if statement

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp    r24, r18
    cpc   r25, r19
    #Branch if less than
    brlt MJ_L4

    # load false
    ldi    r24, 0
    push   r24
    jmp    MJ_L3

    # load true 
MJ_L4:
    ldi    r24, 1
    push   r24
MJ_L3:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L2
    brne   MJ_L0
    jmp    MJ_L2

    # then label for if
MJ_L0:

    # Push Meggy.Tone.B3 onto the stack.
    ldi    r24,lo8(32397)
    ldi    r25,hi8(32397)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 50
    ldi    r24,lo8(50)
    ldi    r25,hi8(50)
    # push two byte expression onto stack
    push   r25
    push   r24

    ### Meggy.toneStart(tone, time_ms) call
    # load a two byte expression off stack
    pop    r22
    pop    r23
    # load a two byte expression off stack
    pop    r24
    pop    r25
    call   _Z10Tone_Startjj

    jmp    MJ_L1
    # else label for if
MJ_L2:

    # done label for if
MJ_L1:
    #### if statement

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp    r24, r18
    cpc   r25, r19
    #Branch if less than
    brlt MJ_L9

    # load false
    ldi    r24, 0
    push   r24
    jmp    MJ_L8

    # load true 
MJ_L9:
    ldi    r24, 1
    push   r24
MJ_L8:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L7
    brne   MJ_L5
    jmp    MJ_L7

    # then label for if
MJ_L5:

    # Push Meggy.Tone.C3 onto the stack.
    ldi    r24,lo8(61157)
    ldi    r25,hi8(61157)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 50
    ldi    r24,lo8(50)
    ldi    r25,hi8(50)
    # push two byte expression onto stack
    push   r25
    push   r24

    ### Meggy.toneStart(tone, time_ms) call
    # load a two byte expression off stack
    pop    r22
    pop    r23
    # load a two byte expression off stack
    pop    r24
    pop    r25
    call   _Z10Tone_Startjj

    jmp    MJ_L6
    # else label for if
MJ_L7:

    # done label for if
MJ_L6:
    #### if statement

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L13
    ldi    r25, 0
    jmp    MJ_L14
MJ_L13:
    ldi    r25, hi8(-1)
MJ_L14:

    cp    r24, r18
    cpc   r25, r19
    #Branch if less than
    brlt MJ_L16

    # load false
    ldi    r24, 0
    push   r24
    jmp    MJ_L15

    # load true 
MJ_L16:
    ldi    r24, 1
    push   r24
MJ_L15:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L12
    brne   MJ_L10
    jmp    MJ_L12

    # then label for if
MJ_L10:

    # Push Meggy.Tone.B3 onto the stack.
    ldi    r24,lo8(32397)
    ldi    r25,hi8(32397)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 50
    ldi    r24,lo8(50)
    ldi    r25,hi8(50)
    # push two byte expression onto stack
    push   r25
    push   r24

    ### Meggy.toneStart(tone, time_ms) call
    # load a two byte expression off stack
    pop    r22
    pop    r23
    # load a two byte expression off stack
    pop    r24
    pop    r25
    call   _Z10Tone_Startjj

    jmp    MJ_L11
    # else label for if
MJ_L12:

    # done label for if
MJ_L11:
    #### if statement

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    #Load a one byte expression off stack
    pop r18
    # promoting a byte to an int
    tst     r18
    brlt     MJ_L20
    ldi    r19, 0
    jmp    MJ_L21
MJ_L20:
    ldi    r19, hi8(-1)
MJ_L21:

    #Load an int expression off stack
    pop r24
    pop r25

    cp    r24, r18
    cpc   r25, r19
    #Branch if less than
    brlt MJ_L23

    # load false
    ldi    r24, 0
    push   r24
    jmp    MJ_L22

    # load true 
MJ_L23:
    ldi    r24, 1
    push   r24
MJ_L22:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L19
    brne   MJ_L17
    jmp    MJ_L19

    # then label for if
MJ_L17:

    # Push Meggy.Tone.C3 onto the stack.
    ldi    r24,lo8(61157)
    ldi    r25,hi8(61157)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 50
    ldi    r24,lo8(50)
    ldi    r25,hi8(50)
    # push two byte expression onto stack
    push   r25
    push   r24

    ### Meggy.toneStart(tone, time_ms) call
    # load a two byte expression off stack
    pop    r22
    pop    r23
    # load a two byte expression off stack
    pop    r24
    pop    r25
    call   _Z10Tone_Startjj

    jmp    MJ_L18
    # else label for if
MJ_L19:

    # done label for if
MJ_L18:
    #### if statement

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    #Load a one byte expression off stack
    pop r18
    # promoting a byte to an int
    tst     r18
    brlt     MJ_L27
    ldi    r19, 0
    jmp    MJ_L28
MJ_L27:
    ldi    r19, hi8(-1)
MJ_L28:

    #Load an int expression off stack
    pop r24
    pop r25

    cp    r24, r18
    cpc   r25, r19
    #Branch if less than
    brlt MJ_L30

    # load false
    ldi    r24, 0
    push   r24
    jmp    MJ_L29

    # load true 
MJ_L30:
    ldi    r24, 1
    push   r24
MJ_L29:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L26
    brne   MJ_L24
    jmp    MJ_L26

    # then label for if
MJ_L24:

    # Push Meggy.Tone.B3 onto the stack.
    ldi    r24,lo8(32397)
    ldi    r25,hi8(32397)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 50
    ldi    r24,lo8(50)
    ldi    r25,hi8(50)
    # push two byte expression onto stack
    push   r25
    push   r24

    ### Meggy.toneStart(tone, time_ms) call
    # load a two byte expression off stack
    pop    r22
    pop    r23
    # load a two byte expression off stack
    pop    r24
    pop    r25
    call   _Z10Tone_Startjj

    jmp    MJ_L25
    # else label for if
MJ_L26:

    # done label for if
MJ_L25:
    #### if statement

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L34
    ldi    r25, 0
    jmp    MJ_L35
MJ_L34:
    ldi    r25, hi8(-1)
MJ_L35:

    cp    r24, r18
    cpc   r25, r19
    #Branch if less than
    brlt MJ_L37

    # load false
    ldi    r24, 0
    push   r24
    jmp    MJ_L36

    # load true 
MJ_L37:
    ldi    r24, 1
    push   r24
MJ_L36:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L33
    brne   MJ_L31
    jmp    MJ_L33

    # then label for if
MJ_L31:

    # Push Meggy.Tone.C3 onto the stack.
    ldi    r24,lo8(61157)
    ldi    r25,hi8(61157)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 50
    ldi    r24,lo8(50)
    ldi    r25,hi8(50)
    # push two byte expression onto stack
    push   r25
    push   r24

    ### Meggy.toneStart(tone, time_ms) call
    # load a two byte expression off stack
    pop    r22
    pop    r23
    # load a two byte expression off stack
    pop    r24
    pop    r25
    call   _Z10Tone_Startjj

    jmp    MJ_L32
    # else label for if
MJ_L33:

    # done label for if
MJ_L32:


/* epilogue start */
    endLabel:
    jmp endLabel
    ret
    .size   main, .-main


