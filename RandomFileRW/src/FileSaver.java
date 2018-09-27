import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

public class FileSaver implements Callable {

    private RandomAccessFile randomAccessFile;
    private int position;
    private String data;

    public FileSaver(RandomAccessFile randomAccessFile, int position, String data) {
        this.randomAccessFile = randomAccessFile;
        this.position = position;
        this.data = data;
    }

    @Override
    public Object call() throws Exception {
        randomAccessFile.seek(position);
        randomAccessFile.writeUTF(data);
        return null;
    }
}
