package com.example.rating;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("auth.php")
    Call<JsonObject> authRequest(
            @Query("number") String number
    );


    @GET("set_password.php")
    Call<JsonObject> set_password(
            @Query("password") String password,
            @Query("number") String number
    );

    @GET("login.php")
    Call<JsonObject> login(
            @Query("password") String password,
            @Query("number") String number
    );


    @GET("faculty_info.php")
    Call<JsonObject> getAllStudents();


    @GET("rating_details.php")
    Call<JsonObject> getRatingDetails(
            @Query("student_id") String student_id
    );

    @GET("rating_group.php")
    Call<JsonObject> getByGroupRating(
            @Query("group_id") String group_id
    );

}
