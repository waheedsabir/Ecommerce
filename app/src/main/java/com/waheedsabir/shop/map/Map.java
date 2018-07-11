package com.waheedsabir.shop.map ;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.waheedsabir.shop.SignDetail.SignUP;

public class Map extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    int x = 0;
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    TextView address ;
    Button next ;
    double lat , log ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.waheedsabir.shop.R.layout.map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(com.waheedsabir.shop.R.id.map);
        mapFragment.getMapAsync(this);

         address = findViewById(com.waheedsabir.shop.R.id.address);
         next = findViewById(com.waheedsabir.shop.R.id.next);
         address.setSelected(true);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
       mLastLocation = location;

        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());


                lat =location.getLatitude() ;
                log = location.getLongitude() ;


             String strAdd = "";
            android.location.Geocoder geocoder = new android.location.Geocoder(this, java.util.Locale.getDefault());
            try {
                java.util.List<android.location.Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
                if (addresses != null) {
                    android.location.Address returnedAddress = addresses.get(0);
                    StringBuilder strReturnedAddress = new StringBuilder("");

                    for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                    }
                    strAdd = strReturnedAddress.toString();
                    android.util.Log.w("location", strReturnedAddress.toString());


                    if(x==0){
                        address.setText(strReturnedAddress.toString());
                        android.util.Log.d("ad","worf");
                        x++;

                    }else {

                    }



                } else {
                    android.util.Log.w("No addreess", "No Address returned!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                android.util.Log.w("error", "Canont get Address!");
            }


        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


public void next(android.view.View view) {
    SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
    SharedPreferences.Editor add =sharedPreferences.edit();
    add.putString("user_address",address.getText().toString());
    add.putString("user_lat",String.valueOf(lat));
    add.putString("user_log",String.valueOf(log));
    add.apply();
    startActivity(new Intent(Map.this, SignUP.class));
    fileList();



}
}
