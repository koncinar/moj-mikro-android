package com.example;

import android.os.AsyncTask;

import java.util.List;

/**
 * User: rok
 * Date: 8/15/12
 * Time: 2:02 PM
 */
public class AsyncMessagesLoader extends AsyncTask<Void, Void, List<Message>> {
    private MessagesLoadable messagesLoadable;

    public AsyncMessagesLoader(MessagesLoadable messagesLoadable) {
        this.messagesLoadable = messagesLoadable;
    }

    @Override
    protected List<Message> doInBackground(Void... voids) {
        return MessagesRetriever.INSTANCE.readMessages();
    }

    @Override
    protected void onPostExecute(List<Message> messages) {
        super.onPostExecute(messages);
        messagesLoadable.loadMessages(messages);
    }
}
