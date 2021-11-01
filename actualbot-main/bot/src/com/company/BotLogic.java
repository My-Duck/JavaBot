package com.company;

import com.company.api.States;
import com.company.api.State;

public class BotLogic {
    private String answer;
    public String handleMessage(String message, State state) {
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
            state.currentState = States.start;
        }
        return answer;
    }
}

