package ru.myandroidhelper.myaccountant.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.myandroidhelper.myaccountant.R;
import ru.myandroidhelper.myaccountant.activity.NoteAddActivity;
import ru.myandroidhelper.myaccountant.listener.ListItemClickListener;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private static Context context;
    private static Activity activity;
    private ArrayList note, calendar, calculator;
    private ListItemClickListener itemClickListener;


    public MenuAdapter(Activity activity, Context context, ArrayList note, ArrayList calculator, ArrayList calendar){
        this.activity = activity;
        this.context = context;
        this.note = note;
        this.calculator = calculator;
        this.calendar = calendar;


    }
    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_row, parent, false);
        return new MenuAdapter.ViewHolder(view, viewType, itemClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        holder.noteText.setText(String.valueOf(note.get(position)));
        holder.calendarText.setText(String.valueOf(calendar.get(position)));
        holder.calculatorText.setText(String.valueOf(calculator.get(position)));
        holder.layoutNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoteAddActivity.class);

            }
        });

        holder.layoutCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.layoutCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public  TextView noteText, calendarText, calculatorText;
        LinearLayout layoutNote, layoutCalculator,layoutCalendar;
        private ListItemClickListener itemClickListener;



        public ViewHolder(@NonNull View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);
            //id_txt = itemView.findViewById(R.id.id_txt);
            noteText = itemView.findViewById(R.id.note_text);
            calculatorText = itemView.findViewById(R.id.calculator_text);
            calendarText = itemView.findViewById(R.id.calendar_text);
            layoutNote = itemView.findViewById(R.id.layoutNote);
            layoutCalculator = itemView.findViewById(R.id.layoutCalculator);
            layoutCalendar = itemView.findViewById(R.id.layoutCalendar);
            //Animate Recyclerview
           // Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
           // mainLayout.setAnimation(translate_anim);

        }


        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getLayoutPosition(), view);
            }

        }
    }
}