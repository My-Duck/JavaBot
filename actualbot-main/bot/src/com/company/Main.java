package com.company;

import com.company.api.IAnecdotesProvider;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        String botName = System.getenv("BotName");
        String botToken = System.getenv("Token");
        IAnecdotesProvider BashProvider = new BashAnecProviderProvider();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot(botName, botToken,new BotLogic(
                new AnecByWordProvider(BashProvider,"https://bash.im/search?text="),
                new RandomAnecProvider(BashProvider,"https://bash.im/random"))));
    }
}
