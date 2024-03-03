// if ram[0] > 0: ram[1] = 1: else ram[1] = 0

@0
D=M

@POSITIVE
D;JGT

@1
M=0
@END
0;JMP

(POSITIVE)
    @1
    M=1
    @END
    0;JMP

(END)
    @END
    0;JMP