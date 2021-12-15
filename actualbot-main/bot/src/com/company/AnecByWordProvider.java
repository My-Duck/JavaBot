package com.company;

import com.company.api.IAnecByWordProvider;
import com.company.api.IProviderOfAnecdotes;
import com.company.api.User;

import java.util.logging.Logger;

public class AnecByWordProvider implements IAnecByWordProvider {
    public AnecByWordProvider(IProviderOfAnecdotes getter, String link){
        this.getter = getter;
        this.link = link;
    }
    IProviderOfAnecdotes getter;
    String link;
    public String findAnecdote(String quote, User user){
        if (!user.keyWordDict.containsKey(quote))
            user.keyWordDict.put(quote,0);
        else
            user.keyWordDict.put(quote,user.keyWordDict.get(quote)+1);
        var number = user.keyWordDict.get(quote);
        Logger log = Logger.getLogger("2");
        try {
            var anecdotes = getter.getListOfAnecdotes(link + quote);
            if (number == anecdotes.size())
                user.keyWordDict.put(quote, 0);
            return anecdotes.get(number);
        }
        catch (Exception exception) {
            log.info("Исключение с анекдотом по слову. Слово: : " + quote);
            return "Что-то пошло не так";
        }
    }
}
