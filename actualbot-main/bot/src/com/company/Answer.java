package com.company;

import com.company.api.IAnswerContainer;
import com.company.api.State;
import com.company.api.States;


public class Answer implements IAnswerContainer {
    public String text;
    public Boolean question;
    public void prepareAnswer(States userState){
        question = userState != States.wait_for_key;
        }
}

