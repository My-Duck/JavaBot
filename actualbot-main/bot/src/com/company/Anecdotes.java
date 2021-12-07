package com.company;
import com.company.api.IGetterOfAnecdotesList;
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
    @SneakyThrows
    public static String GetRandomAnecdote(IGetterOfAnecdotesList getter,String link){
        var anecdotes = getter.getListOfAnecdotes(link);
        var random = (int)(Math.random() * (anecdotes.size()+1));
        return anecdotes.get(random);
    }
    @SneakyThrows
    public static String FindAnecdote(String quote, User user,IGetterOfAnecdotesList getter,String link){
        if (!user.keyWordDict.containsKey(quote))
            user.keyWordDict.put(quote,0);
        else
            user.keyWordDict.put(quote,user.keyWordDict.get(quote)+1);
        var number = user.keyWordDict.get(quote);
        var anecdotes = getter.getListOfAnecdotes(link + quote);
        if (number == anecdotes.size())
            user.keyWordDict.put(quote,0);
        return anecdotes.get(number);
    }

}
