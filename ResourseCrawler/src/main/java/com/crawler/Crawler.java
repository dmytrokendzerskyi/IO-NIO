package com.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Crawler {

    private static final String element = "a";
    private static final String attribute = "href";

    public List<String> parseResource(String URL) throws IOException{
        Document document = Jsoup.connect(URL).get();

        Elements elements = document.select(element);

        return elements.stream().map(element -> element.attr(attribute)).collect(Collectors.toList());
    }
}
