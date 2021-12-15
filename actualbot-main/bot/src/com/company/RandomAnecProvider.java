package com.company;

import com.company.api.IProviderOfAnecdotes;
import com.company.api.IRandomAnecProvider;

import java.util.logging.Logger;

public class RandomAnecProvider implements IRandomAnecProvider {
    public RandomAnecProvider(IProviderOfAnecdotes getter, String link){
        this.getter = getter;
        this.link = link;
    }
    IProviderOfAnecdotes getter;
    String link;
    public String findAnecdote(){
        Logger log = Logger.getLogger("1");
        try {
            var anecdotes = getter.getListOfAnecdotes(link);
            var random = (int)(Math.random() * (anecdotes.size()+1));
            return anecdotes.get(random);
        }
        catch (Exception exception){
            log.info("Исключение в случайном анекдоте" );
            return "Что-то пошло не так";
        }
    }
}
