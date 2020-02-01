package com.example.drawer_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private FragmentManager fragmentManager;
    private Fragment fa, fb;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu_list,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
//        setActionBar(toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        맨 첫 화면 null 일때 home_frag 띄어라
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_home()).commit();
            navigationView.setCheckedItem(R.id.home_frag);
        }

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
        }
        else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.home:
                if(fa == null){
                    fa = new Fragment_home();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_home()).commit();
                }
                if(fa!=null)  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fa).commit();
                if(fb!=null)  getSupportFragmentManager().beginTransaction().hide(fb).commit();
                break;
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_home()).commit();
//                break;
            case R.id.setting:
                if(fb == null){
                    fb = new Fragment_setting();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_setting()).commit();
                }
                if(fa!=null)  getSupportFragmentManager().beginTransaction().hide(fa).commit();
                if(fb!=null)  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fb).commit();
                break;
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_setting()).commit();
//                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}
