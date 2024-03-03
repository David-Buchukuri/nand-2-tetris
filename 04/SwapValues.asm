// Value of ram[0] is written in ram[1]. Value of ram[1] is written in ram[0]

// save ram[0]'s value in ram[2]
@0
D=M
@2
M=D

// write in ram[0] ram[1]'s value
@1
D=M
@0
M=D

// write in ram[1] ram[0]'s value
@2
D=M
@1
M=D

// reset ram[2] to 0
@2
M=0

@14
0;JMP