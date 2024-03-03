// Draws rectangle on top left corner of a screen. Width is fixed to 16px. height can be specified by user. 
// Desired height should be written in ram[0]


// initializing i
@i
M=0

// initializing initial address
@SCREEN
D=A

@address
M=D

(LOOP)
    // check to terminate loop
    // calculate ram[0] - i, if it's less than 0, terminate
    @0
    D=M
    @i
    D=D-M
    @END
    D;JLE

    // dereference @address pointer and write -1 in dereferenced memory register. add 16 to @address pointer
    @address
    A=M
    M=-1
    
    @address
    D=M
    @32
    D=D+A

    @address
    M=D

    // increment i
    @i
    M=M+1

    @LOOP
    0;JMP

(END)
    @END
    0;JMP



















@SCREEN
M=-1

@32
D=A
@SCREEN
A=D+A
M=-1

