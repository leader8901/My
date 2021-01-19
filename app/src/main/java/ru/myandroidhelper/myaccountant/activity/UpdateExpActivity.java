package ru.myandroidhelper.myaccountant.activity;

import androidx.appcompat.app.ActionBar;
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
import ru.myandroidhelper.myaccountant.db.MyDatabaseHelper;


public class UpdateExpActivity extends AppCompatActivity {

    private Button buttonDelete, buttonUpdate;
    String id_exp, nameExp, expenses;
    EditText nameExp_input, expenses_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_exp);


        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        buttonUpdate = findViewById(R.id.UpdBtnExp);
        buttonDelete = findViewById(R.id.DeleteBtnExp);

        nameExp_input = findViewById(R.id.UpdateNameExp);
        expenses_input = findViewById(R.id.input_expenses);


        // Сначала мы называем это
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nameExp);
        }


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateExpActivity.this);
                nameExp = nameExp_input.getText().toString().trim();
                expenses = expenses_input.getText().toString().trim();
                myDB.updateExpData(id_exp, nameExp, expenses);
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    confirmDialog();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Не удалось",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }
        });

    }


    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить: " + nameExp + " ?");
        builder.setMessage("Вы точно хотите удалить: " + nameExp + " ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateExpActivity.this);
                try {
                    myDB.deleteOneRowExp(nameExp);
                    finish();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Не удалось",Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


    void getAndSetIntentData(){
        if(getIntent().hasExtra("name") &&
                getIntent().hasExtra("expenses")){
            // Получение данных из намерений
            id_exp = getIntent().getStringExtra("id");
            nameExp = getIntent().getStringExtra("name");
            expenses = getIntent().getStringExtra("expenses");
            // Установка данных намерений
            nameExp_input.setText(nameExp);
            expenses_input.setText(expenses);
        }else{
            Toast.makeText(this, "Нет Данных.", Toast.LENGTH_SHORT).show();
        }
    }

}
