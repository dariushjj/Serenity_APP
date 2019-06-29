package com.serenity.view.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.serenityapp.R;
import com.google.android.material.navigation.NavigationView;

import static com.example.util.ConstantUtil.HOME;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.home_drawer_layout);
        NavigationView navigationView = findViewById(R.id.home_navigation_view);
        Button menuBtn = findViewById(R.id.title_menu_button);
        TextView textView = findViewById(R.id.title_menu_text);
        textView.setText(HOME);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_music:
                        //intent = new Intent(HomeActivity.this, )
                        break;
                    case R.id.menu_timer:
                        break;
                    case R.id.menu_sleep:
                        break;
                    case R.id.menu_others:
                        break;
                    case R.id.menu_settings:
                        break;
                    default:

                }
                //startActivity(intent);
                return true;
            }
        });
    }
}
