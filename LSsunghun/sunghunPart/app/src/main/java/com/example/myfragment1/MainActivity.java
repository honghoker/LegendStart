package com.example.myfragment1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // 기본적으로 쓰이는 것들 선언
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Spinner spinner;
    RecyclerView recyclerView;
    Adapter adapter;
    ConstraintLayout recy_con_layout;

    //recyclerVIew test용 Title ArrayList선언
    ArrayList<String> recy_title;
    //recyclerVIew test용 Tag ArrayList선언
    ArrayList<String[]> recy_Tag;
    //recyclerView 내려왔는지 실행확인 Frag변수
    boolean recyFrag = false;

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




    // Activity가 시작될 때 호출되는 함수 -> MenuItem 생성과 초기화 진행


    // Activity가 처음 실행되는 상태에 제일 먼저 호출되는 메소드 -> 실행시 필요한 각종 초기화 작업
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recy_con_layout = findViewById(R.id.recy_con_layout);

        //프래그먼트는 뷰와 다르게 context를 매개변수로 넣어줄 필요가 없다.
/*
        fragment1 = new MainFragment();
        fragment2 = new MenuFragment();
        onFragmentChange(0);
*/
        // tollbar 선언
        toolbar = findViewById(R.id.toolbar);
        // spinner 선언
        spinner = findViewById(R.id.spinner);
        // toolbar 초기 Title 선언
        toolbar.setTitle("여기는 됌?");
        // **NoActionBar 해주고 이 메서드 호출하면 toolbar를 Activity의 앱바로 사용가능
        setSupportActionBar(toolbar);

        // Drawer Navigation
        // drawerLayout -> Drawer 기능을 이용하기 위해서 밑에 까는 레이아웃
        drawerLayout = findViewById(R.id.drawer_layout);
        // navigationVIew -> 왼쪽에서 드래그 했을 때 보이는 VIew
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ActionBarDrawerToggle 을 통해 Toolbar 와 DrawerLayout 을 연결
        // Toolbar 에 DrawerArrowDrawerbleToggle 이 NavigationIcon 로 등록되고 (hamburger icon)
        // NavigationOnClickListner 에 DrawerLayout open, close 관련 기능이 연결됨.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        // 현재 Drawerlayout 상태와 ActionBarDrawerToggle 의 상태를 sync
        toggle.syncState();

        // 어플키면 먼저 Map을 보여주기위함
        // MainActivity에 MapFragment(MainFragment)를 올림
        fragmentManager = getSupportFragmentManager();
        fa = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fa).commit();

        //recyclerView test Tag 배열선언
//        final String[] tag1 = {"소고기", "안녕하세요", "김성훈입니다", "방가방가", "소고소고소고소고"};
//        String[] tag2 = {"1"};
//        String[] tag3 = {"1", "2", "3", "4"};
//        String[] tag4 = {"1", "2", "3"};
//        String[] tag5 = {"1", "2"};
        //recyclerView test Title ArrayList
        recy_title = new ArrayList<>();
        recy_title.add("Frist");
        recy_title.add("Two");
        recy_title.add("Third");
        recy_title.add("Four");
        recy_title.add("FIve");
        //recyclerView test Tag ArrayList
//        recy_Tag = new ArrayList<String[]>();
//        recy_Tag.add(tag1);
//        recy_Tag.add(tag2);
//        recy_Tag.add(tag3);
//        recy_Tag.add(tag4);
//        recy_Tag.add(tag5);


        // recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        // recyclerView set ( HORIZONTAL -> 수평 / if) 수직이면 false -> true)
        // 어떤형태로 배치될 아이템 뷰를 만들것인지 결정하는요소 -> LayoutManager -> Linear -> 수직 또는 수평으로 일렬형태로 배치
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // recyclerView에 표시될 아이템 뷰를 생성하는 역할 adapter
        adapter = new Adapter(getApplicationContext(), recy_title);
        recyclerView.setAdapter(adapter);

        //recyclerview pos (위치) 값 가져와서 items 리스트 안에 있는 pos (위치)에 있는 Title 가져옴
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                toolbar.setTitle(recy_title.get(pos));
                Toast.makeText(getApplicationContext(), recy_title.get(pos), Toast.LENGTH_SHORT).show();
            }
        });

        // frameLayout 위에 recyclerView가 나타나야함으로 frameLayout 선언
        mView = findViewById(R.id.frameLayout);
        // recyclerView 초기상태 gone
        recy_con_layout.setVisibility(mView.GONE);
//        recyclerView.setVisibility(mView.GONE);
        final TextView recy_allSee = (TextView) findViewById(R.id.recy_allSee);
//        recy_allSee.setVisibility(mView.GONE);
        final TextView back_allSee = (TextView) findViewById(R.id.back_allSee);
//        back_allSee .setVisibility(mView.GONE);


        // spinner 터치(클릭) 시 이벤트처리
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && recyFrag == false) {
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
                    recy_con_layout.setAnimation(animation);
                    /*recyclerView.setAnimation(animation);
                    recy_allSee.setAnimation(animation);
                    back_allSee.setAnimation(animation);
                    recyclerView.setVisibility(mView.VISIBLE);
                    recy_allSee.setVisibility(mView.VISIBLE);
                    back_allSee.setVisibility(mView.VISIBLE);*/
                    recy_con_layout.setVisibility(mView.VISIBLE);
                    Toast.makeText(getApplicationContext(), "spinner 터치", Toast.LENGTH_LONG).show();
                    recyFrag = true;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && recyFrag == true) {
                    Animation animationH = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translatehide);
                    recy_con_layout.setAnimation(animationH);
                    /*recyclerView.setAnimation(animationH);
                    recy_allSee.setAnimation(animationH);
                    back_allSee.setAnimation(animationH);
                    recyclerView.setVisibility(mView.GONE);
                    recy_allSee.setVisibility(mView.GONE);
                    back_allSee.setVisibility(mView.GONE);
                    */
                    recy_con_layout.setVisibility(mView.GONE);
                    Toast.makeText(getApplicationContext(), "spinner 터치", Toast.LENGTH_LONG).show();
                    recyFrag = false;
                }
                recy_allSee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                Log.e("1","ㅅㅄㅂ");
                        Intent intent = new Intent(mView.getContext(), AllSeeActivity.class);
//                startActivityForResult(intent,1901);
                        startActivity(intent);
                        CustomIntent.customType(mView.getContext(), "left-to-right");
                        Toast.makeText(getApplicationContext(),"전체보기클릭",Toast.LENGTH_SHORT).show();
                    }
                });
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

    // fragment 화면전환 + 유지
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

    // fragment 상에서 다른 fragment로 이동
    //프래그먼트와 프래그먼트끼리 직접접근을하지않는다. 프래그먼트와 엑티비티가 접근함
    public void onFragmentChange(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment2).commit();
        }
    }

    // 뒤로가기 버튼 처리
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    // navigation 메뉴 선택 이벤트처리
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            Intent intent = new Intent(this, homeActivity.class);
            startActivity(intent);
            CustomIntent.customType(this, "left-to-right");
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
