package com.nacu.sport.utils;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class OpenStreetMapUtils
{
    private static OpenStreetMapUtils instance = null;

    private OpenStreetMapUtils() {}

    public static OpenStreetMapUtils getInstance()
    {
        if (instance == null)
        {
            instance = new OpenStreetMapUtils();
        }
        return instance;
    }

    public GeoPoint getCoordinates(String address)
    {
        GeoPoint geoPoint = null;
        StringBuilder query;
        String[] split = address.split(" ");
        String queryResult = null;

        query = new StringBuilder();

        query.append("https://nominatim.openstreetmap.org/search?q=");

        if (split.length == 0)
        {
            return null;
        }

        for (int i = 0; i < split.length; i++)
        {
            query.append(split[i]);
            if (i < (split.length - 1))
            {
                query.append("+");
            }
        }
        query.append("&format=json&addressdetails=1");

        log.debug("Query:" + query);

        try
        {
            queryResult = getRequest(query.toString());
        }
        catch (Exception e)
        {
            log.error("Error when trying to get data with the following query " + query);
        }

        if (queryResult == null)
        {
            return null;
        }

        Object obj = JSONValue.parse(queryResult);
        log.debug("obj=" + obj);

        if (obj instanceof JSONArray array)
        {
            if (array.size() > 0)
            {
                JSONObject jsonObject = (JSONObject) array.get(0);

                String lon = (String) jsonObject.get("lon");
                String lat = (String) jsonObject.get("lat");
                log.debug("lon=" + lon);
                log.debug("lat=" + lat);
                geoPoint = new GeoPoint(Double.parseDouble(lat), Double.parseDouble(lon));
            }
        }

        return geoPoint;
    }

    private String getRequest(String url) throws Exception
    {
        final URL obj = new URL(url);
        final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200)
        {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}