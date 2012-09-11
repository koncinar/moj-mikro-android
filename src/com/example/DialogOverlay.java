package com.example;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;
import java.util.List;

public class DialogOverlay extends ItemizedOverlay<OverlayItem> {
    private Context context;
    private List<OverlayItem> items;

    public DialogOverlay(Drawable drawable, Context context) {
        super(boundCenterBottom(drawable));
        this.context = context;
        items = new ArrayList<OverlayItem>();
    }

    @Override
    protected OverlayItem createItem(int i) {
        return items.get(i);
    }

    @Override
    public int size() {
        return items.size();
    }

    public void addItem(int latitude, int longitude, String title, String message) {
        items.add(new OverlayItem(new GeoPoint(latitude, longitude), title, message));
        populate();
    }

    public void clear() {
        items.clear();
        populate();
    }

    @Override
    protected boolean onTap(int i) {
        OverlayItem tappedItem = createItem(i);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(tappedItem.getTitle());
        dialog.setMessage(tappedItem.getSnippet());
        dialog.setNeutralButton("Zapri", null);
        dialog.show();
        return true;
    }
}

