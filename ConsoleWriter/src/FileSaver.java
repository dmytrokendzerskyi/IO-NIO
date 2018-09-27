import java.io.PrintWriter;

public class FileSaver {

    private PrintWriter printWriter;

    public FileSaver(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void saveInFile(String text){
        printWriter.println(text);
        printWriter.flush();
    }

}
