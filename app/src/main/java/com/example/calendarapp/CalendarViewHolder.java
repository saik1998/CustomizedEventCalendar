package com.example.calendarapp;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;
    public View dateCircle,datecircle2,datecircle3;
    LinearLayout mainContaint;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        mainContaint = itemView.findViewById(R.id.mainContaint);
        dateCircle=itemView.findViewById(R.id.dateCircle);
        datecircle2 = itemView.findViewById(R.id.dateCircle2);
        datecircle3 = itemView.findViewById(R.id.dateCircle3);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(),(String) dayOfMonth.getText());

    }
}
