package com.example.zadshare.models;

public class Item {
    String restaurantid;
    String restaurantname;
    String restaurantlogourl;
    String restaurantlat;
    String restaurantlng;
    String restaurantservicetype;
    String restaurantaddres;
    String restaurantmob;
    String ratting;
    String favorittring;
    String restaurantimageurl;
    String restaurantdelivery;
    String restaurantdeliverytime;
    String restaurantdistance;
    Item(){
    }

    public Item(String restaurantid, String restaurantname, String restaurantlogourl, String restaurantlat, String restaurantlng, String restaurantservicetype, String restaurantaddres, String restaurantmob, String ratting, String favorittring, String restaurantimageurl, String restaurantdelivery, String restaurantdeliverytime, String restaurantdistance) {
        this.restaurantid = restaurantid;
        this.restaurantname = restaurantname;
        this.restaurantlogourl = restaurantlogourl;
        this.restaurantlat = restaurantlat;
        this.restaurantlng = restaurantlng;
        this.restaurantservicetype = restaurantservicetype;
        this.restaurantaddres = restaurantaddres;
        this.restaurantmob = restaurantmob;
        this.ratting = ratting;
        this.favorittring = favorittring;
        this.restaurantimageurl = restaurantimageurl;
        this.restaurantdelivery = restaurantdelivery;
        this.restaurantdeliverytime = restaurantdeliverytime;
        this.restaurantdistance = restaurantdistance;
    }

    public String getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(String restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getRestaurantlogourl() {
        return restaurantlogourl;
    }

    public void setRestaurantlogourl(String restaurantlogourl) {
        this.restaurantlogourl = restaurantlogourl;
    }

    public String getRestaurantlat() {
        return restaurantlat;
    }

    public void setRestaurantlat(String restaurantlat) {
        this.restaurantlat = restaurantlat;
    }

    public String getRestaurantlng() {
        return restaurantlng;
    }

    public void setRestaurantlng(String restaurantlng) {
        this.restaurantlng = restaurantlng;
    }

    public String getRestaurantservicetype() {
        return restaurantservicetype;
    }

    public void setRestaurantservicetype(String restaurantservicetype) {
        this.restaurantservicetype = restaurantservicetype;
    }

    public String getRestaurantaddres() {
        return restaurantaddres;
    }

    public void setRestaurantaddres(String restaurantaddres) {
        this.restaurantaddres = restaurantaddres;
    }

    public String getRestaurantmob() {
        return restaurantmob;
    }

    public void setRestaurantmob(String restaurantmob) {
        this.restaurantmob = restaurantmob;
    }

    public String getRatting() {
        return ratting;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    public String getFavorittring() {
        return favorittring;
    }

    public void setFavorittring(String favorittring) {
        this.favorittring = favorittring;
    }

    public String getRestaurantimageurl() {
        return restaurantimageurl;
    }

    public void setRestaurantimageurl(String restaurantimageurl) {
        this.restaurantimageurl = restaurantimageurl;
    }

    public String getRestaurantdelivery() {
        return restaurantdelivery;
    }

    public void setRestaurantdelivery(String restaurantdelivery) {
        this.restaurantdelivery = restaurantdelivery;
    }

    public String getRestaurantdeliverytime() {
        return restaurantdeliverytime;
    }

    public void setRestaurantdeliverytime(String restaurantdeliverytime) {
        this.restaurantdeliverytime = restaurantdeliverytime;
    }

    public String getRestaurantdistance() {
        return restaurantdistance;
    }

    public void setRestaurantdistance(String restaurantdistance) {
        this.restaurantdistance = restaurantdistance;
    }
}
