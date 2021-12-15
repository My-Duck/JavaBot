package com.company;
import com.company.api.IProviderOfAnecdotes;
import com.company.api.User;

import java.util.logging.Logger;


public class Anecdotes {
    public static String GetRandomAnecdote(IProviderOfAnecdotes getter, String link){
        Logger log = Logger.getLogger("1");
        try {
            var anecdotes = getter.getListOfAnecdotes(link);
            var random = (int)(Math.random() * (anecdotes.size()+1));
            return anecdotes.get(random);

        }
        catch (Exception exception)
        {
            log.info("ошибка с пользовательским сообщением : анекдот" );
            return "Что-то пошло не так";
        }
    }

    public static String FindAnecdote(String quote, User user, IProviderOfAnecdotes getter, String link){
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
            log.info("ошибка с пользовательским сообщением : " + quote);
            return "Что-то пошло не так";

        }
    }

}
