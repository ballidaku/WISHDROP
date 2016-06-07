package com.ameba.sharan.wishdrop;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class WishDetails extends AppCompatActivity implements View.OnClickListener ,OnMapReadyCallback
{

    LinearLayout lay_back;
    TextView     txtv_title;
    ImageView    imgv_otherprofilepic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_details);


        setUpIds();

    }

    private void setUpIds()
    {
        (lay_back = (LinearLayout) findViewById(R.id.lay_back)).setOnClickListener(this);
        txtv_title = (TextView) findViewById(R.id.txtv_title);

        (imgv_otherprofilepic = (ImageView) findViewById(R.id.imgv_otherprofilepic)).setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        txtv_title.setText("Wish List Details");
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.lay_back:
                finish();
                break;


            case R.id.imgv_otherprofilepic:
                startActivity(new Intent(this,OtherProfile.class));
                break;

        }
    }

    @Override
    public void onMapReady(GoogleMap map)
    {


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.889, -87.622), 16));

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_fb)).anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                  .position(new LatLng(41.889, -87.622)));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) return;
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);


    }


}
