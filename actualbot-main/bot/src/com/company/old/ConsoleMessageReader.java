package com.company.old;

import com.company.api.IMessageReader;

import java.util.Scanner;

public class ConsoleMessageReader implements IMessageReader {
    @Override
    public String readMessage() {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        return message;
    }
}
