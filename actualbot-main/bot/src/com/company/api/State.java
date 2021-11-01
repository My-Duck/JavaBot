package com.company.api;

public class State {
    public States currentState;

    public State(States state) {
        this.currentState = state;
    }

    public State() {
        this(States.start);
    }
}