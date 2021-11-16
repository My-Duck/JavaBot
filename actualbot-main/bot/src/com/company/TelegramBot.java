package com.company;

import com.company.api.State;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Hashtable;
import java.util.Optional;

public class TelegramBot extends TelegramLongPollingBot {

    public NewBotLogic logic;
    public TelegramBot (NewBotLogic logic){
        this.logic = logic;
    }

    @Override
    public String getBotUsername() {
        return "@RCSJBot";
    }

    @Override
    public String getBotToken(){
        return "20";
    }


    @SneakyThrows
    public void messageHandler(Message message){
        if (message.hasText()) {
                String id = message.getChatId().toString();
                execute(
                        SendMessage.builder()
                                .text(logic.handleMessage(message.getText(),id))
                                .chatId(id)
                                .build());
        }
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
       if (update.hasMessage()){
           messageHandler(update.getMessage());
           }
       }
    }
