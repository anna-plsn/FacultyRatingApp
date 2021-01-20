package com.example.rating.Models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Response;

public class FacultyModel {


    JsonObject js;
    public FacultyModel(Response<JsonObject> response) {
        js = response.body();
    }

    public boolean success(){
        return  js.get("success").getAsInt() == 1;
    }

    private JsonArray students(){
        return js.get("students").getAsJsonArray();
    }

    private JsonArray groups(){
        return js.get("groups").getAsJsonArray(); //todo make null free
    }

    public int students_size(){
        return students().size();
    }
    public int groups_size(){
        return groups().size();
    }

    public String student_id(int position){
        return students().get(position).getAsJsonObject().get("id").getAsString();
    }

    public String student_name(int p){
        return students().get(p).getAsJsonObject().get("name").getAsString();
    }
    public String student_surname(int p){
        return students().get(p).getAsJsonObject().get("surname").getAsString();
    }
    public String student_midname(int p){
        return students().get(p).getAsJsonObject().get("midname").getAsString();
    }
    public String student_group_id(int p){
        return students().get(p).getAsJsonObject().get("group_id").getAsString();
    }
    public String student_rating(int p){
        return students().get(p).getAsJsonObject().get("rating").getAsString();
    }
    public String student_rating_last_id(int p){
        return students().get(p).getAsJsonObject().get("rating_last_id").getAsString();
    }

    public String group_name (int p){
        return groups().get(p).getAsJsonObject().get("name").getAsString();
    }
    public String group_id (int p){
        return groups().get(p).getAsJsonObject().get("id").getAsString();
    }


}
