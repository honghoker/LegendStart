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
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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


    //툴바 변경 플래그
    private boolean toolbarFlag = true;

    //스피너 ex
    Spinner spinners;

    //메인 디렉토리 출력
    private boolean directoryFlag = false;
    ImageView ibtn;

    //메뉴 서치
    MenuItem mSearch;


    @Override //메뉴 생성 메소드
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_list,menu);


        //프래그먼트에 따른 툴바 변경
        if (toolbarFlag) {
            inflater.inflate(R.menu.menu_list, menu);//xml로 메뉴를 만드는 부분

            //서치 바 길이 설정
            SearchView searchView = (SearchView) menu.findItem(R.id.menu_one).getActionView();
            searchView.setMaxWidth(Integer.MAX_VALUE); //서치 바 길이 최대
            searchView.setQueryHint("저장된 장소를 검색하세요."); //검색 힌트
            //검색 리스너 추가
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
                //검색어 입력시 이벤트 제어
                @Override
                public boolean onQueryTextChange(String s) {
                    Toast.makeText(getApplicationContext(), "입력중입니다.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                //검색어 완료시 이벤트 제어 --> 간단하게 Toast 메세지 출력으로
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Toast.makeText(getApplicationContext(), "검색을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });


        } else {
            inflater.inflate(R.menu.menu_list2, menu);//xml로 메뉴를 만드는 부분

        }


        //출처: https: gakari.tistory.com/entry/안드로이다-실행-중에-메뉴를-교체하기 [가카리의 공부방]

        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_one:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "오픈", Toast.LENGTH_LONG).show();

                directoryFlag = true;

                // 임시
                Animation animationH = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translatehide); //애니메이션 디렉토리의 애니종류 xml 선택
                ibtn.setAnimation(animationH); //각 버튼에 애니메이션 세팅
                ibtn.setVisibility(mView.GONE);

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();

                //임시
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate); //애니메이션 디렉토리의 애니종류 xml 선택
                ibtn.setAnimation(animation); //각 버튼에 애니메이션 세팅
                ibtn.setVisibility(mView.VISIBLE);

                return super.onOptionsItemSelected(item);

        }
    }

/*
    public void downDirectory(boolean directoryFlag, View v) {
        if (directoryFlag == true) { //디렉토리 떠있으면 업앰
            Animation animationH = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translatehide); //애니메이션 디렉토리의 애니종류 xml 선택
            ibtn.setAnimation(animationH); //각 버튼에 애니메이션 세팅
            ibtn.setVisibility(mView.GONE);
        }
        else {
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate); //애니메이션 디렉토리의 애니종류 xml 선택
            ibtn.setAnimation(animation); //각 버튼에 애니메이션 세팅
            ibtn.setVisibility(v.VISIBLE);
        }
    }
*/

    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.frameLayout);
        spinners = findViewById(R.id.spinner);
        spinners.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(getApplicationContext(), "spinner 터치", Toast.LENGTH_LONG).show();
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

        //출처: https://mommoo.tistory.com/30 [개발자로 홀로 서기]
        //프래그먼트는 뷰와 다르게 context를 매개변수로 넣어줄 필요가 없다.
/*
        fragment1 = new MainFragment();
        fragment2 = new MenuFragment();
        onFragmentChange(0);
*/

        //툴바 생성
        toolbar = findViewById(R.id.toolbar);
//        setActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        //드로워레이아웃
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
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

        ibtn = findViewById(R.id.imageButton);


    }


    public void onButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.btnMain:
                toolbarFlag = true;
                invalidateOptionsMenu();//여기서 onCreateOptionsMenu 메소드를 다시 부르게됨 / https://gakari.tistory.com/entry/안드로이다-실행-중에-메뉴를-교체하기

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
                toolbarFlag = false;
                invalidateOptionsMenu();
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


            case R.id.floatingActionButton:
                Toast.makeText(this, "i wanna go home. . . ", Toast.LENGTH_SHORT).show();
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                Toast.makeText(getApplicationContext(), "오픈누름", Toast.LENGTH_LONG).show();
                return true;

            default:
                return false;
        }
    }


}
