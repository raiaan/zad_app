package com.example.zadshare.retrofit_interface;

import com.example.zadshare.models.Item;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface APIInterface {
    @POST("zadvisitorsapi/zadvisitorsapi.php/get_all_restaurants_for_menu")
    public Call<ArrayList<Item>> getRestaurants(@Body JSONObject body) ;
}
