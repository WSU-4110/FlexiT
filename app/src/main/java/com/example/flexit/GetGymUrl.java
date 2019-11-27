package com.example.flexit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class GetGymUrl {
    public String UrlCatcher(String ConnectUrl) throws IOException
    {
        InputStream dataStream = null;
        HttpURLConnection GymConnection = null;
        String getGymData = "";


        try {
            URL seturl = new URL(ConnectUrl);
            GymConnection=(HttpURLConnection) seturl.openConnection();
            GymConnection.connect();

            dataStream = GymConnection.getInputStream();
            StringBuffer readStrings = new StringBuffer();
            BufferedReader readData = new BufferedReader(new InputStreamReader(dataStream));

            String readIncomingData = "";
            while((readIncomingData = readData.readLine()) != null)
            {
                readStrings.append(readIncomingData);
            }

            getGymData = readStrings.toString();
            readData.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            dataStream.close();
            GymConnection.disconnect();
        }
        return getGymData;
    }
}
