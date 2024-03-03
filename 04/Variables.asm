// Value of ram[0] is written in ram[1]. Value of ram[1] is written in ram[0]

// save ram[0]'s value in ram[temp]
@0
D=M
@temp
M=D

// write in ram[0] ram[1]'s value
@1
D=M
@0
M=D

// write in ram[1] ram[temp]'s value
@temp
D=M
@1
M=D

// reset ram[temp] to 0
@temp
M=0

(END)
    @END
    0;JMP