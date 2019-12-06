package com.example.flexit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{



    private GoogleApiClient gApiClient;
    private GoogleMap CallGoogleMap;
    double userLat, userLong;
    public static final int LocationRange = 99;
    private Marker userCurrentLocation;
    int PROXIMITY_RADIUS = 5000;
    private LocationRequest onLocationcall;
    private Location locationCalled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            LocationValidation();

        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }







    public void onMapCall(int callApiRequest, @NonNull String[] getlocation, @NonNull int[] userResult) {
        switch(callApiRequest)
        {
            case LocationRange:
                if(userResult.length >0 && userResult[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
                    {
                        if(gApiClient == null)
                        {
                            GetClientApi();
                        }
                        CallGoogleMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
                }
        }
    }


    @Override
    public void onMapReady(GoogleMap gMapKey) {
        CallGoogleMap = gMapKey;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            GetClientApi();
            CallGoogleMap.setMyLocationEnabled(true);
        }
    }


    protected synchronized void GetClientApi() {
        gApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        gApiClient.connect();

    }

    @Override
    public void onLocationChanged(Location userLocationChanged) {

        userLat = userLocationChanged.getLatitude();
        ///getlatitude and get longitute are built in functions
        userLong = userLocationChanged.getLongitude();
        locationCalled = userLocationChanged;
        if(userCurrentLocation != null)
        {
            userCurrentLocation.remove();

        }
        Log.d("lat = ",""+ userLat);
        LatLng logUserLocation = new LatLng(userLocationChanged.getLatitude() , userLocationChanged.getLongitude());
        MarkerOptions showUser = new MarkerOptions();
        showUser.position(logUserLocation);
        showUser.title("Your current location");
        showUser.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gym));
        userCurrentLocation = CallGoogleMap.addMarker(showUser);
        CallGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(logUserLocation));
        CallGoogleMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if(gApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(gApiClient,this);
        }
    }

    public void onClick(View v)
    {
        Object locationData[] = new Object[2];
        GymsNearUser getLocationData = new GymsNearUser();

        switch(v.getId())
        {
            case R.id.view_gyms:
                CallGoogleMap.clear();
                String gym= "gym";
                String getUserData = getData(userLat, userLong, gym);
                locationData[0] = CallGoogleMap;
                locationData[1] = getUserData;

                Toast.makeText(MapsActivity.this, "Showing Nearby Gyms", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    String getData(double currentUserLat, double currentUserLong, String Gym)


    {

        StringBuilder setGymData = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        setGymData.append("location="+currentUserLat+","+currentUserLong);
        setGymData.append("&radius="+PROXIMITY_RADIUS);
        setGymData.append("&type="+Gym);
        setGymData.append("&sensor=true");
        setGymData.append("&key="+"AIzaSyDWaPNaWKrFlYY9CDoJkVNNcuxpL6okmfI");//API KEY

        return setGymData.toString();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        onLocationcall = new LocationRequest();
        onLocationcall.setFastestInterval(1000);
        onLocationcall.setInterval(100);
        onLocationcall.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(gApiClient, onLocationcall, this);
        }
    }


    public boolean LocationValidation()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION }, LocationRange);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION }, LocationRange);
            }
            return false;

        }
        else
            return true;
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
