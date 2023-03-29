package info.sanaebadi.weatheractivity.ui.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import info.sanaebadi.weatheractivity.R;
import info.sanaebadi.weatheractivity.base.BaseActivity;
import info.sanaebadi.weatheractivity.databinding.ActivityMainBinding;
import info.sanaebadi.weatheractivity.util.Const;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavController navController;

    boolean doubleBackToExitPressedOnce = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

        drawerLayout = binding.drawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(binding.naviagationView);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.homeFragment:
                navController.navigate(R.id.homeFragment);
                break;

            case R.id.profileFragment:
                navController.navigate(R.id.profileFragment);
                break;

            case R.id.settingFragment:
                navController.navigate(R.id.settingFragment);
                break;

            case R.id.loginFragment:
                navController.navigate(R.id.loginFragment);
                break;
        }

        drawerLayout.closeDrawers();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.logout:
                logout();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void showExitDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you sure to Exit?")
                .setMessage("Exiting will call finish() method")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext(), "Nothing Happened", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void logout() {
        try {
            if (sharedPreferences == null) {
                sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME, MODE_PRIVATE);

                sharedEditor = sharedPreferences.edit();
                sharedEditor.putString(Const.USER_NAME_KEY, "");
                sharedEditor.commit();

                navController.navigate(R.id.action_homeFragment_to_loginFragment2);
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(binding.naviagationView)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        showExitDialog();

        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}