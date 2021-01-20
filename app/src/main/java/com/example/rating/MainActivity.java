package com.example.rating;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.rating.ui.Rating.RatingFragment;
import com.example.rating.ui.Settings.SettingsFragment;
import com.example.rating.ui.Top.TopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    String fio;

    SessionManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Главная");
        manager = new SessionManager(getApplicationContext());
        manager.checkLogin();

        RatingFragment fragment = new RatingFragment();
        loadFragment(fragment);
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.btm_nav);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.menu_top:
                        fragment = new TopFragment();
                        setTitle("Список студентов");
                        break;
                    case R.id.menu_rating:
                        fragment = new RatingFragment();
                        setTitle("Главная");
                        break;
                    case R.id.menu_settings:
                        fragment = new SettingsFragment();
                        setTitle("Настройки");
                        break;
                }
                loadFragment(fragment);
                return true;
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}

