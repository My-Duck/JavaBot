package com.company;

import com.company.api.IMessageReader;
import com.company.api.IMessageSender;

public class SyncBot {

    public IMessageSender sender;
    public IMessageReader reader;
    public BotLogic handler = new BotLogic();
    public SyncBot(IMessageReader reader, IMessageSender sender) {
        this.reader = reader;
        this.sender = sender;
    }
}


