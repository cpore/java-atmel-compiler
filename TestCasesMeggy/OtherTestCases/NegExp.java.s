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

    # Load constant int from Symbol
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
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L0
    ldi    r25, 0
    jmp    MJ_L1
MJ_L0:
    ldi    r25, hi8(-1)
MJ_L1:

    # Load constant int from Symbol
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
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L2
    ldi    r25, 0
    jmp    MJ_L3
MJ_L2:
    ldi    r25, hi8(-1)
MJ_L3:

    # Do INT sub operation twice to negate
    #Copy Registers
    mov r20, r24
    mov r21, r25
    sub r24, r20
    sbc r25, r21
    sub r24, r20
    sbc r25, r21
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    #Load an int expression off stack
    pop r24
    pop r25

    # Do INT sub operation twice to negate
    #Copy Registers
    mov r20, r24
    mov r21, r25
    sub r24, r20
    sbc r25, r21
    sub r24, r20
    sbc r25, r21
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    #Load an int expression off stack
    pop r18
    pop r19

    # Do INT sub operation
    sub r24, r18
    sbc r25, r19
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int from Symbol
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int from Symbol
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r24
    pop r25

    #Load an int expression off stack
    pop r18
    pop r19

    # Do INT add operation
    add r24, r18
    adc r25, r19
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load Color expression Meggy.Color.GREEN
    ldi    r22, 4
    # push two byte expression onto stack
    push   r22

    ### Meggy.setPixel(x,y,color) call
    # load a one byte expression off stack
    pop    r20
    # load a one byte expression off stack
    pop    r22
    # load a one byte expression off stack
    pop    r24
    call   _Z6DrawPxhhh
    call   _Z12DisplaySlatev



/* epilogue start */
    endLabel:
    jmp endLabel
    ret
    .size   main, .-main


