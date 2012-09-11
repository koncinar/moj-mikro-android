package com.example;

import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

public class MessagesMapActivity extends MapActivity {
    private DialogOverlay dialogOverlay;
    private MyLocationOverlay myLocationOverlay;
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

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
        for (Message message : MessagesRetriever.INSTANCE.readMessages()) {
            dialogOverlay.addItem(message.getLatitude(), message.getLongitude(),
                    MessageFormat.format("{0} - {1}", message.getAuthor(), DATE_FORMAT.format(message.getDate())),
                    message.getMessage());
        }
    }
}