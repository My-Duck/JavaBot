
package com.company;

import com.company.api.States;

public class MessagesFromBot {
    public static String GetMessage(States state) {
        return switch (state) {
            case start -> "Привет. Ecли хочешь прочитать анекдот, напиши \"анекдот\", а если хочешь найти анекдот по ключевому слову напиши \"слово\"";
            case wait_for_choose_type -> "Я вас не понял";
            case wait_for_continuation -> "Давай попробуем еще раз. \"анекдот\" для рандомного, \"слово\" для поиска по ключевому поиску";
            case wait_for_key -> "Введите ключевое слово";
            default -> throw new IllegalArgumentException("Unexpected value:" + state);
        };
        }
}

