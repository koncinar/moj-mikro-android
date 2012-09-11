package com.example;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
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
                .setIndicator(getString(R.string.my_info))
                .setContent(new Intent(this, MyActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("regions_tab")
                .setIndicator(getString(R.string.regions))
                .setContent(new Intent(this, RegionsListActivity.class)));
    }
}

