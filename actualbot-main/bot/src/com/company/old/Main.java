package com.company.old;

public class Main {
    public static void main(String[] args) {
        var bot = new SyncBot(new ConsoleMessageReader(), new ConsoleMessageSender());
        String userId = "console";
        while (true) {
            var message = bot.reader.readMessage();
            bot.sender.sendMessage(bot.handler.handleMessage(message, userId));
        }
    }
}
