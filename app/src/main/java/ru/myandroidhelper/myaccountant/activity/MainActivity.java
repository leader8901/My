package ru.myandroidhelper.myaccountant.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import ru.myandroidhelper.myaccountant.R;
import ru.myandroidhelper.myaccountant.db.MyDatabaseHelper;
import ru.myandroidhelper.myaccountant.fragments.BottomSheetFragment;
import ru.myandroidhelper.myaccountant.fragments.ExpFragment;
import ru.myandroidhelper.myaccountant.fragments.HomeFragment;
import ru.myandroidhelper.myaccountant.fragments.IncomeFragment;
import ru.myandroidhelper.myaccountant.fragments.MenuFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button fabAddIncome, fabExp, fabNote;
    private FloatingActionButton fabMenu;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    private Float translationY = 100f;
    private Toolbar toolbar;
    private BottomSheetFragment bottomSheetFragment;
    private LinearLayout bottom_sheet;
    private int chackedCount = 0;
    private int theme;
    private RecyclerView recyclerView;
    private TextView noteText, calculatorText,calendarText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        setFragmentTransaction(new HomeFragment(), "Расходы и Доходы");

        fabMenu = findViewById(R.id.fabMenu);
        fabAddIncome = findViewById(R.id.fabIncome);
        fabExp = findViewById(R.id.fabExp);
        noteText = findViewById(R.id.note_text);
        calculatorText = findViewById(R.id.calculator_text);
        calendarText = findViewById(R.id.calendar_text);
        //fabNote = findViewById(R.id.fabNote);

        initFabMenu();

    }


    // отображение диалогового окна нижнего листа
    public void showBottomSheetDialogFragment () {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }




    private void initFabMenu() {
        fabMenu.setAlpha(1f);
        fabAddIncome.setAlpha(0f);
        fabExp.setAlpha(0f);
        //fabNote.setAlpha(0f);

        fabMenu.setTranslationY(translationY);
        fabAddIncome.setTranslationY(translationY);
        fabExp.setTranslationY(translationY);
        //fabNote.setTranslationY(translationY);

        fabMenu.setOnClickListener(this);
        fabAddIncome.setOnClickListener(this);
        fabExp.setOnClickListener(this);
        //fabNote.setOnClickListener(this);
    }
    private void openMenu() {
        isMenuOpen = !isMenuOpen;
        fabMenu.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();
        fabAddIncome.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabExp.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        //fabNote.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }
    private void closeMenu() {
        isMenuOpen = !isMenuOpen;
        fabMenu.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();
        fabAddIncome.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabExp.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
       // fabNote.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabMenu:
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fabIncome:
                startActivity(new Intent(getApplicationContext(), AddActivityIncome.class));
                closeMenu();
                break;
            case R.id.fabExp:
                startActivity(new Intent(getApplicationContext(), AddActivityExp.class));
                closeMenu();



        }

       /* fabNote = (FloatingActionButton) findViewById(R.id.fabNote);
        fabNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeMenu();
            }
        });*/


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                confirmDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            closeMenu();
                            break;
                        case R.id.nav_income:
                            selectedFragment = new IncomeFragment();
                            closeMenu();
                            break;
                        case R.id.nav_exp:
                            selectedFragment = new ExpFragment();
                            closeMenu();
                            break;
                        case R.id.nav_more:
                            selectedFragment = new MenuFragment() ;
                            closeMenu();
                           // showBottomSheetDialogFragment();
                           // finish();
                           break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить все?");
        builder.setMessage("Вы точно хотите удалить все?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllDataIncome();
                myDB.deleteAllDataExp();
                //Refresh Activity
                Toast.makeText(getApplicationContext(),"Все удалено!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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

    @Override
    public void onBackPressed() {
            new AlertDialog.Builder(this)
                    .setMessage("Выйти из приложение?")
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            recreate();
                        }
                    })
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            MainActivity.super.onBackPressed();
                        }
                    }).create().show();
        }


    private void setFragmentTransaction(Fragment fragment, String title) {
        setTitle(title);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}








