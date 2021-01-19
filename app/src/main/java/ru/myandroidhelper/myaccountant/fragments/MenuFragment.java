package ru.myandroidhelper.myaccountant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.myandroidhelper.myaccountant.R;
import ru.myandroidhelper.myaccountant.activity.NoteAddActivity;
import ru.myandroidhelper.myaccountant.adapters.MenuAdapter;


public class MenuFragment extends Fragment implements View.OnClickListener {



    ViewGroup viewGroup;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    ArrayList<String> note, calculator, calendar;

    public void NoteFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Раздуть макет для этого фрагмента
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recyclerMenu);


        showMenuList();


        return viewGroup;
    }


    public void showMenuList(){

        note = new ArrayList<>();
        calculator= new ArrayList<>();
        calendar = new ArrayList<>();
        note.add("Заметки");
        calculator.add("Калькулятор");
        calendar.add("Календарь");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        menuAdapter = new MenuAdapter(getActivity(), getContext(), note,calculator,calendar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(menuAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.note_text:
                startActivity(new Intent(getContext(), NoteAddActivity.class));

        }
    }
}

