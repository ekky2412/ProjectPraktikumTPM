package com.example.projectpraktikummobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.projectpraktikummobile.R;
import com.example.projectpraktikummobile.fragment.AboutFragment;
import com.example.projectpraktikummobile.fragment.CekOngkirFragment;
import com.example.projectpraktikummobile.fragment.HistoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        navigationView = findViewById(R.id.bottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(new CekOngkirFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cekOngkir:
                loadFragment(new CekOngkirFragment());
                break;

            case R.id.menu_history:
                loadFragment(new HistoryFragment());
                break;

            case R.id.menu_about:
                loadFragment(new AboutFragment());
                break;
        }

        return true;
    }

    private boolean loadFragment(Fragment selectedFragment) {
        if (selectedFragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout,selectedFragment)
                    .commit();
            return true;
        }
        else{
            return false;
        }
    }
}