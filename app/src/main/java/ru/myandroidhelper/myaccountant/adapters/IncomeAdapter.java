package ru.myandroidhelper.myaccountant.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import ru.myandroidhelper.myaccountant.R;
import ru.myandroidhelper.myaccountant.activity.UpdateActivityIncome;
import ru.myandroidhelper.myaccountant.listener.ListItemClickListener;


public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.MyViewHolder> {

    private static Context context;
    private Activity activity;
    public ArrayList id_income, nameIn, income,categoryIn;
    private ListItemClickListener itemClickListener;
    LinearLayout mainLayout;
    ArrayList<String> year = new ArrayList<String>();
    ArrayList<String> textDate = new ArrayList<String>();



    public IncomeAdapter(Activity activity, Context context, ArrayList id_income, ArrayList nameIn, ArrayList income,ArrayList categoryIn,ArrayList textDate) {
        this.activity = activity;
        this.context = context;
        this.id_income = id_income;
        this.nameIn = nameIn;
        this.income = income;
        this.categoryIn = categoryIn;
        this.textDate = textDate;
       // this.year = year;


    }


    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row_income, parent, false);
        return new MyViewHolder(view, viewType, itemClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.MyViewHolder holder, final int i) {
            //holder.id_txt.setText(String.valueOf(id.get(position)));
              holder.name_txt.setText(String.valueOf(nameIn.get(i)));
              holder.income_txt.setText(String.valueOf(income.get(i)));
              holder.categoryIncome.setText(String.valueOf(categoryIn.get(i)));
              holder.textDate.setText(String.valueOf(textDate.get(i)));
            //Recyclerview onClickListener
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateActivityIncome.class);
                    intent.putExtra("name", String.valueOf(nameIn.get(i)));
                    intent.putExtra("income", String.valueOf(income.get(i)));
                    intent.putExtra("categoryIn", String.valueOf(categoryIn.get(i)));
                    intent.putExtra("date", String.valueOf(textDate.get(i)));
                    activity.startActivityForResult(intent, 1);


                }


            });




        }

        @Override
        public int getItemCount() {
            // return (null != id ? id.size() : 0);
            //return (book_id == null) ? 0 : book_id.size();
            return nameIn.size();
        }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public static TextView name_txt, income_txt, textDate,year,categoryIncome;
        LinearLayout mainLayout;
        private ListItemClickListener itemClickListener;


        MyViewHolder(@NonNull View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);
            //id_txt = itemView.findViewById(R.id.id_txt);
            name_txt = itemView.findViewById(R.id.name_txt_income);
            income_txt = itemView.findViewById(R.id.income_txt);
           // categoryIncome = itemView.findViewById(R.id.categoryIncome);
            textDate = itemView.findViewById(R.id.data_txt_income);
            mainLayout = itemView.findViewById(R.id.mainLayoutIncome);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }
        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getLayoutPosition(), view);
            }

        }

    }

}

