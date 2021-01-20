package com.example.rating.ui.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rating.AppController;
import com.example.rating.MainActivity;
import com.example.rating.Models.StudentModel;
import com.example.rating.R;
import com.example.rating.SessionManager;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class LoginActivity extends AppCompatActivity {

    TextView greeting;
    EditText login;
    EditText password;
    Button enter;
    Button registration;
    TextView wrong;

    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        manager = new SessionManager(getApplicationContext());

        greeting =(TextView) findViewById(R.id.name_university_txt);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        enter = (Button) findViewById(R.id.enter);
        registration =(Button) findViewById(R.id.registration);
        wrong = (TextView) findViewById(R.id.wrong_login);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppController.getApi().login(password.getText().toString(), login.getText().toString()).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        StudentModel studentModel = new StudentModel(response);
                        if (studentModel.success()){

                            manager.createLoginSession(studentModel.student_id(),
                                    studentModel.name(),
                                    studentModel.surname(),
                                    studentModel.midname(),
                                    studentModel.group(),
                                    login.getText().toString(),
                                    password.getText().toString(),
                                    studentModel.rating(),
                                    studentModel.group_id());

                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                           wrong.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });

            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }
}
