package com.example.rating.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rating.Point;
import com.example.rating.R;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Response;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.MyViewHolder> {

    private Context context;
    Point point;
    private int count_flag;
    private static String today;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView data, codecs, ball;

        public MyViewHolder(View view) {
            super(view);
            data = (TextView) view.findViewById(R.id.data);
            codecs = (TextView) view.findViewById(R.id.codecs);
            ball = (TextView) view.findViewById(R.id.ball);
        }
    }


    public RatingAdapter(Context context, Response<JsonObject> points,int flag) {
        this.point = new Point(points);
        this.context = context;
        this.count_flag = flag;

        Calendar calendar = Calendar.getInstance();
        today = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.point_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.data.setText(getTimeStamp(point.getDate(position)));
        holder.codecs.setText(point.getCodecs(position));
        if (point.getPoint(position)>0){
            holder.ball.setTextColor(context.getResources().getColor(R.color.plusRating));
            holder.ball.setText("+"+ point.getPoint(position));
        } else {
            holder.ball.setTextColor(context.getResources().getColor(R.color.minusRating));
            holder.ball.setText(String.valueOf((point.getPoint(position))));
        }
    }

    private static String getTimeStamp(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = "";

        today = today.length() < 2 ? "0" + today : today;

        try {
            Date date = format.parse(dateStr);
            SimpleDateFormat todayFormat = new SimpleDateFormat("dd");
            String dateToday = todayFormat.format(date);
            format = dateToday.equals(today) ? new SimpleDateFormat("hh:mm a") : new SimpleDateFormat("dd.MM.yy");
            String date1 = format.format(date);
            timestamp = date1.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timestamp;
    }


    @Override
    public int getItemCount() {
        return count_flag == 1 ? point.size() > 3 ? 3 : point.size() : point.size();
    }
}
