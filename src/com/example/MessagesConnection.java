package com.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: rok
 * Date: 8/15/12
 * Time: 10:33 AM
 */
public class MessagesConnection {
    protected String readFromServer(Map<String, String> parameters, String link) {
        try {
            //pošiljanje podatkov
            HttpPost post = new HttpPost(link);
            if (parameters != null && parameters.size() > 0) {
                post.setEntity(new UrlEncodedFormEntity(convertParameters(parameters)));
            }
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(post);

            //prejemanje podatkov
            HttpEntity entity = httpResponse.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }

    private List<NameValuePair> convertParameters(Map<String, String> parameters) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String name : parameters.keySet()) {
            String value = parameters.get(name);
            nameValuePairs.add(new BasicNameValuePair(name, value));
        }
        return nameValuePairs;
    }
}
