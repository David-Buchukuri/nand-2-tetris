import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            throw new Exception("path must be passed as an argument");
        }

        try{
            List<String> parsedProgram = Parser.parse(args[0]);
            System.out.println(parsedProgram);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}