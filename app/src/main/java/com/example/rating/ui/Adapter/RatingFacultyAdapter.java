package com.example.rating.ui.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rating.RatingByFaculty;
import com.example.rating.R;
import com.example.rating.SessionManager;

import java.util.HashMap;
import java.util.List;

public class RatingFacultyAdapter extends RecyclerView.Adapter<RatingFacultyAdapter.MyViewHolder> {

    private List<RatingByFaculty> moviesList;
    private Context context;
    private int resource;
    private HashMap<String,String> data;
    private String student_id;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userGroup, userRating;

        public MyViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.user_name_by_faculty_row);
            userGroup = (TextView) view.findViewById(R.id.user_group_by_faculty_row);
            userRating = (TextView) view.findViewById(R.id.user_rating_by_faculty_row);
        }
    }


    public RatingFacultyAdapter(Context context, List<RatingByFaculty> moviesList, int res) {
        this.moviesList = moviesList;
        this.context = context;
        this.resource=res;
        SessionManager manager = new SessionManager(context);
        data = manager.getUserDetails();
        student_id = data.get("id");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(resource, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RatingByFaculty faculty = moviesList.get(position);
        if(student_id.equals(faculty.getStudent_id())){
            holder.userName.setText(faculty.getName());
            holder.userGroup.setText(faculty.getGroup());
            holder.userRating.setText(String.valueOf(faculty.getRating()));
        }

        else {holder.userName.setTypeface(null, Typeface.BOLD);
        holder.userGroup.setTypeface(null, Typeface.BOLD);
        holder.userRating.setTypeface(null, Typeface.BOLD);
        holder.userName.setText(faculty.getName());
        holder.userGroup.setText(faculty.getGroup());
        holder.userRating.setText(String.valueOf(faculty.getRating()));}
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
