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


import ru.myandroidhelper.myaccountant.R;
import ru.myandroidhelper.myaccountant.db.MyDatabaseHelper;


public class AddActivityIncome extends AppCompatActivity {

    private Button addBtn;
    private EditText edName, edIncome, edCategoryIn;
    private String TAG = "Жизненный цикл";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        //Определяем тулбар и вводим имя в тулбар
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(getString(R.string.app_income));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        edName = findViewById(R.id.edTextNameInc);
        edName.addTextChangedListener(loginTextWatcher);

        edIncome = findViewById(R.id.edTextIncome);
        edIncome.addTextChangedListener(loginTextWatcher);

       // edCategoryIn = findViewById(R.id.categoryIncome);
       // edCategoryIn.addTextChangedListener(loginTextWatcher);
        addBtn = findViewById(R.id.addIncomeBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivityIncome.this);
                        String nameIn = edName.getText().toString();
                        String categoryIn = edCategoryIn.getText().toString();
                        int income = Integer.parseInt(edIncome.getText().toString());
                        if (edCategoryIn.equals(0)) {
                            edCategoryIn.setText("");
                          }

                        myDB.addIncome(nameIn, income, categoryIn, getDateTime());
                        edName.setText("");
                        edIncome.setText("");
                        edCategoryIn.setText("");
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

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
                Intent intent = new Intent(AddActivityIncome.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private String getDateTime() {
        // Log.d(LOG_TAG, "--- Дата: ---");
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm", Locale.getDefault());
        Date data_income = new Date();
        return dateFormat.format(data_income);
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = edName.getText().toString().trim();
            String incomeInput = edIncome.getText().toString().trim();
            String categoryInput = edCategoryIn.getText().toString().trim();

            addBtn.setEnabled(!nameInput.isEmpty() && !incomeInput.isEmpty() && !categoryInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

}