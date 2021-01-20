package com.example.rating.ui.Rating;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rating.AppController;
import com.example.rating.Models.RatingModel;
import com.example.rating.Point;
import com.example.rating.R;
import com.example.rating.RatingDetailsActivity;
import com.example.rating.SessionManager;
import com.example.rating.ui.Adapter.RatingAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingFragment extends Fragment {

    private List<Point> pointList;
    private RecyclerView recyclerView;
    private RatingAdapter rAdapter;
    private Button detailsBtn;
    private View root;

    TextView fio_txt;
    TextView group_txt;
    TextView rating_txt;

    String id,fio, group, rating;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_rating, container, false);

        fio_txt = (TextView) root.findViewById(R.id.user_fio);
        group_txt = (TextView) root.findViewById(R.id.user_group);
        rating_txt = (TextView) root.findViewById(R.id.user_rating);

        SessionManager manager = new SessionManager(getActivity());
        HashMap<String, String> user_data = manager.getUserDetails();
        id = user_data.get("id");
        fio = user_data.get("surname")+" "+ user_data.get("name")+" "+ user_data.get("midname");

        fio_txt.setText(fio);
        group_txt.setText(user_data.get("group"));
        rating_txt.setText(user_data.get("rating"));




        loadRating();
//        mock();


        return root;
    }

    private void loadRating() {

        detailsBtn = (Button) root.findViewById(R.id.details_btn);

        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RatingDetailsActivity.class));
            }
        });
        recyclerView = (RecyclerView) root.findViewById(R.id.user_details);

        AppController.getApi().getRatingDetails(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                rAdapter = new RatingAdapter(getActivity(),response,1);
                recyclerView.setAdapter(rAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void mock() {
        Point first = new Point("20.10.2019","23.11",11);
        pointList.add(first);

        Point sec = new Point("21.10.2019","51.3",-3);
        pointList.add(sec);

        Point ec = new Point("21.11.2019","31.3",20);
        pointList.add(ec);
        rAdapter.notifyDataSetChanged();


    }
}