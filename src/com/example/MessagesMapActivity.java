package com.example;

import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MessagesMapActivity extends MapActivity {
    private DialogOverlay dialogOverlay;
    private MyLocationOverlay myLocationOverlay;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_map);
        MapView mapView = (MapView) findViewById(R.id.messages_map);
        mapView.setBuiltInZoomControls(true);
        dialogOverlay = new DialogOverlay(getResources().getDrawable(R.drawable.ic_map_marker), this);
        mapView.getOverlays().add(dialogOverlay);
        reloadMarkers();
        myLocationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(myLocationOverlay);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myLocationOverlay.enableMyLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myLocationOverlay.disableMyLocation();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    public void reloadMarkers() {
        dialogOverlay.clear();
        dialogOverlay.addItem(46378883, 13834922, "Juhuuuu", "Jaz sem najvišji");
        dialogOverlay.addItem(46120200, 14815438, "Center", "Jaz sem pa najbolj na sredini");
    }
}