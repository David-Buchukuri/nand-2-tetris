import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTable {
    public HashMap<String, Integer> symbolsToAddresses = new HashMap<String, Integer>();

    public SymbolTable(){
        this.symbolsToAddresses.put("@SP", 0);
        this.symbolsToAddresses.put("@LCL", 1);
        this.symbolsToAddresses.put("@ARG", 2);
        this.symbolsToAddresses.put("@THIS", 3);
        this.symbolsToAddresses.put("@THAT", 4);
        this.symbolsToAddresses.put("@R0", 0);
        this.symbolsToAddresses.put("@R1", 1);
        this.symbolsToAddresses.put("@R2", 2);
        this.symbolsToAddresses.put("@R3", 3);
        this.symbolsToAddresses.put("@R4", 4);
        this.symbolsToAddresses.put("@R5", 5);
        this.symbolsToAddresses.put("@R6", 6);
        this.symbolsToAddresses.put("@R7", 7);
        this.symbolsToAddresses.put("@R8", 8);
        this.symbolsToAddresses.put("@R9", 9);
        this.symbolsToAddresses.put("@R10", 10);
        this.symbolsToAddresses.put("@R11", 11);
        this.symbolsToAddresses.put("@R12", 12);
        this.symbolsToAddresses.put("@R13", 13);
        this.symbolsToAddresses.put("@R14", 14);
        this.symbolsToAddresses.put("@R15", 15);
        this.symbolsToAddresses.put("@SCREEN", 16384);
        this.symbolsToAddresses.put("@KBD", 24576);
    }

    public List<String> symbolsToAddresses(List<String> parsed){
        // without label declarations
        List<String> filteredCommands = this.removeLabelsAndSaveAddresses(parsed);

        this.variablesToBinaryAddresses(filteredCommands);
        return filteredCommands;
    }

    private List<String> removeLabelsAndSaveAddresses(List<String> commands){
        List<String> filteredCommands = new ArrayList<>();
        for(int i = 0; i < commands.size(); i++){
            String command = commands.get(i);
            if(
                !Parser.isLabel(command)
            ){
                filteredCommands.add(command);
                continue;
            }

            // remove braces from label, append @ to it and save it as a variable
            String labelInVariableFormat = "@" + command.substring(1, command.length() - 1);
            this.symbolsToAddresses.put(labelInVariableFormat, filteredCommands.size());
        }

        return filteredCommands;
    }

    private void variablesToBinaryAddresses(List<String> commands){
        int nextVariableAddress = 16;

        for(int i = 0; i < commands.size(); i++){
            String command = commands.get(i);

            if(!Parser.isVariable(command)){
                continue;
            }


            int address = 0;
            if(Parser.isSymbolicVariable(command)){
                if(
                    !this.symbolsToAddresses.containsKey(command)
                ){
                    this.symbolsToAddresses.put(command, nextVariableAddress);
                    nextVariableAddress++;
                }

                address = this.symbolsToAddresses.get(command);
            }else{
                address = Integer.parseInt(
                        command.substring(1)
                );
            }

            String binaryAddress = this.fifteenBitBinaryString(address);
            // adding temporary prefix @ so I can easily distinguish and skip A commands when processing C commands
            commands.set(i, "@0" + binaryAddress);
        }
    }

    private String fifteenBitBinaryString(int decimal){
        String binaryString = Integer.toBinaryString(decimal);

        if(binaryString.length() > 15){
            return binaryString.substring(
                binaryString.length() - 15
            );
        }

        int zerosToFill = 15 - binaryString.length();
        StringBuilder zeros = new StringBuilder();

        for(int i = 0; i < zerosToFill; i++){
            zeros.append(0);
        }

        zeros.append(binaryString);

        return zeros.toString();
    }

    public void removeTemporaryPrefixesFromACommands(List<String> commands){
        for(int i = 0; i < commands.size(); i++){
            if(
                commands.get(i).charAt(0) == '@'
            ){
                commands.set(
                    i,
                    commands.get(i).substring(1)
                );
            }
        }
    }
}
