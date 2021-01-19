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
import ru.myandroidhelper.myaccountant.adapters.ExpAdapter;
import ru.myandroidhelper.myaccountant.db.MyDatabaseHelper;
import ru.myandroidhelper.myaccountant.model.Item;


public class ExpFragment extends Fragment {
    private ArrayList<Item> arrayList = new ArrayList<Item>();
    private static final String TAG = "myLog";
    private RecyclerView mRecyclerView;
    private MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> id_exp, name, expenses, categoryExp, textDate;
    private Cursor cursor;
    private ExpAdapter ExpAdapter;
    private ImageView imgEmpty;
    private TextView noDate;


    ViewGroup viewGroup;



    public void ExpFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Раздуть макет для этого фрагмента
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_exp, container, false);
        mRecyclerView =  viewGroup.findViewById(R.id.recyclerExp);
        imgEmpty =  viewGroup.findViewById(R.id.empty_img_exp);
        noDate =  viewGroup.findViewById(R.id.no_data_exp);
        storeDataInArrays();
        return viewGroup;
    }

    void storeDataInArrays() {
        myDatabaseHelper = new MyDatabaseHelper(getActivity());
        id_exp = new ArrayList<>();
        name = new ArrayList<>();
        expenses = new ArrayList<>();
        categoryExp = new ArrayList<>();
        textDate = new ArrayList<>();


        Cursor cursor = myDatabaseHelper.readExpData();
        if (cursor.getCount() == 0) {
            imgEmpty.setVisibility(View.VISIBLE);
            noDate.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                id_exp.add(cursor.getString(0));
                name.add(cursor.getString(1));
                expenses.add(cursor.getString(2));
                categoryExp.add(cursor.getString(3));
                textDate.add(cursor.getString(4));
            }
            imgEmpty.setVisibility(View.GONE);
            noDate.setVisibility(View.GONE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ExpAdapter = new ExpAdapter(getActivity(), getContext(), id_exp, name, expenses, categoryExp, textDate);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(ExpAdapter);
    }
}