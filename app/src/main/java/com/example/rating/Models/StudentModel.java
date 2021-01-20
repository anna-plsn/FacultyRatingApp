package com.example.rating.Models;

import com.google.gson.JsonObject;

import retrofit2.Response;

public class StudentModel {

    JsonObject js;
    public StudentModel(Response<JsonObject> response) {
        js = response.body();
    }

    public boolean success(){
        return  js.get("success").getAsInt() == 1;
    }

    public String name(){
        if(js.get("name").getAsString().equals("")){
            return "Null";
        }else{
            return  js.get("name").getAsString();
        }
    }
    public String surname(){
        if(js.get("surname").getAsString().equals("")){
            return "Null";
        }else{
            return  js.get("surname").getAsString();
        }
    }
    public String midname(){
        if(js.get("midname").getAsString().equals("")){
            return "Null";
        }else{
            return  js.get("midname").getAsString();
        }
    }
    public String group_id(){
        if(js.get("group_id").getAsString().equals("")){
            return "Null";
        }else{
            return  js.get("group_id").getAsString();
        }
    }

    public String student_id(){
        if(js.get("id").getAsString().equals("")){
            return "Null";
        }else{
            return  js.get("id").getAsString();
        }
    }

    public String group(){
        if(js.get("group").getAsString().equals("")){
            return "Null";
        }else{
            return  js.get("group").getAsString();
        }
    }
    public String rating(){
        if(js.get("rating").getAsString().equals("")){
            return "Null";
        }else{
            return  js.get("rating").getAsString();
        }
    }

}
