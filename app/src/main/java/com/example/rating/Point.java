package com.example.rating;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Response;

public class Point {

    String date,Codecs;
     int ball;

     JsonObject js;

    public Point(Response<JsonObject> response) {
        js = response.body();
    }

    public Point(String date, String codecs, int ball) {
        this.date = date;
        Codecs = codecs;
        this.ball = ball;
    }

    private JsonArray getData(){
        return js.get("rating").getAsJsonArray();
    }

    public int size(){
        return getData().size();
    }


    public String getDate(int p) {
        return getData().get(p).getAsJsonObject().get("date").getAsString();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCodecs(int p) {
        return getData().get(p).getAsJsonObject().get("paragraph").getAsString();
    }

    public void setCodecs(String codecs) {
        Codecs = codecs;
    }

    public int getPoint(int p) {
        return getData().get(p).getAsJsonObject().get("sum").getAsInt();
    }

}
