import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashSet;

public class FileSaver {

    public void saveInJSONFormat(Writer writer, HashSet<String> csvLines) throws IOException{
        for (String text: csvLines) {
            writer.write(CsvToJSON(text));
            writer.flush();
        }
        writer.close();
    }

    public String CsvToJSON(String text){
        JSONObject jsonObject = new JSONObject();

        String [] textArray = text.split(",");
        for (int i = 0; i < textArray.length; i++) {
            jsonObject.put(CSVFileContentReader.propertyNames.get(i),textArray[i]);
        }

        return jsonObject.toString();
    }

    public void writebyBufferedWriter(BufferedWriter bufferedWriter, String csvLine , boolean firstLine) throws IOException {
        csvLine = CsvToJSON(csvLine);
        if(!firstLine)
        bufferedWriter.write(",");
        bufferedWriter.newLine();
        bufferedWriter.write(csvLine);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public void writebyByteBuffer(FileChannel channel, ByteBuffer byteBuffer, String csvLine , boolean firstLine) throws IOException {
        if(!firstLine)
        byteBuffer.put(",".getBytes());
        byteBuffer.put("\n".getBytes());
        byteBuffer.put(CsvToJSON(csvLine).getBytes());
        channel.write(byteBuffer);
        channel.force(true);
        channel.close();
    }



}
