package ru.myandroidhelper.myaccountant.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


import ru.myandroidhelper.myaccountant.R;
import ru.myandroidhelper.myaccountant.adapters.IncomeAdapter;
import ru.myandroidhelper.myaccountant.constans.Constans;
import ru.myandroidhelper.myaccountant.db.MyDatabaseHelper;
import ru.myandroidhelper.myaccountant.model.Item;


public class IncomeFragment extends Fragment {

    private ImageView imgEmpty;
    private TextView noDate;
    ViewGroup viewGroup;
    private ArrayList<Item> arrayList = new ArrayList<Item>();
    private static final String TAG = "myLog";
    private RecyclerView recyclerView;
    private MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> id_income, name, income, categoryIn, textDate;
    private Cursor cursor;
    private IncomeAdapter adapter;
    ArrayList<String> date = new ArrayList<String>();


    public void IncomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Раздуть макет для этого фрагмента
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_income, container, false);

        //imgEmpty = (ImageView) viewGroup.findViewById(R.id.empty_img);
        noDate = (TextView) viewGroup.findViewById(R.id.no_data);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recyclerIncome);
        storeDataInArrays();
        return viewGroup;

    }

    void storeDataInArrays() {
        myDatabaseHelper = new MyDatabaseHelper(getActivity());
        id_income = new ArrayList<>();
        name = new ArrayList<>();
        income = new ArrayList<>();
        categoryIn = new ArrayList<>();
        textDate = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.readIncomeData();
        if (cursor.getCount() == 0) {
            //imgEmpty.setVisibility(View.VISIBLE);
            noDate.setVisibility(View.VISIBLE);

        } else {
            while (cursor.moveToNext()) {
                id_income.add(cursor.getString(0));
                name.add(cursor.getString(1));
                income.add(cursor.getString(2));
                categoryIn.add(cursor.getString(3));
                textDate.add(cursor.getString(4));


            }
            //imgEmpty.setVisibility(View.GONE);
            noDate.setVisibility(View.GONE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new IncomeAdapter(getActivity(), getContext(), id_income, name, income, categoryIn, textDate);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}


