package com.company;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        String botName = System.getenv("BotName");
        String botToken = System.getenv("Token");
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot(botName, botToken,new BotLogic(
                new AnecByWordProvider(new BashAnecProvider(),"https://bash.im/search?text="),
                new RandomAnecProvider(new BashAnecProvider(),"https://bash.im/random"))));
    }
}
