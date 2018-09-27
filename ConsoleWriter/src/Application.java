import java.io.*;
import java.util.Scanner;

public class Application {

    private static final String exit = "exit";
    private static final String filePath = "resources.txt";

    public static void main(String[] args) throws IOException{

        FileWriter fileWriter = new FileWriter(new File(filePath),true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        FileSaver fileSaver = new FileSaver(printWriter);

        Scanner scanner = new Scanner(System.in);
        String text = "";
        while(true){
            text = scanner.nextLine();
            if(text.equals(exit))
                break;

            fileSaver.saveInFile(text);

        }
        printWriter.close();

    }
}
