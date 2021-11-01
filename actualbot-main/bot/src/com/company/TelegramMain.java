package com.company;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramMain extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "@RCSJBot";
    }

    @Override
    public String getBotToken(){
        return "2002771093:AAH8V5FggnFC6otGQRE4vM8J69Nqq2s7DB8";
    }

    @SneakyThrows
    public static void main(String[] args) {
        TelegramMain bot = new TelegramMain();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
       if (update.hasMessage()){
           Message message = update.getMessage();
           if (message.hasText()) {
               execute(SendMessage.builder().chatId(message.getChatId().toString())
                       .text("u sent: \n\n" + message.getText()).build());
           }
           }
       }
    }
