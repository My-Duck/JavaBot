package com.company;

public class Answers {
    public static String GetAnswer(int number) {
        if (number < 4)
            return ("Извините, больше такого не смешного анекдота вы не увидите");
        if ((number >= 4) && (number <= 7))
            return ("Нейтрально...");
        else
            return ("Пойдет!!");
    }
}
