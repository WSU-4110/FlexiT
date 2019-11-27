package com.example.flexit;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParseNearbyGym {

    private HashMap<String,String> getGymDuration(JSONArray GymJson)
    {
        HashMap<String,String> GymMap = new HashMap<>();
        String ParseDuration = "";
        String ParseDistance ="";

        try {

            ParseDuration = GymJson.getJSONObject(0).getJSONObject("duration").getString("text");
            ParseDistance = GymJson.getJSONObject(0).getJSONObject("distance").getString("text");

            GymMap.put("duration" , ParseDuration);
            GymMap.put("distance", ParseDistance);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return GymMap;
    }

    private HashMap<String, String> getPlace(JSONObject JsonMap)
    {
        HashMap<String, String> SetGym = new HashMap<>();
        String gymName = "--NA--";
        String gymVic= "--NA--";
        String gymLat= "";
        String gymLong="";
        String gymRef="";



        try {
            if (!JsonMap.isNull("name")) {
                gymName = JsonMap.getString("name");
            }
            if (!JsonMap.isNull("vicinity")) {
                gymVic = JsonMap.getString("vicinity");
            }

            gymLat = JsonMap.getJSONObject("geometry").getJSONObject("location").getString("lat");
            gymLong = JsonMap.getJSONObject("geometry").getJSONObject("location").getString("lng");

            gymRef = JsonMap.getString("reference");

            SetGym.put("place_name", gymName);
            SetGym.put("vicinity", gymVic);
            SetGym.put("lat", gymLat);
            SetGym.put("lng", gymLong);
            SetGym.put("reference", gymRef);


        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return SetGym;

    }
    private List<HashMap<String, String>> getGymLocation(JSONArray gymArrayObject)
    {
        int gymlength = gymArrayObject.length();
        List<HashMap<String, String>> gymlist = new ArrayList<>();
        HashMap<String, String> Place = null;

        for(int i = 0; i<gymlength;i++)
        {
            try {
                Place = getPlace((JSONObject) gymArrayObject.get(i));
                gymlist.add(Place);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return gymlist;
    }

    public List<HashMap<String, String>> parseGymData(String gymData)
    {
        JSONArray gymArr = null;
        JSONObject gymObj;

        Log.d("json data", gymData);

        try {
            gymObj = new JSONObject(gymData);
            gymArr = gymObj.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getGymLocation(gymArr);
    }

    public HashMap<String,String> parseGymLocation(String parseJson) {


        JSONArray gymJsonArry = null;
        JSONObject gymJsonObject;

        try {
            gymJsonObject = new JSONObject(parseJson);
            gymJsonArry = gymJsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getGymDuration(gymJsonArry);

    }
}
