package com.crawler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

public class FileSaver {

    private Crawler crawler;
    private BufferedWriter bufferedWriter;

    private static final String MAIN_URL = "MAIN URL: ";
    private static final String ATTRIBUTE_URL = "URL: ";
    private static final String SEPARATE_LINE = "\n";
    private static final String ERROR_MESSAGE = "Error Message: ";

    public FileSaver(Crawler crawler, BufferedWriter bufferedWriter) {
        this.crawler = crawler;
        this.bufferedWriter = bufferedWriter;
    }

    public void saveInFile(String URL) throws IOException{

        List<String> elements = crawler.parseResource(URL);

        bufferedWriter.write(MAIN_URL.concat(URL).concat(SEPARATE_LINE));
        StringJoiner urlJoiner = new StringJoiner(SEPARATE_LINE.concat(ATTRIBUTE_URL), ATTRIBUTE_URL, SEPARATE_LINE.concat(SEPARATE_LINE) );
        elements.forEach(href -> urlJoiner.add(href));
        bufferedWriter.write(urlJoiner.toString());

        bufferedWriter.flush();
    }

    public void saveErrorMessage(String URL , String errorMessage)throws IOException{
        bufferedWriter.write(MAIN_URL.concat(URL).concat(SEPARATE_LINE));
        bufferedWriter.write(ERROR_MESSAGE.concat(errorMessage).concat(SEPARATE_LINE));
        bufferedWriter.flush();
    }

}
