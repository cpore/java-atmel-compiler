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
    brne MJ_L4
    cp    r25, r19
    #Branch if not equals
    brne MJ_L4
    ldi    r24, 1
    push   r24
    jmp    MJ_L3
MJ_L4:
    ldi    r24, 0
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

    # Load constant int 10
    ldi    r24,lo8(10)
    ldi    r25,hi8(10)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 5
    ldi    r24,lo8(5)
    ldi    r25,hi8(5)
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

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L8
    ldi    r25, 0
    jmp    MJ_L9
MJ_L8:
    ldi    r25, hi8(-1)
MJ_L9:

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

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L10
    ldi    r25, 0
    jmp    MJ_L11
MJ_L10:
    ldi    r25, hi8(-1)
MJ_L11:

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

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L12
    ldi    r25, 0
    jmp    MJ_L13
MJ_L12:
    ldi    r25, hi8(-1)
MJ_L13:

    cp     r24, r18
    brne MJ_L15
    cp    r25, r19
    #Branch if not equals
    brne MJ_L15
    ldi    r24, 1
    push   r24
    jmp    MJ_L14
MJ_L15:
    ldi    r24, 0
    push   r24
MJ_L14:

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

    jmp    MJ_L6
    # else label for if
MJ_L7:

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
MJ_L6:
    #### if statement

    # Load constant int 4
    ldi    r24,lo8(4)
    ldi    r25,hi8(4)
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

    #Load an int expression off stack
    pop r18
    pop r19

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L19
    ldi    r25, 0
    jmp    MJ_L20
MJ_L19:
    ldi    r25, hi8(-1)
MJ_L20:

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
    brne MJ_L22
    cp    r25, r19
    #Branch if not equals
    brne MJ_L22
    ldi    r24, 1
    push   r24
    jmp    MJ_L21
MJ_L22:
    ldi    r24, 0
    push   r24
MJ_L21:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L18
    brne   MJ_L16
    jmp    MJ_L18

    # then label for if
MJ_L16:

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

    jmp    MJ_L17
    # else label for if
MJ_L18:

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
MJ_L17:
    #### if statement

    # Load constant int 6
    ldi    r24,lo8(6)
    ldi    r25,hi8(6)
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

    # Load constant int 5
    ldi    r24,lo8(5)
    ldi    r25,hi8(5)
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
    brlt     MJ_L26
    ldi    r19, 0
    jmp    MJ_L27
MJ_L26:
    ldi    r19, hi8(-1)
MJ_L27:

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L28
    ldi    r25, 0
    jmp    MJ_L29
MJ_L28:
    ldi    r25, hi8(-1)
MJ_L29:

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
    #WANT breq MJ_L25
    brne   MJ_L23
    jmp    MJ_L25

    # then label for if
MJ_L23:

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

    jmp    MJ_L24
    # else label for if
MJ_L25:

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
MJ_L24:
    #### if statement

    # Load constant int 512
    ldi    r24,lo8(512)
    ldi    r25,hi8(512)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 512
    ldi    r24,lo8(512)
    ldi    r25,hi8(512)
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

    # Load constant int 1024
    ldi    r24,lo8(1024)
    ldi    r25,hi8(1024)
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
    #### if statement

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
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
    brlt     MJ_L40
    ldi    r19, 0
    jmp    MJ_L41
MJ_L40:
    ldi    r19, hi8(-1)
MJ_L41:

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

    # Load constant int 5
    ldi    r24,lo8(5)
    ldi    r25,hi8(5)
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
    brne MJ_L43
    cp    r25, r19
    #Branch if not equals
    brne MJ_L43
    ldi    r24, 1
    push   r24
    jmp    MJ_L42
MJ_L43:
    ldi    r24, 0
    push   r24
MJ_L42:

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

    jmp    MJ_L38
    # else label for if
MJ_L39:

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
MJ_L38:
    #### if statement

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
    brlt     MJ_L47
    ldi    r25, 0
    jmp    MJ_L48
MJ_L47:
    ldi    r25, hi8(-1)
MJ_L48:

    # Do INT add operation
    add r24, r18
    adc r25, r19
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    # Load constant int 5
    ldi    r24,lo8(5)
    ldi    r25,hi8(5)
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
    brne MJ_L50
    cp    r25, r19
    #Branch if not equals
    brne MJ_L50
    ldi    r24, 1
    push   r24
    jmp    MJ_L49
MJ_L50:
    ldi    r24, 0
    push   r24
MJ_L49:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L46
    brne   MJ_L44
    jmp    MJ_L46

    # then label for if
MJ_L44:

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

    jmp    MJ_L45
    # else label for if
MJ_L46:

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
MJ_L45:
    #### if statement

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
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

    #Load a one byte expression off stack
    pop r18
    # promoting a byte to an int
    tst     r18
    brlt     MJ_L54
    ldi    r19, 0
    jmp    MJ_L55
MJ_L54:
    ldi    r19, hi8(-1)
MJ_L55:

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L56
    ldi    r25, 0
    jmp    MJ_L57
MJ_L56:
    ldi    r25, hi8(-1)
MJ_L57:

    cp     r24, r18
    brne MJ_L59
    cp    r25, r19
    #Branch if not equals
    brne MJ_L59
    ldi    r24, 1
    push   r24
    jmp    MJ_L58
MJ_L59:
    ldi    r24, 0
    push   r24
MJ_L58:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L53
    brne   MJ_L51
    jmp    MJ_L53

    # then label for if
MJ_L51:

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

    jmp    MJ_L52
    # else label for if
MJ_L53:

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
MJ_L52:
    #### if statement

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 512
    ldi    r24,lo8(512)
    ldi    r25,hi8(512)
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

    # Load constant int 512
    ldi    r24,lo8(512)
    ldi    r25,hi8(512)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 1024
    ldi    r24,lo8(1024)
    ldi    r25,hi8(1024)
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
    brne MJ_L64
    cp    r25, r19
    #Branch if not equals
    brne MJ_L64
    ldi    r24, 1
    push   r24
    jmp    MJ_L63
MJ_L64:
    ldi    r24, 0
    push   r24
MJ_L63:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L62
    brne   MJ_L60
    jmp    MJ_L62

    # then label for if
MJ_L60:

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

    jmp    MJ_L61
    # else label for if
MJ_L62:

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

    # done label for if
MJ_L61:
    #### if statement

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

    # Load constant int 32
    ldi    r24,lo8(32)
    ldi    r25,hi8(32)
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
    brlt     MJ_L68
    ldi    r25, 0
    jmp    MJ_L69
MJ_L68:
    ldi    r25, hi8(-1)
MJ_L69:

    # Do INT sub operation
    sub r24, r18
    sbc r25, r19
    # push hi order byte first
    # push two byte expression onto stack
    push r25
    push r24

    # Load constant int 512
    ldi    r24,lo8(512)
    ldi    r25,hi8(512)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 544
    ldi    r24,lo8(544)
    ldi    r25,hi8(544)
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
    brne MJ_L71
    cp    r25, r19
    #Branch if not equals
    brne MJ_L71
    ldi    r24, 1
    push   r24
    jmp    MJ_L70
MJ_L71:
    ldi    r24, 0
    push   r24
MJ_L70:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L67
    brne   MJ_L65
    jmp    MJ_L67

    # then label for if
MJ_L65:

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

    jmp    MJ_L66
    # else label for if
MJ_L67:

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

    # done label for if
MJ_L66:
    #### if statement

    # Load constant int 0
    ldi    r24,lo8(0)
    ldi    r25,hi8(0)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 32
    ldi    r24,lo8(32)
    ldi    r25,hi8(32)
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
    brlt     MJ_L75
    ldi    r19, 0
    jmp    MJ_L76
MJ_L75:
    ldi    r19, hi8(-1)
MJ_L76:

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

    # Load constant int 512
    ldi    r24,lo8(512)
    ldi    r25,hi8(512)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 544
    ldi    r24,lo8(544)
    ldi    r25,hi8(544)
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
    brne MJ_L78
    cp    r25, r19
    #Branch if not equals
    brne MJ_L78
    ldi    r24, 1
    push   r24
    jmp    MJ_L77
MJ_L78:
    ldi    r24, 0
    push   r24
MJ_L77:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L74
    brne   MJ_L72
    jmp    MJ_L74

    # then label for if
MJ_L72:

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

    jmp    MJ_L73
    # else label for if
MJ_L74:

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
MJ_L73:
    #### if statement

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

    # Load constant int 32
    ldi    r24,lo8(32)
    ldi    r25,hi8(32)
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
    brlt     MJ_L82
    ldi    r19, 0
    jmp    MJ_L83
MJ_L82:
    ldi    r19, hi8(-1)
MJ_L83:

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L84
    ldi    r25, 0
    jmp    MJ_L85
MJ_L84:
    ldi    r25, hi8(-1)
MJ_L85:

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

    # Load constant int 512
    ldi    r24,lo8(512)
    ldi    r25,hi8(512)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 544
    ldi    r24,lo8(544)
    ldi    r25,hi8(544)
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
    brlt     MJ_L86
    ldi    r19, 0
    jmp    MJ_L87
MJ_L86:
    ldi    r19, hi8(-1)
MJ_L87:

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L88
    ldi    r25, 0
    jmp    MJ_L89
MJ_L88:
    ldi    r25, hi8(-1)
MJ_L89:

    cp     r24, r18
    brne MJ_L91
    cp    r25, r19
    #Branch if not equals
    brne MJ_L91
    ldi    r24, 1
    push   r24
    jmp    MJ_L90
MJ_L91:
    ldi    r24, 0
    push   r24
MJ_L90:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L81
    brne   MJ_L79
    jmp    MJ_L81

    # then label for if
MJ_L79:

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

    jmp    MJ_L80
    # else label for if
MJ_L81:

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

    # done label for if
MJ_L80:
    #### if statement

    # Load constant int 32
    ldi    r24,lo8(32)
    ldi    r25,hi8(32)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 32
    ldi    r24,lo8(32)
    ldi    r25,hi8(32)
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
    brlt     MJ_L95
    ldi    r25, 0
    jmp    MJ_L96
MJ_L95:
    ldi    r25, hi8(-1)
MJ_L96:

    cp     r24, r18
    brne MJ_L98
    cp    r25, r19
    #Branch if not equals
    brne MJ_L98
    ldi    r24, 1
    push   r24
    jmp    MJ_L97
MJ_L98:
    ldi    r24, 0
    push   r24
MJ_L97:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L94
    brne   MJ_L92
    jmp    MJ_L94

    # then label for if
MJ_L92:

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

    jmp    MJ_L93
    # else label for if
MJ_L94:

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
MJ_L93:
    #### if statement

    # Load constant int 32
    ldi    r24,lo8(32)
    ldi    r25,hi8(32)
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

    # Load constant int 32
    ldi    r24,lo8(32)
    ldi    r25,hi8(32)
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

    #Load a one byte expression off stack
    pop r18
    # promoting a byte to an int
    tst     r18
    brlt     MJ_L102
    ldi    r19, 0
    jmp    MJ_L103
MJ_L102:
    ldi    r19, hi8(-1)
MJ_L103:

    #Load an int expression off stack
    pop r24
    pop r25

    cp     r24, r18
    brne MJ_L105
    cp    r25, r19
    #Branch if not equals
    brne MJ_L105
    ldi    r24, 1
    push   r24
    jmp    MJ_L104
MJ_L105:
    ldi    r24, 0
    push   r24
MJ_L104:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L101
    brne   MJ_L99
    jmp    MJ_L101

    # then label for if
MJ_L99:

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

    jmp    MJ_L100
    # else label for if
MJ_L101:

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
MJ_L100:
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

    ### Meggy.getPixel(x,y) call
    # load a one byte expression off stack
    pop    r22
    # load a one byte expression off stack
    pop    r24
    call   _Z6ReadPxhh
    # push one byte color expression onto stack
    push   r24

    # Load Color expression Meggy.Color.GREEN
    ldi    r22, 4
    # push two byte expression onto stack
    push   r22

    #Load a one byte expression off stack
    pop r18
    # promoting a byte to an int
    tst     r18
    brlt     MJ_L109
    ldi    r19, 0
    jmp    MJ_L110
MJ_L109:
    ldi    r19, hi8(-1)
MJ_L110:

    #Load a one byte expression off stack
    pop r24
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L111
    ldi    r25, 0
    jmp    MJ_L112
MJ_L111:
    ldi    r25, hi8(-1)
MJ_L112:

    cp     r24, r18
    brne MJ_L114
    cp    r25, r19
    #Branch if not equals
    brne MJ_L114
    ldi    r24, 1
    push   r24
    jmp    MJ_L113
MJ_L114:
    ldi    r24, 0
    push   r24
MJ_L113:

    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L108
    brne   MJ_L106
    jmp    MJ_L108

    # then label for if
MJ_L106:

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

    jmp    MJ_L107
    # else label for if
MJ_L108:

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
MJ_L107:
    #### if statement

    ### MeggyCheckButton
    call     _Z16CheckButtonsDownv
    # pop button value off stack
    lds    r24, Button_A
    # if button value is zero, push 0 else push 1
    tst    r24
    breq   MJ_L118
MJ_L119:
    ldi    r24, 1
    jmp    MJ_L120
MJ_L118:
MJ_L120:
    # push one byte expression onto stack
    push   r24
    # load condition and branch if false
    # load a one byte expression off stack
    pop    r24
    #load zero into reg
    ldi    r25, 0

    #use cp to set SREG
    cp     r24, r25
    #WANT breq MJ_L117
    brne   MJ_L115
    jmp    MJ_L117

    # then label for if
MJ_L115:

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

    jmp    MJ_L116
    # else label for if
MJ_L117:

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
MJ_L116:


/* epilogue start */
    endLabel:
    jmp endLabel
    ret
    .size   main, .-main


