import java.io.IOException;
import java.io.RandomAccessFile;

public class FileContentViewer {

    private RandomAccessFile randomAccessFile;

    public FileContentViewer(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;
    }

    public void readContent() throws IOException{
        readLines();
        readIntegers();
        readUTFStr();
    }

    public void readLines() throws IOException{
        randomAccessFile.seek(0);
        long nanoTime = System.nanoTime();
        String result;
        System.out.println("Read content with using readLine() method");
        while( (result = randomAccessFile.readLine()) != null){
            System.out.println(result);
        }
        System.out.println("Time: "+ (System.nanoTime() - nanoTime));
    }

    public void readIntegers() throws IOException{
        randomAccessFile.seek(0);
        long nanoTime = System.nanoTime();
        int i;
        System.out.println("Read content with using read() method");
        while( (i = randomAccessFile.read()) != -1){
            System.out.print((char) i);
        }
        System.out.println();
        System.out.println("Time: "+ (System.nanoTime() - nanoTime));
    }

    public void readUTFStr() throws IOException{
        randomAccessFile.seek(0);
        long nanoTime = System.nanoTime();
        System.out.println("Read content with using readUTF() method");
        System.out.println(randomAccessFile.readUTF());
        System.out.println("Time: "+ (System.nanoTime() - nanoTime));
    }

}
