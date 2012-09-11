package com.example;

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
        setListAdapter(new ArrayAdapter<String>(this,
                R.layout.regions_list_item,
                getResources().getStringArray(R.array.all_regions)));
        getListView().setBackgroundResource(R.drawable.main_bg);
    }
}