package com.company;

import com.company.api.IMessageReader;
import com.company.api.IMessageSender;

public class ConsoleMessageSender implements IMessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
