package ru.myandroidhelper.myaccountant.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



import ru.myandroidhelper.myaccountant.db.MyDatabaseHelper;
import ru.myandroidhelper.myaccountant.R;


public class AddActivityExp extends AppCompatActivity {


    private Button addBtnExp;
    private EditText editNameExp, editTextExp, editTextСategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exp);


        //Определяем тулбар и вводим имя в тулбар
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(getString(R.string.app_exp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        editNameExp = findViewById(R.id.edTextNameExp);
        editNameExp.addTextChangedListener(loginTextWatcher);

       // editTextСategory = findViewById(R.id.edTextCategoryExp);
//        editTextСategory.addTextChangedListener(loginTextWatcher);

        editTextExp = findViewById(R.id.edTextExp);
        editTextExp.addTextChangedListener(loginTextWatcher);

        addBtnExp = findViewById(R.id.addExpBtn);
        //addBtnExp.addTextChangedListener(loginTextWatcher);
        addBtnExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    MyDatabaseHelper db = new MyDatabaseHelper(AddActivityExp.this);
                    String nameExp = editNameExp.getText().toString();
                    String  categoryExp = editTextСategory.getText().toString();
                    int expenses = Integer.parseInt(editTextExp.getText().toString());


                    db.addExpenses(nameExp, expenses, categoryExp, getDateTime());
                    editNameExp.setText("");
                    editTextExp.setText("");
                    editTextСategory.setText("");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Заполните поля", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

        //Медом для возвращение домой в тоолбаре
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case android.R.id.home:
                    Intent intent = new Intent(AddActivityExp.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }

        }

    private String getDateTime() {
               SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date_exp = new Date();
        return dateFormat.format(date_exp);
    }


    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = editNameExp.getText().toString().trim();
            String incomeInput = editTextExp.getText().toString().trim();
            String categoryInput = editTextСategory.getText().toString().trim();

            addBtnExp.setEnabled(!nameInput.isEmpty() && !incomeInput.isEmpty()&& !categoryInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

}
