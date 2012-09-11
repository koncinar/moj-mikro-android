package com.example;

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
        tabHost.addTab(tabHost.newTabSpec("regions_tab")
                .setIndicator(getString(R.string.regions),
                        getResources().getDrawable(R.drawable.ic_tab_region))
                .setContent(new Intent(this, RegionsListActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("map_tab")
                .setIndicator(getString(R.string.map),
                        getResources().getDrawable(R.drawable.ic_tab_map))
                .setContent(new Intent(this, MessagesMapActivity.class)));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

