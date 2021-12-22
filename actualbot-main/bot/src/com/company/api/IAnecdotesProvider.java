package com.company.api;

import java.io.IOException;
import java.util.ArrayList;

public interface IAnecdotesProvider {
    ArrayList<String> getListOfAnecdotes(String link) throws IOException;
}
