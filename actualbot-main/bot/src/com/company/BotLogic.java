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
            userContainer.put(userId, new User());
        }
        var user = userContainer.get(userId);
        if (user.state.currentState == States.start) {
            answer = MessagesFromBot.GetMessage(user.state.currentState);
            user.state.currentState = States.wait_for_choose_type;
        }
        else if (user.state.currentState == States.wait_for_choose_type) {
            if (userMessage.equals("анекдот")) {
                answer = Anecdotes.GetRandomAnecdote();
                answer +="\n\n"+MessagesFromBot.GetMessage(States.wait_for_continuation);
                user.state.currentState = States.wait_for_choose_type;
            } else if (userMessage.equals("слово")) {
                user.state.currentState = States.wait_for_key;
                answer = MessagesFromBot.GetMessage(user.state.currentState);
            } else answer = MessagesFromBot.GetMessage(user.state.currentState);
        }
        else if (user.state.currentState == States.wait_for_key) {
            answer = Anecdotes.FindAnecdote(userMessage, user);
            answer +="\n\n"+MessagesFromBot.GetMessage(States.wait_for_continuation);
            user.state.currentState = States.wait_for_choose_type;
        }
        return answer;
    }
}