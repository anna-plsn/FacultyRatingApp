package com.example.rating.ui.Top;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rating.AppController;
import com.example.rating.Models.FacultyModel;
import com.example.rating.Point;
import com.example.rating.R;
import com.example.rating.RatingByFaculty;
import com.example.rating.SessionManager;
import com.example.rating.ui.Adapter.RatingAdapter;
import com.example.rating.ui.Adapter.RatingFacultyAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ByGroupFragment extends Fragment {
    private RecyclerView recyclerViewFaculty;
    private RatingFacultyAdapter ratingFacultyAdapter;
    private List<RatingByFaculty> facultyList = new ArrayList<>();
    private HashMap<String,String> user_data;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_by_group, viewGroup, false);

        SessionManager sessionManager = new SessionManager(getActivity());
        user_data = sessionManager.getUserDetails();
        loadData();




        //mock();
        return root;
    }

    private void loadData() {
        AppController.getApi().getByGroupRating(user_data.get("group_id")).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                FacultyModel model = new FacultyModel(response);
                for (int i = 0; i < model.students_size(); i++) {
                    String fio =  model.student_surname(i) + " " + model.student_name(i);
                    if (fio.length() > 21 ) {
                        fio = fio.substring(0,21);
                        fio += "..";
                    }
                    String group = "";
                    for (int j = 0; j < model.groups_size(); j++) {
                        if( model.group_id(j).equals(model.student_group_id(i))){
                            group = model.group_name(j);
                            break;
                        }
                    }
                    facultyList.add(new RatingByFaculty( model.student_id(i),fio, group, Integer.valueOf(model.student_rating(i))));
                }

                RecyclerView recyclerViewFaculty = (RecyclerView) root.findViewById(R.id.recycler_by_group);

                ratingFacultyAdapter = new RatingFacultyAdapter(getActivity(),facultyList, R.layout.group_list_row);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerViewFaculty.setLayoutManager(mLayoutManager);
                recyclerViewFaculty.setItemAnimator(new DefaultItemAnimator());
                recyclerViewFaculty.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
                recyclerViewFaculty.setAdapter(ratingFacultyAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

    private void mock() {
        RatingByFaculty first = new RatingByFaculty("1","Иванов Иван Иванович","КА-19-03",100);
        facultyList.add(first);

        RatingByFaculty sec = new RatingByFaculty("2","Иванов Иван Ивановчи2","КИ-19-01",-3);
        facultyList.add(sec);

        RatingByFaculty ec = new RatingByFaculty("3","Иванов Иван Иванович3","КС-19-..",20);
        facultyList.add(ec);
    }
}
