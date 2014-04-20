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

    # NewExp
    ldi    r24, lo8(2)
    ldi    r25, hi8(2)
    # allocating object of size 2 on heap
    call    malloc
    # push object address
    # push two byte expression onto stack
    push   r25
    push   r24

    #### function call
    # put parameter values into appropriate registers
    # receiver will be passed as first param
    # load a two byte expression off stack
    pop    r24
    pop    r25

    call    Footesting

    # handle return value
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Color expression Meggy.Color.RED
    ldi    r22,1
    # push one byte expression onto stack
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



    .text
.global Footesting
    .type  Footesting, @function
Footesting:
    push   r29
    push   r28
    # make space for locals and params
    ldi    r30, 0
    push   r30
    push   r30
    push   r30
    push   r30

    # Copy stack pointer to frame pointer
    in     r28,__SP_L__
    in     r29,__SP_H__

    # save off parameters
    std    Y + 2, r25
    std    Y + 1, r24
/* done with function Footesting prologue */


    # NewExp
    ldi    r24, lo8(3)
    ldi    r25, hi8(3)
    # allocating object of size 3 on heap
    call    malloc
    # push object address
    # push two byte expression onto stack
    push   r25
    push   r24

    ### AssignStatement
    # load rhs exp
    # load a two byte expression off stack
    pop    r24
    pop    r25

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # store rhs into var b
    std    Z + 1, r25
    std    Z + 0, r24

    # IdExp
    # load value for variable b

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # variable is a member variable

    # load a two byte variable from base+offset
    ldd    r25, Z + 1
    ldd    r24, Z + 0
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 2
    ldi    r24,lo8(2)
    ldi    r25,hi8(2)
    # push two byte expression onto stack
    push   r25
    push   r24

    #### function call
    # put parameter values into appropriate registers
    # load a two byte expression off stack
    pop    r22
    pop    r23
    # receiver will be passed as first param
    # load a two byte expression off stack
    pop    r24
    pop    r25

    call    BarchangeY

    # handle return value
    # push two byte expression onto stack
    push   r25
    push   r24

    ### AssignStatement
    # load rhs exp
    # load a two byte expression off stack
    pop    r24
    pop    r25
    # store rhs into var a
    std    Y + 4, r25
    std    Y + 3, r24

    # IdExp
    # load value for variable b

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # variable is a member variable

    # load a two byte variable from base+offset
    ldd    r25, Z + 1
    ldd    r24, Z + 0
    # push two byte expression onto stack
    push   r25
    push   r24

    #### function call
    # put parameter values into appropriate registers
    # receiver will be passed as first param
    # load a two byte expression off stack
    pop    r24
    pop    r25

    call    BargetY

    # handle return value
    # push two byte expression onto stack
    push   r25
    push   r24

    # IdExp
    # load value for variable a
    # variable is a local or param variable

    # load a two byte variable from base+offset
    ldd    r25, Y + 4
    ldd    r24, Y + 3
    # push two byte expression onto stack
    push   r25
    push   r24
    # load a two byte expression off stack
    pop    r18
    pop    r19
    # load a two byte expression off stack
    pop    r24
    pop    r25

    # Do add operation
    add    r24, r18
    adc    r25, r19
    # push two byte expression onto stack
    push   r25
    push   r24

/* epilogue start for Footesting */
    # handle return value
    # load a two byte expression off stack
    pop    r24
    pop    r25
    # pop space off stack for parameters and locals
    pop    r30
    pop    r30
    pop    r30
    pop    r30
    # restoring the frame pointer
    pop    r28
    pop    r29
    ret
    .size Footesting, .-Footesting


    .text
.global BarchangeY
    .type  BarchangeY, @function
BarchangeY:
    push   r29
    push   r28
    # make space for locals and params
    ldi    r30, 0
    push   r30
    push   r30
    push   r30
    push   r30

    # Copy stack pointer to frame pointer
    in     r28,__SP_L__
    in     r29,__SP_H__

    # save off parameters
    std    Y + 2, r25
    std    Y + 1, r24
    std    Y + 4, r23
    std    Y + 3, r22
/* done with function BarchangeY prologue */


    # IdExp
    # load value for variable p
    # variable is a local or param variable

    # load a two byte variable from base+offset
    ldd    r25, Y + 4
    ldd    r24, Y + 3
    # push two byte expression onto stack
    push   r25
    push   r24

    ### AssignStatement
    # load rhs exp
    # load a two byte expression off stack
    pop    r24
    pop    r25

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # store rhs into var y
    std    Z + 2, r25
    std    Z + 1, r24


    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # push two byte expression onto stack
    push   r31
    push   r30

    #### function call
    # put parameter values into appropriate registers
    # receiver will be passed as first param
    # load a two byte expression off stack
    pop    r24
    pop    r25

    call    BargetY

    # handle return value
    # push two byte expression onto stack
    push   r25
    push   r24

/* epilogue start for BarchangeY */
    # handle return value
    # load a two byte expression off stack
    pop    r24
    pop    r25
    # pop space off stack for parameters and locals
    pop    r30
    pop    r30
    pop    r30
    pop    r30
    # restoring the frame pointer
    pop    r28
    pop    r29
    ret
    .size BarchangeY, .-BarchangeY


    .text
.global BargetY
    .type  BargetY, @function
BargetY:
    push   r29
    push   r28
    # make space for locals and params
    ldi    r30, 0
    push   r30
    push   r30

    # Copy stack pointer to frame pointer
    in     r28,__SP_L__
    in     r29,__SP_H__

    # save off parameters
    std    Y + 2, r25
    std    Y + 1, r24
/* done with function BargetY prologue */


    # IdExp
    # load value for variable y

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # variable is a member variable

    # load a two byte variable from base+offset
    ldd    r25, Z + 2
    ldd    r24, Z + 1
    # push two byte expression onto stack
    push   r25
    push   r24

/* epilogue start for BargetY */
    # handle return value
    # load a two byte expression off stack
    pop    r24
    pop    r25
    # pop space off stack for parameters and locals
    pop    r30
    pop    r30
    # restoring the frame pointer
    pop    r28
    pop    r29
    ret
    .size BargetY, .-BargetY

