"""
 4-way multiplexor:
 out = a if sel == 00
       b if sel == 01
       c if sel == 10
       d if sel == 11
"""

def mux4Way(a, b, c, d, sel):
    if sel == 0:
        return a

    if sel == 1:
        return b
    
    if sel == 2:
        return c
    
    if sel == 3:
        return d
    

def getTestCaseString(a, b, c, d, sel):
    # set a 0,
    # set b 0,
    # set c 0,
    # set d 0,
    # set sel 0,
    # eval,
    # output;
                            
    testCase = []

    testCase.append(f"set a {a},\n")
    testCase.append(f"set b {b},\n")
    testCase.append(f"set c {c},\n")
    testCase.append(f"set d {d},\n")
    testCase.append(f"set sel {sel},\n")
    testCase.append(f"eval,\n")
    testCase.append(f"output;\n")

    return "".join(testCase)
        
        
  


# to represent 10 and 11 we write them in decimal
selVals = [0, 1, 2, 3]
aVals = [0, 1]
bVals = [0, 1]
cVals = [0, 1]
dVals = [0, 1]

decToBinMap = {
    0: "00",
    1: "01",
    2: "10",
    3: "11",
}


testCases = [

]


output = [
    "|   a   |   b   |   c   |   d   |  sel  |   out   |\n"
]

for sel in selVals:
    for a in aVals:
        for b in bVals:
            for c in cVals:
                for d in dVals:
                    res = mux4Way(a, b, c, d, sel)
                    line = f"|   {a}   |   {b}   |   {c}   |   {d}   |  {decToBinMap[sel]}   |    {res}    |\n"
                    output.append(line)


                    testCases.append(
                        getTestCaseString(a, b, c, d, sel)
                    )


print(
    "".join(output)
)

file = open("./Mux4Way.cmp", "w")
file.write("".join(output))
file.close()

file = open("./Mux4Way.tst", "w")

setupString="""
load Mux4Way.hdl,
output-file Mux4Way.out,
compare-to Mux4Way.cmp,
output-list a%B3.1.3 b%B3.1.3 c%B3.1.3 d%B3.1.3 sel%B2.2.3 out%B4.1.4;
"""

file.write(
    setupString + "\n".join(testCases)
)

file.close()



