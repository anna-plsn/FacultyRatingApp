package com.example.rating.Models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Response;

public class RatingModel {


    JsonObject js;

    public RatingModel(Response<JsonObject> response) {
        js = response.body();
    }

    private JsonArray getData(){
        return js.getAsJsonArray();
    }

    public int size(){
        return getData().size();
    }

    public String date(int p){
        return getData().get(p).getAsJsonObject().get("date").getAsString();
    }

    public String paragraph(int p){
        return getData().get(p).getAsJsonObject().get("paragraph").getAsString();
    }

    public int sum(int p){
        return getData().get(p).getAsJsonObject().get("sum").getAsInt();
    }

}
