package com.company;

import com.company.api.IBotLogic;
import com.company.api.State;
import com.company.api.States;

import java.util.Hashtable;

public class NewBotLogic implements IBotLogic {
    private String answer;
    public Hashtable<String, State> stateContainer = new Hashtable<String, State>();
    public String handleMessage(String message, String userId) {
        if (!stateContainer.containsKey(userId)) {
            stateContainer.put(userId,new State());
        }
        var state = stateContainer.get(userId);
        if (state.currentState == States.start) {
            answer = NewMessagesFromBot.GetMessage(state.currentState);
            state.currentState = States.anecdote;
        } else if (state.currentState == States.anecdote) {
            answer = NewAnecdotes.GetAnecdote();
            state.currentState = States.anecdote;
        }
        return answer;
    }
}

