import java.io.*;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.HashSet;

public class Application {

    public static final String FILE_PATH = "1500000 Sales Records\\1500000 Sales Records.csv";
    public static final String FILE_OUTPUT_PATH_IO = "1500000 Sales Records\\1500000 Sales Records_IO.json";
    public static final String FILE_OUTPUT_PATH_NIO = "1500000 Sales Records\\1500000 Sales Records_NIO.json";
    public static int size = 1024;

    public static void main(String[] args) throws URISyntaxException , IOException{
        File file = new File(FILE_PATH);

        double fileSize = file.length()/(Math.pow(size,2));
        double freeJVMMemory = Runtime.getRuntime().freeMemory()/(Math.pow(size,2));
        chooseReaderMethod(file , fileSize,freeJVMMemory);
    }

    public static void chooseReaderMethod(File file, double fileSize , double freeJVMMemory) throws IOException{
        FileSaver fileSaver = new FileSaver();
        CSVFileContentReader csvFileContentReader = new CSVFileContentReader();
        if(freeJVMMemory > fileSize){
            HashSet<String> csvLines = csvFileContentReader.readFileByBufferedReader(Files.newBufferedReader(Paths.get(file.getAbsolutePath())));
            fileSaver.saveInJSONFormat(Files.newBufferedWriter(new File(FILE_OUTPUT_PATH_IO).toPath(), StandardOpenOption.APPEND), csvLines);
        } else {
            boolean firstLine = true;
            String csvLine = csvFileContentReader.readFileProperties(Files.newBufferedReader(Paths.get(file.getAbsolutePath())));
            long skip = csvLine.getBytes().length+2;
            long skipDefault = skip;
            long time = System.nanoTime();
            while ((csvLine = csvFileContentReader.readFileByPartBufferedReader(Files.newBufferedReader(Paths.get(file.getAbsolutePath())),skip)) != null){
                fileSaver.writebyBufferedWriter(Files.newBufferedWriter(new File(FILE_OUTPUT_PATH_IO).toPath(), StandardOpenOption.APPEND),csvLine,firstLine);
                skip += csvLine.getBytes().length+2;
                firstLine = false;
            }
            System.out.println("Transforming with using IO: "+(System.nanoTime() - time));
            firstLine = true;
            skip = skipDefault;
            time = System.nanoTime();
            while ((csvLine = csvFileContentReader.readFileWithBuffer(FileChannel.open(file.toPath(), EnumSet.of(StandardOpenOption.READ)), ByteBuffer.allocate(size) ,skip)) != null){
                fileSaver.writebyByteBuffer(FileChannel.open(new File(FILE_OUTPUT_PATH_NIO).toPath(), EnumSet.of(StandardOpenOption.APPEND)), ByteBuffer.allocate(size),csvLine,firstLine);
                skip += csvLine.getBytes().length+2;
                firstLine = false;
            }
            System.out.println("Transforming with using NIO: "+(System.nanoTime() - time));
        }
    }



}
