package com.example.calendarapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements  CalendarAdapter.OnItemListener {

    private TextView mothYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selctedDate, currentToday;
    public String currentDate;
    public View dateCircle, datecircle2, datecircle3;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateCircle = findViewById(R.id.dateCircle);
        datecircle2 = findViewById(R.id.dateCircle2);
        datecircle3 = findViewById(R.id.dateCircle3);

        initWidgets();
        selctedDate = LocalDate.now();
        currentToday = selctedDate;
        setMonthView();

        currentDate = String.valueOf(selctedDate);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        mothYearText.setText(monthYearFromDate(selctedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selctedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(getApplicationContext(), daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selctedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(yearMonth) + "-" + String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calaendarRecyclerView);
        mothYearText = findViewById(R.id.monthYearTV);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view) {
        selctedDate = selctedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view) {
        selctedDate = selctedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {

    }
}
