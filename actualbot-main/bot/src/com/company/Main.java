package com.company;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        String botName = System.getenv("BotName");
        String botToken = System.getenv("Token");
        String AdminToken = System.getenv("adminId");
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot(botName, botToken,AdminToken,new BotLogic(
                new BashAnecProvider(),"https://bash.im/random","https://bash.im/search?text=")));
    }
}
