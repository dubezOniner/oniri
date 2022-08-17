package com.flexabyse.app.oniri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottomNavigationView);

        // remove the background
        navigationView.setBackground(null);

        // disable the center button, since using FAB
        navigationView.getMenu().getItem(2).setEnabled(false);

        // Create the Home fragment on first load
        replaceFragment(new HomeFragment());

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        menuItem.setChecked(true);
                        break;

                    case R.id.lucidity:
                        replaceFragment(new LucidityFragment());
                        menuItem.setChecked(true);
                        break;

                    case R.id.stats:
                        replaceFragment(new StatsFragment());
                        menuItem.setChecked(true);
                        break;

                    case R.id.settings:
                        replaceFragment(new SettingsFragment());
                        menuItem.setChecked(true);
                        break;
                }
                return false;
            }
        });
    }

    /* Replace the currently shown fragment with another */
    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }
}