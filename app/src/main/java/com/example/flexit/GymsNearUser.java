package com.example.flexit;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//For Maps Functionality
public class GymsNearUser extends AsyncTask<Object, String, String> {

    private GoogleMap gMapData;
    private String getMapData;
    String MapUrlObject;

    @Override
    protected String doInBackground(Object... objects){
        gMapData = (GoogleMap)objects[0];
        MapUrlObject = (String)objects[1];

        GetGymUrl GymUrl = new GetGymUrl();
        try {
            getMapData = GymUrl.UrlCatcher(MapUrlObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getMapData;
    }

    @Override
    protected void onPostExecute(String execute){

        List<HashMap<String, String>> nearbyPlaceList;
        ParseNearbyGym parseNearbyLocation = new ParseNearbyGym();
        nearbyPlaceList = parseNearbyLocation.parseGymData(execute);
        getNearbyGyms(nearbyPlaceList);
    }

    private void getNearbyGyms(List<HashMap<String, String>> gym)
    {
        for(int iterator = 0; iterator < gym.size(); iterator++)
        {
            MarkerOptions gymIcon = new MarkerOptions();
            HashMap<String, String> googlePlace = gym.get(iterator);

            String gymName = googlePlace.get("place_name");
            String gymVicin = googlePlace.get("vicinity");
            double currentlat = Double.parseDouble( googlePlace.get("lat"));
            double currentlong = Double.parseDouble( googlePlace.get("lng"));

            LatLng getUserLatLong = new LatLng( currentlat, currentlong);
            gymIcon.position(getUserLatLong);
            gymIcon.title(gymName + " : "+ gymVicin);
            gymIcon.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gym));

            gMapData.addMarker(gymIcon);
            gMapData.moveCamera(CameraUpdateFactory.newLatLng(getUserLatLong));
            gMapData.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }
}
