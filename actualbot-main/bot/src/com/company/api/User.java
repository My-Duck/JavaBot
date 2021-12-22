package com.company.api;


import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class User {
    public State state;
    public HashMap<String,Integer> keyWordDict;
    public String previousChoice;
    public String previousQuote;
    public User(){
        state = new State();
        keyWordDict = new HashMap<String,Integer>();
        previousChoice = "ещё";
    }

}
