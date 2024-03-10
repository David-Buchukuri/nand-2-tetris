// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen
// by writing 'black' in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen by writing
// 'white' in every pixel;
// the screen should remain fully clear as long as no key is pressed.

//// Replace this comment with your code.


// i guess run the infinite loop, if we detect that key is pressed jump to procedure where we blacken the screen
// once the procedure is done we jump back to the loop
// if key is not pressed we jump to procedure where we whiten out screen

// to do this efficiently, we can keep pointer which will tell us till which register is screen blackened
// if screen is blackened till SCREEN + 8k registers, and key is pressed down, we wont need to do anything, because
// we can tell that screen is already fully blackened

// if screen is blackened till SCREEN + 2K that means there is still 6K registers to go

// if there is no key pressed, we look up till which register screen is blackened, start from there,
// turn registers to 0 and decrement the pointer

// this allows us to midway stop screen blackening or whitening operations


// initializing variables

// making blackTill equal to screen's first register - 1, because it is inclusive pointer
// we increment first and then make register black
@SCREEN
D=A
@blackTill
M=D-1



// not 8160 because in total there are 8160 registers for the screen, including SCREEN itself 
@8159
D=A
@SCREEN
A=D+A
D=A
@lastRegisterOfScreen
M=D
A=M

(LOOP)
    @KBD
    D=M
    @MAKE_BLACK
    D;JNE

    // if we got to this instruction, that means jump for make black was skipped, meaning keyboard is idle
    // therefore we can directly jump to make white
    @MAKE_WHITE
    0;JEQ


(MAKE_BLACK)
    // if blackTill is equal to last register's value, go back to loop
    // otherwise process

    @lastRegisterOfScreen
    D=M
    @blackTill
    D=D-M
    @LOOP
    D;JEQ

    // make black
    @blackTill
    M=M+1
    A=M
    M=-1
    
    @LOOP
    0;JMP


(MAKE_WHITE)
    // if blackTill is equal to first register - 1, that means whole screen is white 
    // and we jump back to the loop
    // otherwise, make register white and only then decrement blackTill

    @SCREEN
    A=A-1
    D=A
    @blackTill
    D=D-M
    @LOOP
    D;JEQ

    // make white
    @blackTill
    A=M
    M=0
    @blackTill
    M=M-1
    
    @LOOP
    0;JMP