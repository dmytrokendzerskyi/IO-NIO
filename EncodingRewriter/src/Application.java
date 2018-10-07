import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Application {

    private static final String FILE_PATH = "file.txt";

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(new File(FILE_PATH).toPath(), StandardCharsets.UTF_8, StandardOpenOption.WRITE);

        char[] chars = readFile(fileInputStream);
        writeFile(bufferedWriter,chars);

        fileInputStream.close();
        bufferedWriter.close();
    }

    public static char[] readFile(FileInputStream fileInputStream) throws Exception{
        char [] chars = new char[fileInputStream.available()];
        int b;
        int i = 0;
        while((b = fileInputStream.read()) != -1){
            chars[i] = (char) b;
            i++;
        }
        return chars;
    }

    public static void writeFile(BufferedWriter bufferedWriter, char [] chars) throws IOException{
        bufferedWriter.write(chars);
        bufferedWriter.flush();
    }

}
