package com.example.rating.ui.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rating.AppController;
import com.example.rating.Models.StudentModel;
import com.example.rating.R;
import com.example.rating.SessionManager;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    Button save;
    TextView passwordOld;
    TextView passwordNew;
    TextView passwordRepeat;
    TextView wrong_old_password;
    TextView wrong_repeat_password;
    TextView success;

    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        save = (Button) findViewById(R.id.save);
        passwordNew = (TextView) findViewById(R.id.passwordNew);
        passwordOld = (TextView) findViewById(R.id.passwordOld);
        passwordRepeat = (TextView) findViewById(R.id.passwordRepeat);
        wrong_old_password = (TextView) findViewById(R.id.wrong_old_password);
        wrong_repeat_password = (TextView) findViewById(R.id.wrong_repeat_password);
        success = (TextView) findViewById(R.id.password_success);

        manager = new SessionManager(getApplicationContext());

        final HashMap<String,String> user_data = manager.getUserDetails();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordOld.getText().toString().equals(user_data.get("password"))){
                    if (passwordNew.getText().toString().equals(passwordRepeat.getText().toString())){
                        AppController.getApi().set_password(passwordNew.getText().toString(), user_data.get("number")).enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                StudentModel model = new StudentModel(response);
                                if (model.success()){
                                    success.setVisibility(View.VISIBLE);
                                }
                                else {
                                    success.setTextColor(getResources().getColor(R.color.minusRating));
                                    success.setText("Произошла ошибка. Попробуйте позднее");
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {

                            }
                        });
                    }
                    else {
                        wrong_repeat_password.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    wrong_old_password.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
