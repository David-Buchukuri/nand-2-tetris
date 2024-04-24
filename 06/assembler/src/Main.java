import java.util.List;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            throw new Exception("path must be passed as an argument");
        }

        try{
            List<String> parsedProgram = Parser.parse(args[0]);
            System.out.println(parsedProgram);

            SymbolTable symbolTable = new SymbolTable();
            List<String> commandsWithBinaryAddresses = symbolTable.symbolsToAddresses(parsedProgram);
            System.out.println(commandsWithBinaryAddresses);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}