package com.company.old;

import com.company.api.IMessageReader;
import com.company.api.IMessageSender;
import com.company.old.OldBotLogic;

public class SyncBot {

    public IMessageSender sender;
    public IMessageReader reader;
    public OldBotLogic handler = new OldBotLogic();
    public SyncBot(IMessageReader reader, IMessageSender sender) {
        this.reader = reader;
        this.sender = sender;
    }
}


