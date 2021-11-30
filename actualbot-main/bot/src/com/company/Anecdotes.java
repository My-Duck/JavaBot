package com.company;
import com.company.api.User;
import lombok.SneakyThrows;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Anecdotes {
    private static ArrayList<String> GetFromBash(String link) throws IOException {
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
    @SneakyThrows
    public static String GetRandomAnecdote(){
        var anecdotes = GetFromBash("https://bash.im/random");
        var random = (int)(Math.random() * (anecdotes.size()+1));
        return anecdotes.get(random);
    }
    @SneakyThrows
    public static String FindAnecdote(String quote, User user){
        if (!user.keyWordDict.containsKey(quote))
            user.keyWordDict.put(quote,0);
        else
            user.keyWordDict.put(quote,user.keyWordDict.get(quote)+1);
        var number = user.keyWordDict.get(quote);
        var anecdotes = GetFromBash("https://bash.im/search?text=" + quote);
        if (number == anecdotes.size())
            user.keyWordDict.put(quote,0);
        return anecdotes.get(number);
    }

}
