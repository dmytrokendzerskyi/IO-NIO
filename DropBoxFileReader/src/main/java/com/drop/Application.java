package com.drop;

import com.drop.auth.Authorization;
import com.dropbox.core.v2.DbxClientV2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

public class Application {

    private static final String FILE_NAME = "textFile.txt";

    public static void main(String[] args) throws Exception {
        Authorization authorization = new Authorization();
        DbxClientV2 dbxClientV2 = authorization.authDropBoxUser();
        readFile(dbxClientV2);

    }

    public static void readFile(DbxClientV2 dbxClientV2) throws Exception {
        InputStream fileInputStream = dbxClientV2.files().download("/"+FILE_NAME).getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "X-JIS0208"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(FILE_NAME) , "X-JIS0208"));

        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
            bufferedWriter.write(line);
            bufferedWriter.flush();
        }

        bufferedReader.close();
        bufferedWriter.close();

    }
}
