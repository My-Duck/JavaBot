
package com.company.old;

import com.company.api.States;

public class OldMessagesFromBot {
    public static String GetMessage(States state) {
        return switch (state) {
            case start -> "Привет, я бот который знает 5 анекдотов, Введя число от 1 до 5 ты можешь его прочитать.";
            case wait_for_choose_type -> "Оцените по 10-ти балльной шкале.";
            case wait_for_continuation -> "Давай попробуем еще раз.";
            default -> throw new IllegalArgumentException("Unexpected value:" + state);
        };
        }
}

