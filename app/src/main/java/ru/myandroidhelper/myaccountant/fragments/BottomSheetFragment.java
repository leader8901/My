package ru.myandroidhelper.myaccountant.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ru.myandroidhelper.myaccountant.R;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private TextView TextCalendar, TextReminder, TextIncome,TextExpenses;
    private ViewGroup viewGroup;
    private BottomSheetListener mListener;

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewGroup =  (ViewGroup) inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
        TextCalendar = viewGroup.findViewById(R.id.TextCalendar);

        TextCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return viewGroup;
    }




    public interface BottomSheetListener{
        void onButtonClicked(String text);
    }

}
