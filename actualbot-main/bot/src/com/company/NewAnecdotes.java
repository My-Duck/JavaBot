package com.company;
import lombok.SneakyThrows;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class NewAnecdotes {
    private static ArrayList<String> GetFromBash(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        doc.select("br").append("\\\n");
        Elements elements = doc.getElementsByAttributeValue("class","quote__body");
        elements.remove(0);
        ArrayList<String> listOfJokes = new ArrayList<String>();
        for (Element element: elements){
            listOfJokes.add(Jsoup.clean(element.toString(), Whitelist.none()));
        }
        return listOfJokes;
    }
    public static String GetRandomAnecdote(){
        return GetAnecdote("https://bash.im/");
    }
    public static String FindAnecdote(String quote){
        return GetAnecdote("https://bash.im/search?text=" + quote);
    }
    @SneakyThrows
    public static String GetAnecdote (String link){
        var anecdotes = GetFromBash(link);
        var random = (int)(Math.random() * (anecdotes.size()+1));
        return anecdotes.get(random);

    }

}
