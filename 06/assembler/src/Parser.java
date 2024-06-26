import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static List<String> parse(String path) throws FileNotFoundException {
        List<String> withoutComments = new ArrayList<String>();

        File file = new File(path);
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            int idx = line.indexOf("//");

            if(line.length() == 0 || idx == 0) {
                continue;
            }

            if(idx == -1 && line.trim().length() > 0){
                withoutComments.add(line.trim());
                continue;
            }

            String trimmed = line.substring(0, idx).trim();

            if(trimmed.length() > 0){
                withoutComments.add(trimmed);
            }
        }

        reader.close();
        return withoutComments;
    }

    public static boolean isLabel(String statement){
        return statement.charAt(0) == '(';
    }

    public static boolean isSymbolicVariable(String statement){
        if(statement.length() < 2){
            return false;
        }

        if(statement.charAt(0) != '@'){
            return false;
        };

        for(int i = 1; i < statement.length(); i++){
            if(
                !Character.isDigit(statement.charAt(i))
            ){
                return true;
            }
        }

        return false;
    }

    public static boolean isVariable(String statement){
        if(statement.charAt(0) == '@'){
            return true;
        };
        return false;
    }

    public static String getDest(String cCommand){
        String[] parts = cCommand.split("=");
        if(parts.length > 1){
            return parts[0];
        }
        return null;
    }

    public static String getComp(String cCommand){
        String[] parts = cCommand.split("=");

        String[] compAndJump = parts[parts.length - 1].split(";");

        return compAndJump[0];
    }

    public static String getJump(String cCommand){
        String[] parts = cCommand.split("=");

        String[] compAndJump = parts[parts.length - 1].split(";");

        if(compAndJump.length > 1){
            return compAndJump[1];
        }

        return null;
    }
}
