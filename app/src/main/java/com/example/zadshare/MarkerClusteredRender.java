package com.example.zadshare;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;


public class MarkerClusteredRender extends DefaultClusterRenderer<MarkerItem> {
    private IconGenerator iconGenerator;
    Context context;
    private ImageView image;
    private TextView title;
    private View view;
    public MarkerClusteredRender(Context context, GoogleMap map, ClusterManager<MarkerItem> clusterManager) {
        super(context, map, clusterManager);
        this.context=context;
        iconGenerator = new IconGenerator(context.getApplicationContext());
        view = LayoutInflater.from(context).inflate(R.layout.cluster_icon_layout,null);
        image = (ImageView)view.findViewById(R.id.cluster_icon_image);
        title = (TextView)view.findViewById(R.id.cluster_icon_title);
        iconGenerator.setContentView(view);
    }

    @Override
    protected boolean shouldRenderAsCluster(@NonNull Cluster<MarkerItem> cluster) {
        return false;
    }

    @Override
    protected void onBeforeClusterItemRendered(@NonNull MarkerItem item, @NonNull MarkerOptions markerOptions) {
        image.setImageDrawable(context.getResources().getDrawable(R.drawable.log_demo));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(context,view))).title(item.getTitle());
    }

    @Override
    protected void onClusterItemRendered(@NonNull final MarkerItem clusterItem, @NonNull final Marker marker) {
        Glide.with(context.getApplicationContext())
                .load("https://zadshareapp.com/zadvisitorsapi/picupload/member/"+clusterItem.getImage())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        image.setImageDrawable(resource);
                        title.setText(clusterItem.getTitle());
                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(context,view)));
                    }
                });
    }
    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else view.draw(canvas);

        return bitmap;
    }

}
