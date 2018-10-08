package com.drop;

import com.drop.auth.Authorization;
import com.dropbox.core.v2.DbxClientV2;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.*;

public class Application {

    private static final String FILE_NAME = "textFile.txt";
    private static final String PDF_FILE_NAME = "3_Теорія_пружності1-1.pdf";

    public static void main(String[] args) throws Exception {
        Authorization authorization = new Authorization();
        DbxClientV2 dbxClientV2 = authorization.authDropBoxUser();
        readFile(dbxClientV2);
        readPDFFile(dbxClientV2);

    }

    public static void readFile(DbxClientV2 dbxClientV2) throws Exception {
        InputStream fileInputStream = dbxClientV2.files().download("/"+FILE_NAME).getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "X-JIS0208"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(FILE_NAME) , "UTF-8"));

        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
            bufferedWriter.write(line);
            bufferedWriter.flush();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static void readPDFFile(DbxClientV2 dbxClientV2) throws Exception {
        InputStream fileInputStream = dbxClientV2.files().download("/"+PDF_FILE_NAME).getInputStream();
        PDDocument pdDocument = PDDocument.load(fileInputStream);
        if (!pdDocument.isEncrypted()) {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);

            PDFTextStripper tStripper = new PDFTextStripper();

            String pdfFileInText = tStripper.getText(pdDocument);

            // split by whitespace
            String lines[] = pdfFileInText.split("\\r?\\n");
            for (String line : lines) {
                System.out.println(line);
            }
        }
        fileInputStream.close();
    }

}
