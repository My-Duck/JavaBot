package com.company;

import com.company.api.IBotLogic;
import com.company.api.State;
import com.company.api.States;
import com.company.api.User;

import java.util.Hashtable;

public class BotLogic implements IBotLogic {
    private String answer;
    public Hashtable<String, User> userContainer = new Hashtable<String, User>();
    public String handleMessage(String userMessage, String userId) {
        if (!userContainer.containsKey(userId)) {
            userContainer.put(userId,new User());
        }
        var user = userContainer.get(userId);
        if (user.state.currentState == States.start) {
            answer = MessagesFromBot.GetMessage(user.state.currentState);
            user.state.currentState = States.choose;
        }
        else if (user.state.currentState == States.choose){
            if (userMessage.equals("анекдот")) {
                answer = Anecdotes.GetRandomAnecdote();
                user.state.currentState = States.wait;
            }
            else if (userMessage.equals("слово")) {
                user.state.currentState = States.find;
                answer = MessagesFromBot.GetMessage(user.state.currentState);
            }
            else answer = MessagesFromBot.GetMessage(user.state.currentState);
        }
        else if (user.state.currentState == States.find) {
            answer = Anecdotes.FindAnecdote(userMessage,user);
            user.state.currentState = States.wait;
        }
        else if (user.state.currentState == States.wait) {
            answer = MessagesFromBot.GetMessage(user.state.currentState);
            user.state.currentState = States.choose;
        }
        return answer;
    }

}