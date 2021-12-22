package com.company.api;

import java.io.IOException;
import java.util.Hashtable;

public interface IBotLogic {
    String handleMessage(String message,String userID) throws IOException;
}
