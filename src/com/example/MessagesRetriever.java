package com.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: rok
 * Date: 8/15/12
 * Time: 11:01 AM
 */
public class MessagesRetriever extends MessagesConnection {
    public static final MessagesRetriever INSTANCE = new MessagesRetriever();
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String url = "http://heart-designs.net/read.php";

    private MessagesRetriever() { }

    public List<Message> readMessages() {
        String response = readFromServer(null, url);
        if (response != null && response.length() > 0) {
            return parseResponse(response);
        } else {
            return new ArrayList<Message>();
        }
    }

    private List<Message> parseResponse(String objectsString) {
        List<Message> messages = new ArrayList<Message>();
        try {
            JSONArray jsonArray = new JSONArray(objectsString);
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject data = jsonArray.getJSONObject(i);
                    String author = data.getString("author");
                    String messageString = data.getString("message");
                    Date date = null;
                    date = DATE_FORMAT.parse(data.getString("date"));
                    int longitude = data.getInt("longitude");
                    int latitude = data.getInt("latitude");
                    Message message = new Message(author, date, latitude, longitude, messageString);
                    messages.add(message);
                } catch (ParseException ignored) { }
            }
        } catch (JSONException ignored) { }
        return messages;
    }
}
