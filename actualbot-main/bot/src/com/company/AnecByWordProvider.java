package com.company;

import com.company.api.IAnecByWordProvider;
import com.company.api.IAnecdotesProvider;
import com.company.api.User;

import java.io.IOException;

public class AnecByWordProvider implements IAnecByWordProvider {
    public AnecByWordProvider(IAnecdotesProvider provider, String link){
        this.provider = provider;
        this.link = link;
    }
    IAnecdotesProvider provider;
    String link;
    public String findAnecdote(String quote, User user) throws IOException {
        if (!user.keyWordDict.containsKey(quote))
            user.keyWordDict.put(quote,0);
        else
            user.keyWordDict.put(quote,user.keyWordDict.get(quote)+1);
        var number = user.keyWordDict.get(quote);
        var anecdotes = provider.getListOfAnecdotes(link + quote);
        if (number == anecdotes.size())
                user.keyWordDict.put(quote, 0);
        return anecdotes.get(number);

    }
}
