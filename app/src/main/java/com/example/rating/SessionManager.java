package com.example.rating;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.rating.ui.Login.LoginActivity;

import java.util.HashMap;

public class SessionManager {

    private Context context;
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_MIDNAME = "midname";
    public static final String KEY_GROUP = "group";
    public static final String KEY_GROUP_ID = "group_id";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_RATING = "rating";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ID = "id";
    private static final String IS_LOGIN = "IsLoggedIn";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("Student",Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(String id,String name, String surname,String midname,String group, String number, String password,String rating,String group_id){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID,id);
        editor.putString(KEY_SURNAME,surname);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_RATING,rating);
        editor.putString(KEY_MIDNAME, midname);
        editor.putString(KEY_GROUP,group);
        editor.putString(KEY_GROUP_ID,group_id);
        editor.putString(KEY_NUMBER, number);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }


    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> user = new HashMap<>();

        user.put(KEY_ID, pref.getString(KEY_ID,null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_SURNAME, pref.getString(KEY_SURNAME, null));
        user.put(KEY_MIDNAME, pref.getString(KEY_MIDNAME,null));
        user.put(KEY_GROUP, pref.getString(KEY_GROUP, null));
        user.put(KEY_GROUP_ID, pref.getString(KEY_GROUP_ID, null));
        user.put(KEY_NUMBER, pref.getString(KEY_NUMBER, null));
        user.put(KEY_RATING, pref.getString(KEY_RATING, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD,null));
        return user;
    }

    public void checkLogin(){

        if(!this.isLoggedIn()){
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }



}
