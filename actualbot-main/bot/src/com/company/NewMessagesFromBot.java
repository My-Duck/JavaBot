
package com.company;

import com.company.api.States;

public class NewMessagesFromBot {
    public static String GetMessage(States state) {
        return switch (state) {
            case start -> "Привет. Ecли хочешь прочитать анекдот, напиши анекдот";
            case anecdote -> "Давай попробуем еще раз";
            case choose -> "я вас не понял";
            case wait -> "Давай попробуем еще раз";
            case find -> "Введите ключевое слово";
            default -> throw new IllegalArgumentException("Unexpected value:" + state);
        };
        }
}

