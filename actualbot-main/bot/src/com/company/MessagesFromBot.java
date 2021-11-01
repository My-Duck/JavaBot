
package com.company;

import com.company.api.States;

public class MessagesFromBot {
    public static String GetMessage(States state) {
        return switch (state) {
            case start -> "Привет, я бот который знает 5 анекдотов, Введя число от 1 до 5 ты можешь его прочитать.";
            case anecdote -> "Оцените по 10-ти балльной шкале.";
            case score -> "Давай попробуем еще раз.";
            default -> throw new IllegalArgumentException("Unexpected value:" + state);
        };
        }
}

