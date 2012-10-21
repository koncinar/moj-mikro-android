package com.example;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

/**
 * User: rok
 * Date: 15.3.12
 * Time: 21:42
 */
public class MojMikroAndroidActivity extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moj_mikro_android);

        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("my_tab")
                .setIndicator(getString(R.string.my_info),
                        getResources().getDrawable(R.drawable.ic_tab_profile))
                .setContent(new Intent(this, MyActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("messages_tab")
                .setIndicator(getString(R.string.messages),
                        getResources().getDrawable(R.drawable.ic_tab_messages))
                .setContent(new Intent(this, MessagesListActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("map_tab")
                .setIndicator(getString(R.string.map),
                        getResources().getDrawable(R.drawable.ic_tab_map))
                .setContent(new Intent(this, MessagesMapActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("send_message_tab")
                .setIndicator(getString(R.string.send_message_text),
                        getResources().getDrawable(R.drawable.ic_tab_send))
                .setContent(new Intent(this, SendMessageActivity.class)));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                return true;
            case R.id.quit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refresh() {
        LocalActivityManager localActivityManager = getLocalActivityManager();
        Activity currentActivity = localActivityManager.getCurrentActivity();
        if (currentActivity instanceof MessagesListActivity) {
            ((MessagesListActivity) currentActivity).reloadMessages();
        } else if (currentActivity instanceof MessagesMapActivity) {
            ((MessagesMapActivity) currentActivity).reloadMarkers();
        }
    }

    public void switchTab(String tag) {
        TabHost tabHost = getTabHost();
        tabHost.setCurrentTabByTag(tag);
    }

    public void switchTab(String tag, Message selectedMessage) {
        switchTab(tag);
        Activity tabActivity = getLocalActivityManager().getActivity(tag);
        if (tabActivity instanceof MessageSelectable) {
            MessageSelectable activity = (MessageSelectable) tabActivity;
            activity.showSelectedMessage(selectedMessage);
        }
    }
}

