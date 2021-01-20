package com.example.rating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rating.ui.Adapter.RatingAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RatingDetailsActivity extends AppCompatActivity
{
    private List<Point> pointList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RatingAdapter rAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_details);
        recyclerView = (RecyclerView) findViewById(R.id.user_details);

        SessionManager session = new SessionManager(this);

        HashMap<String, String> user_data = session.getUserDetails();

        AppController.getApi().getRatingDetails(user_data.get("id")).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                rAdapter = new RatingAdapter(getApplicationContext(),response,0);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(rAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

      //  mock();

    }
}
