package com.example.rating.ui.Login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rating.AppController;
import com.example.rating.MainActivity;
import com.example.rating.Models.PinModel;
import com.example.rating.Models.StudentModel;
import com.example.rating.R;
import com.example.rating.SessionManager;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button get_code;
    Button registration;
    Button save;
    EditText new_password;
    EditText repeat_password;
    EditText user_number;
    EditText user_code;
    TextView code;
    TextView wrong_password;

    String pin_code;
    String phone_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        get_code = (Button) findViewById(R.id.get_code);
        save = (Button) findViewById(R.id.user_save);
        new_password= (EditText) findViewById(R.id.password_user_new);
        repeat_password = (EditText) findViewById(R.id.password_user_repeat);
        user_number = (EditText) findViewById(R.id.user_number);
        user_code = (EditText) findViewById(R.id.user_code);
        registration = (Button) findViewById(R.id.btn_registration);
        code = (TextView) findViewById(R.id.code);
        wrong_password = (TextView) findViewById(R.id.wrong_password);

        get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_code.setVisibility(View.GONE);
                user_number.setVisibility(View.GONE);
                user_code.setVisibility(View.VISIBLE);
                registration.setVisibility(View.VISIBLE);
                code.setVisibility(View.VISIBLE);


                phone_number = user_number.getText().toString();

                AppController.getApi().authRequest(user_number.getText().toString()).enqueue(new Callback<JsonObject>() {

                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        PinModel pinModel = new PinModel(response);
                        if(pinModel.success()){
                            code.setText(pinModel.pin());
                            pin_code = pinModel.pin();
                            set_password();
                        }else{
                            code.setText(pinModel.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });



    }

    void set_password(){
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pin_code.contentEquals(user_code.getText())) {
                    user_code.setVisibility(View.GONE);
                    registration.setVisibility(View.GONE);
                    new_password.setVisibility(View.VISIBLE);
                    repeat_password.setVisibility(View.VISIBLE);
                    save.setVisibility(View.VISIBLE);
                    code.setVisibility(View.GONE);

                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if (new_password.getText().toString().equals(repeat_password.getText().toString())){
                                AppController.getApi().set_password(new_password.getText().toString(), phone_number ).enqueue(new Callback<JsonObject>() {
                                    @Override
                                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                        StudentModel studentModel = new StudentModel(response);
                                        if (studentModel.success()){

                                            SessionManager manager = new SessionManager(getApplicationContext());
                                            manager.createLoginSession(studentModel.student_id(),
                                                    studentModel.name(),
                                                    studentModel.surname(),
                                                    studentModel.midname(),
                                                    studentModel.group(),
                                                    phone_number,
                                                    new_password.getText().toString(),
                                                    studentModel.rating(),
                                                    studentModel.group_id());
                                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                            finish();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<JsonObject> call, Throwable t) {

                                    }
                                });

                            }
                            else {
                                wrong_password.setVisibility(View.VISIBLE);
                            }



                        }
                    });

                }
                else {
                    code.setText("Код неверный");
                }
            }
        });
    }

}
