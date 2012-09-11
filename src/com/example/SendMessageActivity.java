package com.example;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * User: rok
 * Date: 8/15/12
 * Time: 10:22 AM
 */
public class SendMessageActivity extends Activity implements LocationListener {
    private Location currentLocation;
    private LocationManager locationManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_message);
        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    private void sendData() {
       SharedPreferences pref = getSharedPreferences("mma_preferences", 0);
       String name = pref.getString("name", "");
       if (name == null || name.length() == 0) {
           Toast.makeText(this, getString(R.string.enter_name_first), Toast.LENGTH_SHORT).show();
           return;
       }
       TextView messageTextView = (TextView) findViewById(R.id.message_input);
       String message = messageTextView.getText().toString();
       if (currentLocation == null) {
           Toast.makeText(this, getString(R.string.waiting_for_gps), Toast.LENGTH_SHORT).show();
           return;
       }
       boolean success = MessageSender.INSTANCE.sendMessage(name, message, (int) (currentLocation.getLongitude() * 1000000), (int) (currentLocation.getLatitude() * 1000000));
       if (success) {
           Toast.makeText(this, getString(R.string.message_sent), Toast.LENGTH_SHORT).show();
       } else {
           Toast.makeText(this, getString(R.string.message_not_sent), Toast.LENGTH_SHORT).show();
       }
   }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) { }

    @Override
    public void onProviderEnabled(String s) { }

    @Override
    public void onProviderDisabled(String s) { }
}