// if ram[0] > 0: ram[1] = 1: else ram[1] = 0

@0
D=M

@8
D;JGT

@1
M=0
@6
0;JMP

@1
M=1
@10
0;JMP