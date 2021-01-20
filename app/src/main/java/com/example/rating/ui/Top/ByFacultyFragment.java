package com.example.rating.ui.Top;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.rating.ui.Adapter.RatingAdapter;
import com.example.rating.ui.Adapter.RatingFacultyAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ByFacultyFragment extends Fragment {
    private RecyclerView recyclerViewFaculty;
    private RatingFacultyAdapter ratingFacultyAdapter;
    private List<RatingByFaculty> facultyList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_by_faculty, viewGroup, false);

        recyclerViewFaculty = (RecyclerView) root.findViewById(R.id.recycler_by_faculty);
        loadData();

        return root;
    }

    private void loadData() {
        AppController.getApi().getAllStudents().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                facultyList = new ArrayList<>();
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

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerViewFaculty.setLayoutManager(mLayoutManager);
                recyclerViewFaculty.setItemAnimator(new DefaultItemAnimator());
                recyclerViewFaculty.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));

                ratingFacultyAdapter = new RatingFacultyAdapter(getActivity(), facultyList, R.layout.faculty_list_row);
                recyclerViewFaculty.setAdapter(ratingFacultyAdapter);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void mock() {
//        RatingByFaculty first = new RatingByFaculty("Иванов Иван Иванович", "КА-19-03", 100);
//        facultyList.add(first);
//
//        RatingByFaculty sec = new RatingByFaculty("Иванов Иван Ивановчи2", "КИ-19-01", -3);
//        facultyList.add(sec);
//
//        RatingByFaculty ec = new RatingByFaculty("Иванов Иван Иванович3", "КС-19-..", 20);
//        facultyList.add(ec);
//

    }
}

