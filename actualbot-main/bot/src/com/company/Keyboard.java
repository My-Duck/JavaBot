package com.company;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;


import java.util.ArrayList;
import java.util.List;

public class Keyboard {
    List<List<String>> buttons = new ArrayList<>();

    public Keyboard() {
        buttons.add(List.of("анекдот", "слово"));
        buttons.add(List.of("ещё"));
    }
}
