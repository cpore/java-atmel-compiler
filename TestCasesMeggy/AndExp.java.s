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

    # Load True/1 expression
    ldi    r24, 1
    # push one byte expression onto stack
    push   r24

    # &&: if left operand is false do not eval right
    # load a one byte expression off stack
    pop    r24
    # push one byte expression onto stack
    push   r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L5
    brne   MJ_L6
    jmp    MJ_L5

MJ_L6:
    # right operand
    # load a one byte expression off stack
pop    r24

    # Load True/1 expression
    ldi    r24, 1
    # push one byte expression onto stack
    push   r24

MJ_L5:
    # &&: if left operand is false do not eval right
    # load a one byte expression off stack
    pop    r24
    # push one byte expression onto stack
    push   r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L3
    brne   MJ_L4
    jmp    MJ_L3

MJ_L4:
    # right operand
    # load a one byte expression off stack
pop    r24

    # Load false/0 expression
    ldi    r24, 0
    # push one byte expression onto stack
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

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r24
    pop r25

    # Do INT sub operation twice to negate
    #Copy Registers
    mov r18, r24
    mov r19, r25
    sub r24, r18
    sbc r25, r19
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

    # Load constant int 7
    ldi    r24,lo8(7)
    ldi    r25,hi8(7)
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
    brlt     MJ_L7
    ldi    r25, 0
    jmp    MJ_L8
MJ_L7:
    ldi    r25, hi8(-1)
MJ_L8:

    # Do INT sub operation twice to negate
    #Copy Registers
    mov r18, r24
    mov r19, r25
    sub r24, r18
    sbc r25, r19
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

    # Do multiplication of bytes
    pop r24
    pop r26
    muls r26, r24
    push r1
    push r0

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 6
    ldi    r24,lo8(6)
    ldi    r25,hi8(6)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load Color expression Meggy.Color.RED
    ldi    r22, 1
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

    jmp    MJ_L1
    # else label for if
MJ_L2:

    # done label for if
MJ_L1:


/* epilogue start */
    endLabel:
    jmp endLabel
    ret
    .size   main, .-main


