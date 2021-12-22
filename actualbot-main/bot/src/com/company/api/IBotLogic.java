package com.company.api;

import com.company.Answer;

import java.io.IOException;

public interface IBotLogic {
    Answer handleMessage(String message, String userID) throws IOException;
}
