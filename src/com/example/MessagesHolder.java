package com.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MessagesHolder {
    private static final DateFormat format = new SimpleDateFormat("d. M. yyyy H:m");
    private List<Message> messages = new ArrayList<Message>();
    public static MessagesHolder INSTANCE = new MessagesHolder();

    private MessagesHolder() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void readMessages() {
        messages = new ArrayList<Message>();
        try {
            messages.add(new Message("koncinar", format.parse("15. 5. 2012 22:42"), 46000000, 15000000, "Lep prvi pozdrav!"));
            messages.add(new Message("koncinar 2", format.parse("15. 5. 2012 22:43"), 46010000, 15001000, "Lep drugi pozdrav!"));
            messages.add(new Message("koncinar 3", format.parse("15. 5. 2012 22:55"), 46015000, 15000500, "ola"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}