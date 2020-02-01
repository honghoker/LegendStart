package com.example.myfragment1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

//    private FragmentManager fragmentManager;
    private Fragment fc, fd;

    //프래그먼트는  xml레이아웃 파일 하나랑 자바소스 파일 하나로 정의할 수 있다.
    //이게 하나의 뷰처럼 쓸 수 있는데 뷰하고 약간 다른특성들이 있다.
    //엑티비티를 본떠 만들었기 떄문에 프래그먼트 매니저가 소스코드에서 담당한다.
    MainFragment fragment1;
    MenuFragment fragment2;
    private boolean menuFlag = true;
    //프래그먼트 유지
    private FragmentManager fragmentManager;
    private Fragment fa, fb = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프래그먼트는 뷰와 다르게 context를 매개변수로 넣어줄 필요가 없다.
/*
        fragment1 = new MainFragment();
        fragment2 = new MenuFragment();
        onFragmentChange(0);
*/
        toolbar = findViewById(R.id.toolbar);
//        setActionBar(toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //프래그먼트 유지

        fragmentManager = getSupportFragmentManager();
        fa = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fa).commit();


        //상단 메뉴
//
//        //액션바 타이틀 변경하기
//        getSupportActionBar().setTitle("App");
//        //액션바 배경색 변경
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
//        //홈버튼 표시
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 home


    }


    public void onButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.btnMain:
                //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();/*프래그먼트 매니저가 프래그먼트를 담당한다!*/
                if (fa == null) {
                    Toast.makeText(this, "맵 생성 전", Toast.LENGTH_SHORT).show();
                    fa = new MenuFragment();
                    fragmentManager.beginTransaction().add(R.id.frameLayout, fa).commit();
                }

                if (fa != null) {
                    fragmentManager.beginTransaction().show(fa).commit();
                    Toast.makeText(this, "맵 생성완료", Toast.LENGTH_SHORT).show();
                }
                if (fb != null) fragmentManager.beginTransaction().hide(fb).commit();

                break;

            case R.id.btnMenu:
                //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment2).commit();/replace 는 닫고 새로 열어주는 거
                if (fb == null) {
                    Toast.makeText(this, "리스트 생성 전", Toast.LENGTH_SHORT).show();

                    fb = new MenuFragment();
                    fragmentManager.beginTransaction().add(R.id.frameLayout, fb).commit();
                }

                if (fa != null) {
                    fragmentManager.beginTransaction().hide(fa).commit();
                    Toast.makeText(this, "리스트 생성완료", Toast.LENGTH_SHORT).show();

                }
                if (fb != null) {
                    fragmentManager.beginTransaction().show(fb).commit();

                }
                break;
            default:
                break;
        }
    }


    //프래그먼트와 프래그먼트끼리 직접접근을하지않는다. 프래그먼트와 엑티비티가 접근함
    public void onFragmentChange(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment2).commit();
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
        return false;
    }
}
