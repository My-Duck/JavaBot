package com.company;
import lombok.SneakyThrows;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NewAnecdotes {
    private static ArrayList<String> GetFromBash() throws IOException {
        Document doc = Jsoup.connect("https://bash.im/").get();
        Elements elements = doc.getElementsByAttributeValue("class","quote__body");
        elements.remove(0);
        ArrayList<String> listOfJokes = new ArrayList<String>();
        for (Element element: elements){
            listOfJokes.add(Jsoup.clean(element.toString(), Whitelist.none().addTags("br")));
        }
        return listOfJokes;
    }
    @SneakyThrows
    public static String GetAnecdote (){
        var anecdotes = GetFromBash();
        var random = (int)(Math.random() * (anecdotes.size()+1));
        return anecdotes.get(random);

    }

}
