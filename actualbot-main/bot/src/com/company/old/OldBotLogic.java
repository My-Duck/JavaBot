package com.company.old;

import com.company.Answers;
import com.company.api.IBotLogic;
import com.company.api.States;
import com.company.api.State;

import java.util.Hashtable;

public class OldBotLogic implements IBotLogic {
    private String answer;
    public Hashtable<String, State> stateContainer = new Hashtable<String, State>();
    public String handleMessage(String message, String userId) {
        if (!stateContainer.containsKey(userId)) {
            stateContainer.put(userId,new State());
        }
        var state = stateContainer.get(userId);
        if (state.currentState == States.start) {
            answer = OldMessagesFromBot.GetMessage(state.currentState);
            state.currentState = States.wait_for_key;
        } else if (state.currentState == States.wait_for_key) {
            answer = OldAnecdotes.GetAnecdote(Integer.parseInt(message));
            answer += "\n" + OldMessagesFromBot.GetMessage(state.currentState);
            state.currentState = States.wait_for_key;
        }else if (state.currentState == States.wait_for_key) {
            answer = Answers.GetAnswer(Integer.parseInt(message));
            answer += "\n" + OldMessagesFromBot.GetMessage(state.currentState);
            state.currentState = States.wait_for_key;
        }
        return answer;
    }
}

