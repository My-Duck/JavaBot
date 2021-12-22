package com.company;

import com.company.api.IAnecdotesProvider;
import com.company.api.IRandomAnecProvider;

import java.io.IOException;


public class RandomAnecProvider implements IRandomAnecProvider {
    public RandomAnecProvider(IAnecdotesProvider getter, String link){
        this.getter = getter;
        this.link = link;
    }
    IAnecdotesProvider getter;
    String link;
    public String findAnecdote() throws IOException {
        var anecdotes = getter.getListOfAnecdotes(link);
        var random = (int)(Math.random() * (anecdotes.size()+1));
        return anecdotes.get(random);
    }
}
