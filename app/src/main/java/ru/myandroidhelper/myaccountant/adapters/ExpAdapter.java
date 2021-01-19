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
import ru.myandroidhelper.myaccountant.activity.UpdateExpActivity;
import ru.myandroidhelper.myaccountant.listener.ListItemClickListener;
import ru.myandroidhelper.myaccountant.model.ExpensesModel;


public class ExpAdapter extends RecyclerView.Adapter<ExpAdapter.MyViewHolder> {

    private static Context context;
    private Activity activity;
    private ArrayList<ExpensesModel> exp_id, nameExp, expenses, categoryExp, textDate;
    private ListItemClickListener itemClickListener;
    LinearLayout mainLayout;



    public ExpAdapter(Activity activity, Context context, ArrayList exp_id, ArrayList nameExp, ArrayList expenses, ArrayList categoryExp, ArrayList textDate) {
        this.activity = activity;
        this.context = context;
        this.exp_id = exp_id;
        this.nameExp = nameExp;
        this.expenses = expenses;
        this.categoryExp = categoryExp;
        this.textDate = textDate;
        //this.total = total;
    }
    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ExpAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row_exp, parent, false);
        return new MyViewHolder(view, viewType, itemClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ExpAdapter.MyViewHolder holder, final int position) {
        //holder.exp_id.setText(String.valueOf(id.get(position)));
        holder.name_txt.setText(String.valueOf(nameExp.get(position)));
        holder.expenses_txt.setText(String.valueOf(expenses.get(position)));
        holder.category_txt.setText(String.valueOf(categoryExp.get(position)));
        holder.textDate.setText(String.valueOf(textDate.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateExpActivity.class);
                //intent.putExtra("_id", String.valueOf(exp_id.get(position)));
               intent.putExtra("name", String.valueOf(nameExp.get(position)));
               intent.putExtra("expenses", String.valueOf(expenses.get(position)));
               intent.putExtra("categoryExp", String.valueOf(categoryExp.get(position)));
               intent.putExtra("date", String.valueOf(textDate.get(position)));
               activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nameExp.size();
    }


        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView name_txt, expenses_txt,category_txt, textDate;
        LinearLayout mainLayout;
        private ListItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);
            //exp_id = itemView.findViewById(R.id.id_txt_exp);
            name_txt = itemView.findViewById(R.id.name_txt_exp);
            expenses_txt = itemView.findViewById(R.id.exp_txt);
            category_txt = itemView.findViewById(R.id.categoryExpenses);
            textDate = itemView.findViewById(R.id.data_txt_exp);
            mainLayout = itemView.findViewById(R.id.mainLayoutExp);

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

