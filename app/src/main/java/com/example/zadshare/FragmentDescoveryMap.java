package com.example.zadshare;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.zadshare.models.Item;
import com.example.zadshare.retrofit_interface.APIInterface;
import com.example.zadshare.retrofit_interface.ApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDescoveryMap extends Fragment implements Callback<ArrayList<Item>>,
        OnMapReadyCallback {

    private APIInterface apiInterface= ApiClient.getClient().create(APIInterface.class);
    private Call call;

    {
        try {
            call = apiInterface.getRestaurants(
                    new JSONObject("{ \"fixedpassword\": \"Zadj{o*=k^QFmi^\", \"language\": \"en\", \"usertoken\": \"56jfy6ras856sdg67r8tre56s\" , \"menuid\": \"1\" , \"visitorlocationlat\": \"30.033333\", \"visitorlocationlng\": \"31.233334\" }")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ClusterManager<MarkerItem> mClusterManager;
    private MarkerClusteredRender mClusterManagerRenderer;
    private ArrayList<MarkerItem> mClusterMarkers ;
    private  GoogleMap map;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_descovery_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(googleMap!=null){
            this.map = googleMap;
            call.enqueue(this);
        }

    }

    @Override
    public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {

        if (response.body() != null) {
            addMarkers(response.body());
        } else {
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Item>> call, Throwable t) {

    }
    private void  addMarkers(ArrayList<Item> places){
        if (map != null) {
            if (mClusterManager == null) {
                mClusterManager = new ClusterManager<MarkerItem>(getActivity().getApplicationContext(), map);
            }
            if (mClusterManagerRenderer == null) {
                mClusterManagerRenderer = new MarkerClusteredRender(getActivity(), map, mClusterManager );
                mClusterManager.setRenderer(mClusterManagerRenderer);
                map.setInfoWindowAdapter(mClusterManager.getMarkerManager());
                mClusterManager.getMarkerCollection().setInfoWindowAdapter(new MyCustomAdapterForItems(LayoutInflater.from(getContext())
                        .inflate(R.layout.map_detail, null),places));
                map.setOnMarkerClickListener(mClusterManager);
            }
            for (Item place:places) {
                try {
                    MarkerItem newClusterMarker = new MarkerItem(
                            new LatLng(
                                    Double.parseDouble(place.getRestaurantlat()),
                                    Double.parseDouble(place.getRestaurantlng())
                            ),
                            place.getRestaurantname(),
                            place.getRestaurantdelivery(),
                            place,place.getRestaurantlogourl());
                    mClusterManager.addItem(newClusterMarker);
                    mClusterMarkers.add(newClusterMarker);
                } catch (NullPointerException e) {
                }
            }
            mClusterManager.cluster();
            setCameraView();
        }
    }
    private  void setCameraView() {
        Double bottomBoundary  =30.033333 - .1;
        Double leftBoundary = 31.233334 - .1;
        Double topBoundary = 30.033333 + .1;
        Double rightBoundary= 31.233334 + .1;
        map.moveCamera(
                CameraUpdateFactory.newLatLngBounds(
                        new LatLngBounds(
                                new LatLng(bottomBoundary, leftBoundary),
                                new LatLng(topBoundary, rightBoundary)
                        ), 0
                )
        );
    }
    public class MyCustomAdapterForItems implements GoogleMap.InfoWindowAdapter{

        View view ;
        ArrayList<Item> detail;
        ImageView logo,delivery;
        TextView placename, address,rattingTxt,typeService;
        RatingBar ratingBar;
        MyCustomAdapterForItems(View view,ArrayList<Item> detail) {
            this.view = view;
            this.detail=detail;
        }
        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }
        private Item filterData(String currMarker){
            for (Item item: detail){
                if(item.getRestaurantname().equals(currMarker))
                    return item;
            }
            return null;
        }
        @Override
        public View getInfoContents(Marker marker) {
            if(view !=null){
                Item item= filterData(marker.getTitle());
                logo=(ImageView) view.findViewById(R.id.logo_place_detail);
                delivery=(ImageView) view.findViewById(R.id.delivery_checkbox);
                placename = (TextView) view.findViewById(R.id.name_place_detail);
                address= (TextView)view.findViewById(R.id.location_place_detail);
                rattingTxt = (TextView)view.findViewById(R.id.rating_txt_place_detail);
                ratingBar =(RatingBar) view.findViewById(R.id.ratingBar);
                typeService=(TextView)view.findViewById(R.id.type_place_detail);
                if(item!=null){
                    Glide.with(getContext().getApplicationContext())
                            .load("https://zadshareapp.com/zadvisitorsapi/picupload/member/"+item.getRestaurantlogourl())
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    logo.setImageDrawable(resource);
                                }
                            });
                    placename.setText(item.getRestaurantname());
                    address.setText(item.getRestaurantaddres());
                    rattingTxt.setText(item.getRatting());
                    ratingBar.setRating(Float.parseFloat(item.getRatting()));
                    delivery.setColorFilter(getContext().getResources().getColor(R.color.tabs_text_color), PorterDuff.Mode.SRC_IN);

                }

                return view;
            }

            return null;
        }

    }
}