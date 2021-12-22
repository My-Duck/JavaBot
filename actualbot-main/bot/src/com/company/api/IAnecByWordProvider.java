package com.company.api;

import java.io.IOException;

public interface IAnecByWordProvider {
    public String findAnecdote(String quote, User user) throws IOException;
}
