import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            throw new Exception("path must be passed as an argument");
        }
        try{
            List<String> parsedProgram = Parser.parse(args[0]);

            SymbolTable symbolTable = new SymbolTable();
            List<String> commandsWithBinaryAddresses = symbolTable.symbolsToAddresses(parsedProgram);

            Code code = new Code();
            code.translateCCommands(commandsWithBinaryAddresses);
            symbolTable.removeTemporaryPrefixesFromACommands(commandsWithBinaryAddresses);


            String[] pathParts = args[0].split("/");
            String fileName = pathParts[pathParts.length - 1].split("\\.")[0];
            pathParts[pathParts.length - 1] = fileName + ".hack";

            String outputPath = String.join("/", pathParts);
            FileWriter fileWriter = new FileWriter(outputPath);

            for(String command: commandsWithBinaryAddresses){
                fileWriter.write(command + '\n');
            }

            fileWriter.close();
            System.out.println("assembled successfully");
            System.out.println(outputPath);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}