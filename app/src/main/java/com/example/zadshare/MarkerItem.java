package com.example.zadshare;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zadshare.models.Item;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MarkerItem implements ClusterItem {
    private LatLng position; // required field
    private String title; // required field
    private String snippet; // required field
    private Item place;
    private String image;

    public MarkerItem(LatLng position, String title, String snippet, Item place, String image) {
        this.position = position;
        this.title = title;
        this.snippet = snippet;
        this.place = place;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Item getPlace() {
        return place;
    }

    public void setPlace(Item place) {
        this.place = place;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public LatLng getPosition() {
        return position;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }
}
