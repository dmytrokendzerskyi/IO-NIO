package com.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Application {

    private static final String exit = "exit";
    private static final String filePath = "C:\\ResourseCrawler\\src\\main\\resources\\resources";

    public static void main(String[] args) throws IOException {

        Crawler crawler = new Crawler();
        FileWriter fileWriter = new FileWriter(new File(filePath),true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        FileSaver fileSaver = new FileSaver(crawler,bufferedWriter);

        Scanner scanner = new Scanner(System.in);

        String URL = "";

        while(true){
            URL = scanner.nextLine();
            if(URL.equals(exit))
                break;
            try {
                fileSaver.saveInFile(URL);
            }catch (Exception e){
                fileSaver.saveErrorMessage(URL,e.getMessage());
            }
        }
        bufferedWriter.close();

    }

}
