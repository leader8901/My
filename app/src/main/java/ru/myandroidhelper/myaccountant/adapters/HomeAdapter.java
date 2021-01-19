package ru.myandroidhelper.myaccountant.adapters;

import android.app.Activity;
import android.content.Context;
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
import ru.myandroidhelper.myaccountant.listener.ListItemClickListener;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


    private static Context context;
    private Activity activity;
    private ArrayList<String> SumIncome;
    private ArrayList<String> SumExp;
    private ListItemClickListener itemClickListener;
    LinearLayout mainLayout;
    int i = 0;

    public HomeAdapter(Activity activity, Context context,ArrayList sum_income,ArrayList sumExp){
        this.context = context;
        this.activity = activity;
        this.SumIncome = sum_income;
        this.SumExp = sumExp;


    }


    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row_home, parent, false);
        return new HomeAdapter.MyViewHolder(view, viewType, itemClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, int i) {
        holder.sumIncome.setText(String.valueOf(SumIncome.get(i)));
        holder.sumExp.setText(String.valueOf(SumExp.get(i)));




    }

    @Override
    public int getItemCount() {
        return SumIncome.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public static TextView sumIncome,sumExp;
        private ListItemClickListener itemClickListener;
        LinearLayout layoutIncome,layoutExp,layoutTotal;

        public MyViewHolder(@NonNull View itemView, int viewType, ListItemClickListener itemClickListener) {

            super(itemView);

            sumIncome = itemView.findViewById(R.id.sumIncome);
            layoutIncome = itemView.findViewById(R.id.layoutIncome);
            sumExp = itemView.findViewById(R.id.sumExp);
            layoutExp = itemView.findViewById(R.id.layoutExp);
            layoutTotal = itemView.findViewById(R.id.layoutTotal);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            layoutIncome.setAnimation(translate_anim);
            Animation translate_anim2 = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            layoutExp.setAnimation(translate_anim2);
            Animation translate_anim3 = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            layoutTotal.setAnimation(translate_anim3);

        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getLayoutPosition(), view);
            }
        }

    }
}