package com.example;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rok
 * Date: 15.3.12
 * Time: 23:17
 */
public class MessagesListActivity extends ListActivity {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                R.layout.regions_list_item,
                getMessages()));
        getListView().setBackgroundResource(R.drawable.main_bg);
    }

    private List<String> getMessages() {
        List<String> messages = new ArrayList<String>();
        for (Message message : MessagesRetriever.INSTANCE.readMessages()) {
            messages.add(MessageFormat.format("{0} - {1}\n{2}",
                    message.getAuthor(), DATE_FORMAT.format(message.getDate()), message.getMessage()));
        }
        return messages;
    }
}