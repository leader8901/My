package ru.myandroidhelper.myaccountant.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.myandroidhelper.myaccountant.R;
import ru.myandroidhelper.myaccountant.constans.Constans;
import ru.myandroidhelper.myaccountant.db.MyDatabaseHelper;


public class UpdateActivityIncome extends AppCompatActivity {

    private EditText updateName, updateIncome,updateCategoryIn;
    private Button updateBtn, deleteBtn;
    String  income_id, nameIncome,categoryIncome,income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_income);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        updateName = findViewById(R.id.UpdateNameInc);
        updateIncome = findViewById(R.id.UpdateIncome);
        updateCategoryIn = findViewById(R.id.UpdateCategory);
        updateBtn = findViewById(R.id.UpdIncomeBtn);
        deleteBtn = findViewById(R.id.DeleteIncomeBtn);


        // Сначала мы называем это
        getAndSetIntentData();


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivityIncome.this);
                //Получаем данные для замены и передаем их переменны

                nameIncome = updateName.getText().toString().trim();
                income = updateIncome.getText().toString().trim();;
                categoryIncome = updateCategoryIn.getText().toString().trim();
                //Ну а теперь передаем переменные БД
                myDB.updateIncomeData(income_id, nameIncome, income,categoryIncome);
                Toast.makeText(getApplicationContext(),"Удалось",Toast.LENGTH_LONG).show();
                startActivity(new Intent(UpdateActivityIncome.this, MainActivity.class));


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Не удалось",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   confirmDialog();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Не удалось",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("nameIn") &&
                getIntent().hasExtra("income") &&
                getIntent().hasExtra("categoryIn")){
            // Получение данных из намерений
            income_id = getIntent().getStringExtra("_id");
            nameIncome = getIntent().getStringExtra("nameIn");
            income = getIntent().getStringExtra("income");
            categoryIncome =  getIntent().getStringExtra("categoryIn");
            // Установка данных намерений
            updateName.setText(nameIncome);
            updateIncome.setText(income);
            updateCategoryIn.setText(categoryIncome);

        }else{
            Toast.makeText(this, "Нет Данных.", Toast.LENGTH_SHORT).show();
        }
    }



    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить: " + nameIncome + " ?");
        builder.setMessage("Вы точно хотите удалить: " + nameIncome + " ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivityIncome.this);
                myDB.deleteOneRowIncome(income_id);
                finish();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}