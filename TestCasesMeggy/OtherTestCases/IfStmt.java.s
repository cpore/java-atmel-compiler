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
    brlt     MJ_L3
    ldi    r25, 0
    jmp    MJ_L4
MJ_L3:
    ldi    r25, hi8(-1)
MJ_L4:

    # Do INT add operation
    add r24, r18
    adc r25, r19
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

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

    cp     r24, r18
    brne MJ_L6
    cp    r25, r19
    #Branch if not equals
    brne MJ_L6
    ldi    r24, 1
    push   r24
    jmp    MJ_L5
MJ_L6:
    ldi    r24, 0
    push   r24
MJ_L5:

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

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
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

    jmp    MJ_L1
    # else label for if
MJ_L2:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
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

    # done label for if
MJ_L1:
    #### if statement

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
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

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    # Do INT sub operation
    sub r24, r18
    sbc r25, r19
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp     r24, r18
    brne MJ_L11
    cp    r25, r19
    #Branch if not equals
    brne MJ_L11
    ldi    r24, 1
    push   r24
    jmp    MJ_L10
MJ_L11:
    ldi    r24, 0
    push   r24
MJ_L10:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L9
    brne   MJ_L7
    jmp    MJ_L9

    # then label for if
MJ_L7:

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

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

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

    # Load constant int 6
    ldi    r24,lo8(6)
    ldi    r25,hi8(6)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp     r24, r18
    brne MJ_L16
    cp    r25, r19
    #Branch if not equals
    brne MJ_L16
    ldi    r24, 1
    push   r24
    jmp    MJ_L15
MJ_L16:
    ldi    r24, 0
    push   r24
MJ_L15:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L14
    brne   MJ_L12
    jmp    MJ_L14

    # then label for if
MJ_L12:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
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

    jmp    MJ_L13
    # else label for if
MJ_L14:

    # done label for if
MJ_L13:
    jmp    MJ_L8
    # else label for if
MJ_L9:

    # done label for if
MJ_L8:
    #### if statement

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
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

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    # Do INT sub operation
    sub r24, r18
    sbc r25, r19
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp     r24, r18
    brne MJ_L21
    cp    r25, r19
    #Branch if not equals
    brne MJ_L21
    ldi    r24, 1
    push   r24
    jmp    MJ_L20
MJ_L21:
    ldi    r24, 0
    push   r24
MJ_L20:

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

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

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

    # Load constant int 7
    ldi    r24,lo8(7)
    ldi    r25,hi8(7)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp     r24, r18
    brne MJ_L26
    cp    r25, r19
    #Branch if not equals
    brne MJ_L26
    ldi    r24, 1
    push   r24
    jmp    MJ_L25
MJ_L26:
    ldi    r24, 0
    push   r24
MJ_L25:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L24
    brne   MJ_L22
    jmp    MJ_L24

    # then label for if
MJ_L22:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
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

    jmp    MJ_L23
    # else label for if
MJ_L24:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

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

    # done label for if
MJ_L23:
    jmp    MJ_L18
    # else label for if
MJ_L19:

    # done label for if
MJ_L18:
    #### if statement

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
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

    # Load constant int 1
    ldi    r24,lo8(1)
    ldi    r25,hi8(1)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    # Do INT add operation
    add r24, r18
    adc r25, r19
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp     r24, r18
    brne MJ_L31
    cp    r25, r19
    #Branch if not equals
    brne MJ_L31
    ldi    r24, 1
    push   r24
    jmp    MJ_L30
MJ_L31:
    ldi    r24, 0
    push   r24
MJ_L30:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L29
    brne   MJ_L27
    jmp    MJ_L29

    # then label for if
MJ_L27:

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

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

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

    # Load constant int 6
    ldi    r24,lo8(6)
    ldi    r25,hi8(6)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp     r24, r18
    brne MJ_L36
    cp    r25, r19
    #Branch if not equals
    brne MJ_L36
    ldi    r24, 1
    push   r24
    jmp    MJ_L35
MJ_L36:
    ldi    r24, 0
    push   r24
MJ_L35:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L34
    brne   MJ_L32
    jmp    MJ_L34

    # then label for if
MJ_L32:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 4
    ldi    r24,lo8(4)
    ldi    r25,hi8(4)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load Color expression Meggy.Color.ORANGE
    ldi    r22, 2
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

    jmp    MJ_L33
    # else label for if
MJ_L34:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 4
    ldi    r24,lo8(4)
    ldi    r25,hi8(4)
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

    # done label for if
MJ_L33:
    jmp    MJ_L28
    # else label for if
MJ_L29:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 4
    ldi    r24,lo8(4)
    ldi    r25,hi8(4)
    # push two byte expression onto stack
    push   r25
    push   r24

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

    # done label for if
MJ_L28:
    #### if statement

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
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

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
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

    # Do INT sub operation
    sub r24, r18
    sbc r25, r19
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp     r24, r18
    brne MJ_L41
    cp    r25, r19
    #Branch if not equals
    brne MJ_L41
    ldi    r24, 1
    push   r24
    jmp    MJ_L40
MJ_L41:
    ldi    r24, 0
    push   r24
MJ_L40:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L39
    brne   MJ_L37
    jmp    MJ_L39

    # then label for if
MJ_L37:

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

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

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

    # Load constant int 7
    ldi    r24,lo8(7)
    ldi    r25,hi8(7)
    # push two byte expression onto stack
    push   r25
    push   r24

    #Load an int expression off stack
    pop r18
    pop r19

    #Load an int expression off stack
    pop r24
    pop r25

    cp     r24, r18
    brne MJ_L46
    cp    r25, r19
    #Branch if not equals
    brne MJ_L46
    ldi    r24, 1
    push   r24
    jmp    MJ_L45
MJ_L46:
    ldi    r24, 0
    push   r24
MJ_L45:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L44
    brne   MJ_L42
    jmp    MJ_L44

    # then label for if
MJ_L42:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 5
    ldi    r24,lo8(5)
    ldi    r25,hi8(5)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load Color expression Meggy.Color.ORANGE
    ldi    r22, 2
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

    jmp    MJ_L43
    # else label for if
MJ_L44:

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

    cp     r24, r18
    brne MJ_L51
    cp    r25, r19
    #Branch if not equals
    brne MJ_L51
    ldi    r24, 1
    push   r24
    jmp    MJ_L50
MJ_L51:
    ldi    r24, 0
    push   r24
MJ_L50:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L49
    brne   MJ_L47
    jmp    MJ_L49

    # then label for if
MJ_L47:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 5
    ldi    r24,lo8(5)
    ldi    r25,hi8(5)
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

    jmp    MJ_L48
    # else label for if
MJ_L49:

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 5
    ldi    r24,lo8(5)
    ldi    r25,hi8(5)
    # push two byte expression onto stack
    push   r25
    push   r24

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

    # done label for if
MJ_L48:
    # done label for if
MJ_L43:
    jmp    MJ_L38
    # else label for if
MJ_L39:

    # done label for if
MJ_L38:


/* epilogue start */
    endLabel:
    jmp endLabel
    ret
    .size   main, .-main


