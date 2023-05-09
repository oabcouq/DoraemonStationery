package com.example.sellclothesapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.sellclothesapp.databinding.ActivityMainBinding;
import com.example.sellclothesapp.model.User;
import com.example.sellclothesapp.ui.fragment.AccountFragment;
import com.example.sellclothesapp.ui.fragment.CartFragment;
import com.example.sellclothesapp.ui.fragment.FavouriteFragment;
import com.example.sellclothesapp.ui.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private View mHeaderView;
    private TextView edUser;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbarr);
        nv = findViewById(R.id.nvView);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        FragmentManager manager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, homeFragment)
                .commit();

        nv.setCheckedItem(R.id.nav_HomeFragment);
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvUser);
        edUser.setText("Welcome " + User.getInstance().getName());

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                if (item.getItemId() == R.id.nav_HomeFragment) {
                    Home();
                    nv.setCheckedItem(R.id.nav_HomeFragment);
                }

                if (item.getItemId() == R.id.nav_AccountFragment) {
                    Account();
                    nv.setCheckedItem(R.id.nav_AccountFragment);
                }

                if (item.getItemId() == R.id.nav_CartFragment) {
                    Cart();
                    nv.setCheckedItem(R.id.nav_CartFragment);
                }

                if (item.getItemId() == R.id.nav_FavouriteFragment) {
                    Favorite();
                    nv.setCheckedItem(R.id.nav_FavouriteFragment);
                }
                drawer.closeDrawers();
                return false;
            }
        });


    }

    private void Home() {
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Home");
        HomeFragment homeFragment = new HomeFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, homeFragment)
                .commit();
    }

    private void Cart() {
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Cart");
        CartFragment cartFragment = new CartFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, cartFragment)
                .commit();
    }

    private void Favorite() {
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Favorite");
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, favouriteFragment)
                .commit();
    }

    private void Account() {
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Account");
        AccountFragment accountFragment = new AccountFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, accountFragment)
                .commit();
    }

//    //Out Ứng Dụng
//    boolean doubleBackToExitPressedOnce = false;
//
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Click 2 Lần Để Thoát", Toast.LENGTH_SHORT).show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce = false;
//            }
//        }, 2000);
//    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}