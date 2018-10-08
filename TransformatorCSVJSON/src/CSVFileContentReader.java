
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.stream.Stream;

public class CSVFileContentReader {

    public static List<String> propertyNames = new ArrayList<>();
    private static final char LINE_SEPARATOR = 13;
    private static final String RECORD_SEPARATOR = ",";

    public HashSet<String> readFileByBufferedReader(BufferedReader bufferedReader) throws IOException{
        HashSet<String> csvHashSet = new HashSet<>();

        // write first line
        Stream.of(bufferedReader.readLine().split(RECORD_SEPARATOR)).forEach(property ->propertyNames.add(property));

        String csvLine;
        while( (csvLine = bufferedReader.readLine()) != null ){
            csvHashSet.add(csvLine);
        }
        bufferedReader.close();
        return csvHashSet;
    }

    public String readFileByPartBufferedReader(BufferedReader bufferedReader, long skip) throws IOException{
        // write first line
        if(skip == 0)
        Stream.of(bufferedReader.readLine().split(RECORD_SEPARATOR)).forEach(property ->propertyNames.add(property));

        bufferedReader.skip(skip);
        String line = bufferedReader.readLine();

        bufferedReader.close();

        return line;
    }

    public String readFileProperties(BufferedReader bufferedReader) throws IOException{
        String line = bufferedReader.readLine();

        Stream.of(line.split(RECORD_SEPARATOR)).forEach(property ->propertyNames.add(property));
        bufferedReader.close();

        return line;
    }


    public String readFileWithBuffer(FileChannel channel, ByteBuffer byteBuffer , long skip ) throws IOException{
        channel.read(byteBuffer);
        StringBuilder stringBuilder = new StringBuilder();
        byteBuffer.position((int) skip);
        char b;

        while(( b = (char) byteBuffer.get() ) == LINE_SEPARATOR) {
            stringBuilder.append(b);
        }

        if(byteBuffer.get() == -1)
            return null;

        return stringBuilder.toString();
    }

}
