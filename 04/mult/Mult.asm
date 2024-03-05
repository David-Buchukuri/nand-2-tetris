// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// The algorithm is based on repetitive addition.


// we will transform both numbers to positive and multiply them
// before we transform them to positive, we should save some information whether to transform product
// to negative in the end
// only if one of the number is negative, outcome should be negative
// we will keep 1 variable, shouldBeNegative
// if first number is negative we will decrement that variable
// if second number is negative we will increment that variable
// there are 4 possible cases
// if both numbers are positive, that variable will remain 0
// if both numbers are negative, it will remain 0
// if one of variables is negative, that variable will be 1 or -1
// we see that, when that var will not equal to 0, we should transform our product to negative


@R2
M=0

(CHECK_FIRST_NUM)
    @shouldResultBeNegative
    @0
    D=M
    @CHECK_SECOND_NUM
    D;JGE
    @shouldResultBeNegative
    M=M-1

(CHECK_SECOND_NUM)
    @1
    D=M
    @TURN_FIRST_NUM_POSITIVE
    D;JGE
    @shouldResultBeNegative
    M=M+1


(TURN_FIRST_NUM_POSITIVE)
    // check if it's already positive
    @0
    D=M
    @TURN_SECOND_NUM_POSITIVE
    D;JGE

    // turning to positive
    D=-1
    @0
    D=D-M
    D=D+1
    M=D
    

(TURN_SECOND_NUM_POSITIVE)
    // check if it's already positive
    @1
    D=M
    @LOOP
    D;JGE

    // turning to positive
    D=-1
    @1
    D=D-M
    D=D+1
    M=D



// we will use second number(ram[1]) as i and decrement it until1 it reaches 0,
// on each step we will make ram[2] += first number
(LOOP)
    // termination check
    @1
    D=M
    @TURN_RESULT_TO_NEGATIVE_IF_NEEDED
    D;JLE

    // adding to result
    @0
    D=M
    @2
    M=M+D

    // decrement i
    @1
    M=M-1

    // start again from the start of the loop
    @LOOP
    0;JMP


(TURN_RESULT_TO_NEGATIVE_IF_NEEDED)
    @shouldResultBeNegative
    D=M
    @END
    D;JEQ
    @2
    D=M
    M=M-D
    M=M-D


(END)
    @END
    0;JMP



