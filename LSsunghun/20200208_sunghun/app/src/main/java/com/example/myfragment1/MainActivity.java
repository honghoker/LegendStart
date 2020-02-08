package com.example.myfragment1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar;
        Spinner spinner;
        RecyclerView recyclerView;
        Adapter adapter;
        ArrayList<String> items;
        ArrayList<String> items_des;
        ArrayList<String[]> test_Tag = new ArrayList<String[]>();
        boolean recyFrag = false;
        boolean test_btnFrag = false;

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
    View mView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list,menu);
        return true;
    }

    // Title 하나당 Tag 여러개 class
    class a{
      String cl_Title;
      ArrayList<String> cl_Tag;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //프래그먼트는 뷰와 다르게 context를 매개변수로 넣어줄 필요가 없다.
/*
        fragment1 = new MainFragment();
        fragment2 = new MenuFragment();
        onFragmentChange(0);
*/
        toolbar = findViewById(R.id.toolbar);
        spinner = findViewById(R.id.spinner);
//        setActionBar(toolbar);
        toolbar.setTitle("여기는 됌?");
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


        String[] tag1 = {"소고기","안녕하세요","김성훈입니다","방가방가","소고소고소고소고"};
        String[] tag2 = {"1"};
        String[] tag3 = {"1","2","3","4"};
        String[] tag4 = {"1","2","3"};
        String[] tag5 = {"1","2"};

        items = new ArrayList<>();
//        items_des = new ArrayList<>();
        items.add("Frist");
        items.add("Two");
        items.add("Third");
        items.add("Four");
        items.add("FIve");

        test_Tag.add(tag1);
        test_Tag.add(tag2);
        test_Tag.add(tag3);
        test_Tag.add(tag4);
        test_Tag.add(tag5);
//        items_des.add("Frist");
//        items_des.add("Two");
//        items_des.add("Third");
//        items_des.add("Four");
//        items_des.add("FIve");

        recyclerView = findViewById(R.id.recyclerView);
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

//        recyclerView.scrollToPosition(items.size()-1);
        adapter = new Adapter(getApplicationContext(), items,test_Tag);
//        adapter = new Adapter(getApplicationContext(), items,items_des);
        recyclerView.setAdapter(adapter);

        //recyclerview pos 위치 값 가져와서 items 리스트 안에 있는 pos 위치에 있는 Title 가져옴
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                toolbar.setTitle(items.get(pos));
                Toast.makeText(getApplicationContext(),items.get(pos),Toast.LENGTH_SHORT).show();
            }
        });

        mView = findViewById(R.id.frameLayout);
        recyclerView.setVisibility(mView.GONE);
        final TextView recy_text= (TextView) findViewById(R.id.recy_text);
        recy_text.setVisibility(mView.GONE);
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN && recyFrag == false){
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
                    recyclerView.setAnimation(animation);
                    recy_text.setAnimation(animation);
                    recyclerView.setVisibility(mView.VISIBLE);
                    recy_text.setVisibility(mView.VISIBLE);
                    Toast.makeText(getApplicationContext(),"spinner 터치",Toast.LENGTH_LONG).show();
                    recyFrag = true;
                }
                else if(event.getAction() == MotionEvent.ACTION_DOWN && recyFrag == true){
                    Animation animationH = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translatehide);
                    recyclerView.setAnimation(animationH);
                    recy_text.setAnimation(animationH);
                    recyclerView.setVisibility(mView.GONE);
                    recy_text.setVisibility(mView.GONE);
                    Toast.makeText(getApplicationContext(),"spinner 터치",Toast.LENGTH_LONG).show();
                    recyFrag = false;
                }

//                Intent intent = new Intent(this, homeActivity.class);
//                startActivity(intent);
//                CustomIntent.customType(this,"left-to-right");

////
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                }
                return true;
            }
        });

//        toolbar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    Toast.makeText(getApplicationContext(),"toolabr 터치",Toast.LENGTH_LONG).show();
//                }
//                return true;
//            }
//        });

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
        int id = menuItem.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            Intent intent = new Intent(this, homeActivity.class);
            startActivity(intent);
            CustomIntent.customType(this,"left-to-right");
        } else if (id == R.id.setting) {

        } else if (id == R.id.todo) {

        } else if (id == R.id.excel) {

        } else if (id == R.id.call) {

        } else if (id == R.id.help) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
