// adds first 5 registers

@4
D=A
@i
M=D

(LOOP)
    // termination
    @i
    D=M
    @END
    D;JLT

    // add to sum
    @i
    A=M
    D=M
    @result
    M=M+D

    // decrement i
    @i
    M=M-1

    // jump back to the loop
    @LOOP
    0;JMP


(END)
    @END
    0;JMP







(END)
    @END
    0;JMP