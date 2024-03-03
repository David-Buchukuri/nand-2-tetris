// compute sum of values from 1 to n (including n), n should be specified in ram[0] and it should be a positive number

// initializing
@i
M=1;

@result
M=0;

// moving i to D register and adding it to result
(LOOP_START)
    // add i to result
    @i
    D=M
    @result
    M=D+M


    // incrementing i
    @i
    M=M+1

    // loop
    @0
    D=M
    @i
    D=D-M
    @LOOP_START
    D;JGE

(END)
    @END
    0;JMP