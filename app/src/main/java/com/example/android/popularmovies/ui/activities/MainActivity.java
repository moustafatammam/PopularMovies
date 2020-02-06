package com.example.android.popularmovies.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding activityMainBinding;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this, navController);
        getSupportActionBar().setHideOnContentScrollEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }


}

