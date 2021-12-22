package com.company;

import com.company.api.*;

import java.io.IOException;
import java.util.Hashtable;

public class BotLogic implements IBotLogic {
    public BotLogic(IAnecByWordProvider anecByWord, IRandomAnecProvider randomAnec){
        this.randomAnec = randomAnec;
        this.anecByWord = anecByWord;

    }
    private IRandomAnecProvider randomAnec;
    private IAnecByWordProvider anecByWord;
    private String answer;
    public Hashtable<String, User> userContainer = new Hashtable<String, User>();
    public String handleMessage(String userMessage, String userId) throws IOException {
        if (!userContainer.containsKey(userId)) {
            userContainer.put(userId, new User());
        }
        var user = userContainer.get(userId);
        if (user.state.currentState == States.start) {
            answer = MessagesFromBot.getMessage(user.state.currentState);
            user.state.currentState = States.wait_for_choose_type;
        }
        else if (user.state.currentState == States.wait_for_choose_type) {
            if (userMessage.equals("анекдот")) {
                answer = randomAnec.findAnecdote();
                answer +="\n\n"+MessagesFromBot.getMessage(States.wait_for_continuation);
                user.state.currentState = States.wait_for_choose_type;
            } else if (userMessage.equals("слово")) {
                user.state.currentState = States.wait_for_key;
                answer = MessagesFromBot.getMessage(user.state.currentState);
            } else answer = MessagesFromBot.getMessage(user.state.currentState);
        }
        else if (user.state.currentState == States.wait_for_key) {
            answer = anecByWord.findAnecdote(userMessage,user);
            answer +="\n\n"+MessagesFromBot.getMessage(States.wait_for_continuation);
            user.state.currentState = States.wait_for_choose_type;
        }
        return answer;
    }
}