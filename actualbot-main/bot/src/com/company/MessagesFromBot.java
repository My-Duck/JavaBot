
package com.company;

import com.company.api.States;

public class MessagesFromBot {
    public static String getMessage(States state) {
        return switch (state) {
            case start -> "Привет. Ecли хочешь прочитать анекдот, напиши \"анекдот\", а если хочешь найти анекдот по ключевому слову, напиши \"слово\" (можно тыкать кнопочки)";
            case wait_for_choose_type -> "Я вас не понял";
            case wait_for_continuation -> "Давай попробуем еще раз. \"анекдот\" для рандомного, \"слово\" для поиска по ключевому поиску (можно тыкать кнопочки)";
            case wait_for_key -> "Введите ключевое слово";
            default -> throw new IllegalArgumentException("Unexpected value:" + state);
        };
    }
}

