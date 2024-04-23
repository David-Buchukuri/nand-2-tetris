import java.util.HashMap;
import java.util.List;

public class SymbolTable {
    public HashMap<String, Integer> symbolsToAddresses = new HashMap<String, Integer>();

    public SymbolTable(List<String> parsedProgram){
        this.symbolsToAddresses.put("SP", 0);
        this.symbolsToAddresses.put("LCL", 1);
        this.symbolsToAddresses.put("ARG", 2);
        this.symbolsToAddresses.put("THIS", 3);
        this.symbolsToAddresses.put("THAT", 4);
        this.symbolsToAddresses.put("R0", 0);
        this.symbolsToAddresses.put("R1", 1);
        this.symbolsToAddresses.put("R2", 2);
        this.symbolsToAddresses.put("R3", 3);
        this.symbolsToAddresses.put("R4", 4);
        this.symbolsToAddresses.put("R5", 5);
        this.symbolsToAddresses.put("R6", 6);
        this.symbolsToAddresses.put("R7", 7);
        this.symbolsToAddresses.put("R8", 8);
        this.symbolsToAddresses.put("R9", 9);
        this.symbolsToAddresses.put("R10", 10);
        this.symbolsToAddresses.put("R11", 11);
        this.symbolsToAddresses.put("R12", 12);
        this.symbolsToAddresses.put("R13", 13);
        this.symbolsToAddresses.put("R14", 14);
        this.symbolsToAddresses.put("R15", 15);
        this.symbolsToAddresses.put("SCREEN", 16384);
        this.symbolsToAddresses.put("KBD", 24576);

        this.symbolsToAddresses(parsedProgram);
    }

    private void symbolsToAddresses(List<String> parsed){
        for(int i = 0; i < parsed.size(); i++){
            String command = parsed.get(i);
            if(
                !Parser.isLabel(command)
            ){
                continue;
            }

            // remove braces from label, append @ to it and save it as a variable
            String labelInVariableFormat = "@" + command.substring(1, command.length() - 1);
            this.symbolsToAddresses.put(labelInVariableFormat, i);
        }

        int nextVariableAddress = 16;

        for(int i = 0; i < parsed.size(); i++){
            String command = parsed.get(i);

            if(!Parser.isSymbolicVariable(command)){
                continue;
            }

            if(!this.symbolsToAddresses.containsKey(command)){
                this.symbolsToAddresses.put(command, nextVariableAddress);
                nextVariableAddress++;
            }
        }
    }
}
