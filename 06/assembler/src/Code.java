import java.util.HashMap;
import java.util.List;

public class Code {
    public HashMap<String, String> destToBinary = new HashMap<String, String>();
    public HashMap<String, String> compToBinary = new HashMap<String, String>();
    public HashMap<String, String> jumpToBinary = new HashMap<String, String>();

    //    from idx 3 to 6
    //    must be 011 and i have 001, command M=1;

    public Code(){
        this.destToBinary.put(null, "000");
        this.jumpToBinary.put(null, "000");

        this.destToBinary.put("null", "000");

        this.destToBinary.put("M", "001");
        this.destToBinary.put("D", "010");
        this.destToBinary.put("A", "100");

        this.destToBinary.put("MD", "011");
        this.destToBinary.put("AD", "110");
        this.destToBinary.put("AM", "101");

        this.destToBinary.put("AMD", "111");

        this.compToBinary.put("0", "0101010");
        this.compToBinary.put("1", "0111111");
        this.compToBinary.put("-1", "0111010");
        this.compToBinary.put("D", "0001100");
        this.compToBinary.put("A", "0110000");
        this.compToBinary.put("M", "1110000");
        this.compToBinary.put("!D", "0001101");
        this.compToBinary.put("!A", "0110001");
        this.compToBinary.put("!M", "1110001");
        this.compToBinary.put("-D", "0001111");
        this.compToBinary.put("-A", "0110011");
        this.compToBinary.put("-M", "1110011");

        this.compToBinary.put("D+1", "0011111");
        this.compToBinary.put("A+1", "0110111");
        this.compToBinary.put("M+1", "1110111");
        this.compToBinary.put("D-1", "0001110");
        this.compToBinary.put("A-1", "0110010");
        this.compToBinary.put("M-1", "1110010");
        this.compToBinary.put("D+A", "0000010");
        this.compToBinary.put("A+D", "0000010");
        this.compToBinary.put("D+M", "1000010");
        this.compToBinary.put("M+D", "1000010");
        this.compToBinary.put("D-A", "0010011");
        this.compToBinary.put("D-M", "1010011");
        this.compToBinary.put("A-D", "0000111");
        this.compToBinary.put("M-D", "1000111");

        this.compToBinary.put("D&A", "0000000");
        this.compToBinary.put("D&M", "1000000");
        this.compToBinary.put("D|A", "0010101");
        this.compToBinary.put("D|M", "1010101");


        this.jumpToBinary.put("null", "000");
        this.jumpToBinary.put("JGT", "001");
        this.jumpToBinary.put("JEQ", "010");
        this.jumpToBinary.put("JGE", "011");
        this.jumpToBinary.put("JLT", "100");
        this.jumpToBinary.put("JNE", "101");
        this.jumpToBinary.put("JLE", "110");
        this.jumpToBinary.put("JMP", "111");
    }


    public void translateCCommands(List<String> commands) throws Exception{

        for(int i = 0; i < commands.size(); i++){
            String command = commands.get(i);
            if(

                command.charAt(0) == '@'
            ){
                continue;
            }

            String dest = Parser.getDest(command);
            String comp = Parser.getComp(command);
            String jump = Parser.getJump(command);

            if(
                !destToBinary.containsKey(dest)
            ){
                throw new Exception("unknown keyword " + dest + "in " + command);
            }

            if(
                !compToBinary.containsKey(comp)
            ){
                throw new Exception("unknown keyword " + comp + "in " + command);
            }

            if(
                !jumpToBinary.containsKey(jump)
            ){
                throw new Exception("unknown keyword " + jump + "in " + command);
            }

            StringBuilder binaryCommand = new StringBuilder();
            binaryCommand.append("111");
            binaryCommand.append(this.compToBinary.get(comp));
            binaryCommand.append(this.destToBinary.get(dest));
            binaryCommand.append(this.jumpToBinary.get(jump));

            commands.set(i, binaryCommand.toString());
        }
    }
}
