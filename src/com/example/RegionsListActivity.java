package com.example;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * User: rok
 * Date: 15.3.12
 * Time: 23:17
 */
public class RegionsListActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter(this,
                R.layout.regions_list_item,
                getResources().getStringArray(R.array.all_regions)));
    }
}