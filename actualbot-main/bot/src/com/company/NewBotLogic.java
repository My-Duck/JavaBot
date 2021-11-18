package com.company;

import com.company.api.IBotLogic;
import com.company.api.State;
import com.company.api.States;

import java.util.Hashtable;

public class NewBotLogic implements IBotLogic {
    private String answer;
    public Hashtable<String, State> stateContainer = new Hashtable<String, State>();
    public String handleMessage(String userMessage, String userId) {
        if (!stateContainer.containsKey(userId)) {
            stateContainer.put(userId,new State());
        }
        var state = stateContainer.get(userId);
        if (state.currentState == States.start) {
            answer = NewMessagesFromBot.GetMessage(state.currentState);
            state.currentState = States.choose;
        }
        else if (state.currentState == States.choose){
            if (userMessage == "1") {
                answer = NewAnecdotes.GetRandomAnecdote();
                state.currentState = States.wait;
            }
            else if (userMessage == "2") {
                state.currentState = States.find;
                answer = NewMessagesFromBot.GetMessage(state.currentState);
            }
            else answer = NewMessagesFromBot.GetMessage(state.currentState);
        }
        else if (state.currentState == States.find) {
            answer = NewAnecdotes.FindAnecdote(userMessage);
            state.currentState = States.wait;
        }
        else if (state.currentState == States.wait) {
            answer = NewMessagesFromBot.GetMessage(state.currentState);
            state.currentState = States.choose;
        }
        return answer;
    }

}