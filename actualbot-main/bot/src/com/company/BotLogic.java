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
    public Hashtable<String, User> userContainer = new Hashtable<String, User>();
    public Answer handleMessage(String userMessage, String userId) throws IOException {
        Answer answer = new Answer();
        if (!userContainer.containsKey(userId)) {
            userContainer.put(userId, new User());
        }
        var user = userContainer.get(userId);
        if (user.state.currentState == States.start) {
            answer.text = MessagesFromBot.getMessage(user.state.currentState);
            user.state.currentState = States.wait_for_choose_type;
        }
        else if (user.state.currentState == States.wait_for_choose_type) {
            if (userMessage.equals("анекдот")) {
                answer.text = randomAnec.findAnecdote();
                answer.text +="\n\n"+MessagesFromBot.getMessage(States.wait_for_continuation);
                user.state.currentState = States.wait_for_choose_type;
            } else if (userMessage.equals("слово")) {
                user.state.currentState = States.wait_for_key;
                answer.text = MessagesFromBot.getMessage(user.state.currentState);
            } else answer.text = MessagesFromBot.getMessage(user.state.currentState);
        }
        else if (user.state.currentState == States.wait_for_key) {
            answer.text = anecByWord.findAnecdote(userMessage,user);
            answer.text +="\n\n"+MessagesFromBot.getMessage(States.wait_for_continuation);
            user.state.currentState = States.wait_for_choose_type;
        }
        answer.prepareAnswer(user.state.currentState);
        return answer;
    }
}