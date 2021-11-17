package com.company;

import com.company.api.IBotLogic;
import com.company.api.States;
import com.company.api.State;

import java.util.Hashtable;

public class BotLogic implements IBotLogic {
    private String answer;
    public Hashtable<String, State> stateContainer = new Hashtable<String, State>();
    public String handleMessage(String message, String userId) {
        if (!stateContainer.containsKey(userId)) {
            stateContainer.put(userId,new State());
        }
        var state = stateContainer.get(userId);
        if (state.currentState == States.start) {
            answer = MessagesFromBot.GetMessage(state.currentState);
            state.currentState = States.anecdote;
        } else if (state.currentState == States.anecdote) {
            answer = Anecdotes.GetAnecdote(Integer.parseInt(message));
            answer += "\n" + MessagesFromBot.GetMessage(state.currentState);
            state.currentState = States.score;
        }else if (state.currentState == States.score) {
            answer = Answers.GetAnswer(Integer.parseInt(message));
            answer += "\n" + MessagesFromBot.GetMessage(state.currentState);
            state.currentState = States.anecdote;
        }
        return answer;
    }
}

