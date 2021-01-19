package ru.myandroidhelper.myaccountant.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import ru.myandroidhelper.myaccountant.R;

public class CalendarActivity extends AppCompatActivity {

    // Используем объект CalendarView:
    CalendarView mCalendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Связываемся с нашим календариком:
        mCalendarView = (CalendarView) findViewById(R.id.calendar_view);
        //Настраиваем слушателя смены даты:
        //Настраиваем слушателя смены даты:
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            // Описываем метод выбора даты в календаре:
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                // При выборе любой даты отображаем Toast сообщение с данными о выбранной дате (Год, Месяц, День):
                Toast.makeText(getApplicationContext(),
                        "Год: " + year + "\n" +
                                "Месяц: " + month + "\n" +
                                "День: " + dayOfMonth,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}