import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    private static final String filePath = "resources.txt";
    private static Scanner scannerText = new Scanner(System.in);
    private static Scanner scannerInt = new Scanner(System.in);
    private static int CountOfThreads = 3;

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath,"r" );

        List<FileSaver> fileSavers = new ArrayList<>();
        int position = 0;

        for (int i = 0; i < CountOfThreads; i++) {
            position = getPosition();
            fileSavers.add(new FileSaver(new RandomAccessFile(filePath,"rw"),position,getText()));
        }

        fileSavers.forEach(fileSaver -> executorService.submit(fileSaver));

        executorService.shutdown();

        new FileContentViewer(randomAccessFile).readContent();
    }


    public static String getText(){
        System.out.println("Enter text");
        return scannerText.nextLine();
    }

    public static int getPosition(){
        System.out.println("Enter the position");
        return scannerInt.nextInt();
    }



}
