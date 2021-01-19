package ru.myandroidhelper.myaccountant.fragments;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ru.myandroidhelper.myaccountant.R;
import ru.myandroidhelper.myaccountant.adapters.HomeAdapter;
import ru.myandroidhelper.myaccountant.db.MyDatabaseHelper;
import ru.myandroidhelper.myaccountant.model.ItemHome;


public class HomeFragment<myDatabaseHelper> extends Fragment {


    ViewGroup viewGroup;
    private MyDatabaseHelper myDatabaseHelper;
    // private ArrayList<ItemHome> arrayList = new ArrayList<ItemHome>();
    private RecyclerView recyclerView;
    ArrayList<String> sum_income, sum_exp;
    private TextView sumIncome;
    private Cursor cursor;
    private HomeAdapter homeAdapter;
    TextView jobCategory, fameleCategory, fudCategory, CarSharing;


    PieChart pieChart;


    public void HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Раздуть макет для этого фрагмента
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        //tvPython = (TextView) viewGroup.findViewById(R.id.recyclerHome);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recyclerHome);
        pieChart = (PieChart) viewGroup.findViewById(R.id.piechart);
        sumIncome = (TextView) viewGroup.findViewById(R.id.sumIncome);


        // jobCategory = (TextView) viewGroup.findViewById(R.id.tvR);
        // fameleCategory = findViewById(R.id.tvPython);
        //  tvCPP = findViewById(R.id.tvCPP);
        // tvJava = findViewById(R.id.tvJava);
        // pieChart = findViewById(R.id.piechart);
        SumTotalIncome();
        //addPieChart();
        return viewGroup;

    }

    void SumTotalIncome() {
        myDatabaseHelper = new MyDatabaseHelper(getActivity());
        sum_income = new ArrayList<>();
        sum_exp = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.SumIncome();
        Cursor cursor1 = myDatabaseHelper.SumExp();
        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                sum_income.add(cursor.getString(0));
            }
           // sumIncome.setVisibility(View.GONE);
        }
        if (cursor1.getCount() == 0) {
            cursor1.getString(0);
        } else {
            while (cursor1.moveToNext()) {
                sum_exp.add(cursor1.getString(0));
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        homeAdapter = new HomeAdapter(getActivity(), getContext(), sum_income, sum_exp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeAdapter);

    }


    private void addPieChart() {
        myDatabaseHelper = new MyDatabaseHelper(getActivity());
        jobCategory.setText(Integer.toString(40));
        fameleCategory.setText(Integer.toString(40));
        fudCategory.setText(Integer.toString(40));
        CarSharing.setText(Integer.toString(40));


    }
}

        //tvJava.setText(Integer.toString(60));

        // Set the data and color to the pie chart
    /*    pieChart.addPieSlice(
                new PieModel(
                        "R",
                      //  Integer.parseInt(sum_income.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Python",
                        Integer.parseInt(tvPython.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "C++",
                        Integer.parseInt(tvCPP.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Java",
                        Integer.parseInt(tvJava.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }*/


