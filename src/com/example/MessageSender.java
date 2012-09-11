package com.example;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * User: rok
 * Date: 8/15/12
 * Time: 10:44 AM
 */
public class MessageSender extends MessagesConnection {
    private static final String url = "http://logica.pro/write.php";
    public static final MessageSender INSTANCE = new MessageSender();

    private MessageSender() { }

    public boolean sendMessage(String author, String message, int longitude, int latitude) {
        Map<String, String> parameters = new HashMap<String, String>(4);
        try {
            parameters.put("author", encode(author));
        } catch (UnsupportedEncodingException ignored) { }
        parameters.put("message", message);
        parameters.put("longitude", String.valueOf(longitude));
        parameters.put("latitude", String.valueOf(latitude));
        String response = readFromServer(parameters, url);
        return "1".equals(response);
    }

    private String encode(String author) throws UnsupportedEncodingException {
        return URLEncoder.encode(author, "UTF-8");
    }
}
