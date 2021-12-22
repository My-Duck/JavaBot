package com.company;

import com.company.api.IBotLogic;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TelegramBot extends TelegramLongPollingBot {

    public IBotLogic logic;
    public String token;
    public String name;


    public TelegramBot(String name, String token, IBotLogic logic) {
        this.name = name;
        this.token = token;
        this.logic = logic;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }


    @SneakyThrows
    public void messageHandler(Message message) {
        if (message.hasText()) {
            String id = message.getChatId().toString();
            Logger log = Logger.getLogger("BotLogic");
            ReplyKeyboardMarkup keyboard = null;
            try {
                Answer answer = logic.handleMessage(message.getText(), id);
                if (answer.question)
                    keyboard = getKeyboard(new Keyboard());
                execute(
                        SendMessage.builder()
                                .text(answer.text)
                                .replyMarkup(keyboard)
                                .chatId(id)
                                .build());
            } catch (Exception e) {
                log.info("Исключение. Сообщение пользователя: " + message.getText());
                execute(
                        SendMessage.builder()
                                .text("Извините. Что-то пошло не так")
                                .chatId(id)
                                .build());
            }
        }
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            messageHandler(update.getMessage());
        }
    }


    public ReplyKeyboardMarkup getKeyboard(Keyboard keyboard){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(false);
        List<KeyboardRow> rowList = new ArrayList<>();
        for (List<String> list: keyboard.buttons) {
            KeyboardRow buttonRow = new KeyboardRow();
            for (String button: list) {
                buttonRow.add(button);
            }
            rowList.add(buttonRow);
        }
        replyKeyboardMarkup.setKeyboard(rowList);
        return replyKeyboardMarkup;
    }
}