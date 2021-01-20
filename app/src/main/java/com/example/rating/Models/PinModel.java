package com.example.rating.Models;

import com.google.gson.JsonObject;

import retrofit2.Response;

public class PinModel {

    JsonObject js;
    public PinModel(Response<JsonObject> response) {
        js = response.body();

    }

    public boolean success(){
        return  js.get("success").getAsInt() == 1;
    }

    public String message(){
        if(js.get("message").getAsString().equals("")){
            return "Null";
        }else{
            return  js.get("message").getAsString();
        }
    }

    public String pin (){
        if(js.get("pin").getAsString().equals("")){
            return "Null";
        }else{
            return  js.get("pin").getAsString();
        }
    }



}
