package com.company;

import com.company.api.IGetterOfAnecdotesList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class BashAnecProvider implements IGetterOfAnecdotesList {
    @Override
    public ArrayList<String> getListOfAnecdotes(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        doc.select("br").append("\n");
        Elements elements = doc.getElementsByAttributeValue("class","quote__body");
        elements.remove(0);
        ArrayList<String> listOfJokes = new ArrayList<String>();
        for (Element element: elements){
            listOfJokes.add(Jsoup.clean(element.toString(), Whitelist.none()));
        }
        return listOfJokes;
    }
}
