package com.company;

import java.util.Hashtable;
import com.company.api.State;

public class Main {
    public static void main(String[] args) {
        var bot = new SyncBot(new ConsoleMessageReader(), new ConsoleMessageSender());
        Hashtable<String, State> stateContainer = new Hashtable<String, State>();;
        while (true) {
            var message = bot.reader.readMessage();
            String userId = "console";

            if (!stateContainer.containsKey(userId)) {
                stateContainer.put(userId,new State());
            }
            var state = stateContainer.get(userId);
            bot.sender.sendMessage(bot.handler.handleMessage(message, state));

        }
    }
}
