package com.example.calendarapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<String> dayOfMonth;
    private final ArrayList<String> eventDates = new ArrayList<>();
    private final OnItemListener onItemListener;
    public Context context;
    int selectedpos = -1;
    private DateFormat dateFormat = DateFormat.getDateInstance();
    Calendar c = new GregorianCalendar();

    public CalendarAdapter(Context context,ArrayList<String> dayOfMonth, OnItemListener onItemListener)
    {
        this.dayOfMonth = dayOfMonth;
        this.onItemListener = onItemListener;
        this.context = context;
        eventDates.add("12-07-2023");
        eventDates.add("14-07-2023");
        eventDates.add("17-07-2023");
        eventDates.add("27-07-2023");
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height= (int) (parent.getHeight() * 0.111111111);
        return new CalendarViewHolder(view, onItemListener);
    }



    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        String currentDate = "";
        String currentMonth = "";
        String currentYear = "";
        String day = dayOfMonth.get(position);
        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int days = cal.get(Calendar.DAY_OF_MONTH);
        if (!day.isEmpty()) {
            String currentDateArray[] = day.split("-", 3);
            currentDate = currentDateArray[2];
            currentMonth = currentDateArray[1];
            currentYear = currentDateArray[0];
        }
        holder.dayOfMonth.setText(currentDate);

        if (!day.isEmpty() && Integer.parseInt(currentDate) == days &&
                Integer.parseInt(currentMonth) == (month+1) && Integer.parseInt(currentYear) == (year)){
            holder.dayOfMonth.setBackground(context.getDrawable(R.drawable.current_higllight));
            holder.dayOfMonth.setTextColor(Color.parseColor("#FFFFFF"));
        }


        for (String eventDate:eventDates) {
            String currentDateArray[] = eventDate.split("-", 3);
            String currDate = currentDateArray[0];
            String currMonth = currentDateArray[1];
            String currYear = currentDateArray[2];
            if (currentDate.equals(currDate) && currentMonth.equals(currMonth) && currentYear.equals(currYear)) {
                switch (eventDate) {
                    case "12-07-2023":
                        holder.dateCircle.setVisibility(View.VISIBLE);
                        holder.datecircle2.setVisibility(View.VISIBLE);
                        holder.datecircle3.setVisibility(View.GONE);
                        break;
                    case "14-07-2023":
                        holder.dateCircle.setVisibility(View.VISIBLE);
                        holder.datecircle2.setVisibility(View.GONE);
                        holder.datecircle3.setVisibility(View.VISIBLE);
                        break;
                    case "17-07-2023":
                        holder.dateCircle.setVisibility(View.VISIBLE);
                        holder.datecircle2.setVisibility(View.VISIBLE);
                        holder.datecircle3.setVisibility(View.VISIBLE);
                        break;

                    case "27-07-2023":
                        holder.dateCircle.setVisibility(View.VISIBLE);
                        holder.datecircle2.setVisibility(View.VISIBLE);
                        holder.datecircle3.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }


        holder.dayOfMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedpos = position;
                notifyDataSetChanged();

            }
        });
        if (selectedpos == position) {
            holder.dayOfMonth.setBackground(context.getDrawable(R.drawable.selctor_layout));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }
    public void setSelected(int selectedpos){
        this.selectedpos = selectedpos;
    }

    @Override
    public int getItemCount() {
        return dayOfMonth.size();
    }
    public interface OnItemListener
    {
        void onItemClick(int position, String dayText);
    }


}
