package com.example.Myfrag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.Myfrag.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

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
    LinearLayout recy_add_btn;

    View asdf;

    // 이거이용
    ConstraintLayout recy_card_layout;

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

    static List<View> test_view = null;
    static View [] test_view_2 = null;
    String test_view_1;
    static boolean addFrag = false;
    static RecyclerView recy_test_view;

    // Activity가 시작될 때 호출되는 함수 -> MenuItem 생성과 초기화 진행
    // Activity가 처음 실행되는 상태에 제일 먼저 호출되는 메소드 -> 실행시 필요한 각종 초기화 작업
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test_view_2 = new View[5];

        asdf = findViewById(R.id.asdf);

        recy_card_layout = findViewById(R.id.recy_card_layout);
        recy_con_layout = findViewById(R.id.recy_con_layout);
<<<<<<< HEAD:LSsunghun/sunghunPart/app/src/main/java/com/example/myfragment1/MainActivity.java
=======
        recy_add_btn = findViewById(R.id.recy_add_btn);
>>>>>>> 367a33e516d7bf8ce0808a272087776e99544dd8:LSsunghun/sunghunPart/app/src/main/java/com/example/Myfrag/MainActivity.java

        // tollbar 선언
        toolbar = findViewById(R.id.toolbar);

        // spinner 선언
        spinner = findViewById(R.id.spinner);

        // toolbar 초기 Title 선언
        toolbar.setTitle("여기는 됨?");

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

        recy_title = new ArrayList<>();
//        recy_title.add("Frist");
//        recy_title.add("Two");
//        recy_title.add("Third");
//        recy_title.add("Four");
//        recy_title.add("FIve");

        // 여기 조져야함
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "directory_db").build();

        db.directoryDao().getAll().observe(this, new Observer<List<Directory>>() {
            @Override
            public void onChanged(List<Directory> directories) {
<<<<<<< HEAD:LSsunghun/sunghunPart/app/src/main/java/com/example/myfragment1/MainActivity.java
                for(Directory D : directories){
                    recy_title.add(D.toString());
=======
                for (int i = 0; i < directories.size(); i++) {
                    recy_title.add(directories.get(i).toString());
                    Log.d("1","확인");
                    // 아예 추가하고 여길 다시 안돔..
//                    if(addFrag==true){
//                        Log.d("1","여긴옴?");
//                        adapter = new Adapter(getApplicationContext(),recy_title);
//                        recyclerView.setAdapter(adapter);
//                        addFrag=false;
//                    }
>>>>>>> 367a33e516d7bf8ce0808a272087776e99544dd8:LSsunghun/sunghunPart/app/src/main/java/com/example/Myfrag/MainActivity.java
                }
            }
        });

//        List<Directory> a = db.directoryDao().getAll().getValue();
//        Log.d("1","db확인11"+ db.directoryDao().getAll().getValue());

        // recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recy_test_view = findViewById(R.id.recyclerView);
        // recyclerView set ( HORIZONTAL -> 수평 / if) 수직이면 false -> true)
        // 어떤형태로 배치될 아이템 뷰를 만들것인지 결정하는요소 -> LayoutManager -> Linear -> 수직 또는 수평으로 일렬형태로 배치
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // recyclerView에 표시될 아이템 뷰를 생성하는 역할 adapter

//        adapter = new Adapter(this);
        adapter = new Adapter(this, recy_title);
//        test_view.add(adapter.view);


        recyclerView.setAdapter(adapter);
        mView = findViewById(R.id.frameLayout);
        Log.d("Search", "adapter 확인");

        //recyclerview pos (위치) 값 가져와서 items 리스트 안에 있는 pos (위치)에 있는 Title 가져옴
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                toolbar.setTitle(recy_title.get(pos));
                Toast.makeText(getApplicationContext(), recy_title.get(pos), Toast.LENGTH_SHORT).show();
                Animation animationH = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translatehide);
                asdf.setAnimation(animationH);
                asdf.setVisibility(mView.GONE);
            }
        });

        // frameLayout 위에 recyclerView가 나타나야함으로 frameLayout 선언

        // recyclerView 초기상태 gone
        asdf.setVisibility(mView.GONE);
//        recy_con_layout.setVisibility(mView.GONE);
//        recy_add_btn.setVisibility(mView.GONE);
//        recy_card_layout.setVisibility(mView.GONE);

        final TextView recy_allSee = (TextView) findViewById(R.id.recy_allSee);
        final TextView back_allSee = (TextView) findViewById(R.id.back_allSee);

//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        // spinner 터치(클릭) 시 이벤트처리
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && recyFrag == false) {
//                    Log.d("Search", "runrun@@@@1");
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
<<<<<<< HEAD:LSsunghun/sunghunPart/app/src/main/java/com/example/myfragment1/MainActivity.java
                    recy_con_layout.setAnimation(animation);
                    recy_con_layout.setVisibility(mView.VISIBLE);
                    //recyclerView.setAnimation(animation);
                    recyclerView.setVisibility(mView.VISIBLE);

                    Animation animationt = new AlphaAnimation(0, 1);
                    animation.setDuration(1000);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAnimation(animationt);
                    recy_allSee.setVisibility(View.VISIBLE);
=======
//                    Log.d("Search", "runrun@@@@2");
                    asdf.setAnimation(animation);
                    asdf.setVisibility(mView.VISIBLE);
//                    recyclerView.setAnimation(animation);
//                    recyclerView.setVisibility(mView.VISIBLE);
                    Log.d("Search", "1");
//                    for(int i =0; i<5; i++){
//                        test_view_2[i].setAnimation(animation);
//                        test_view_2[i].setVisibility(mView.VISIBLE);
//                    }
//                    test_view.setAnimation(animation);
//                    test_view.setVisibility(mView.VISIBLE);

//                    recy_con_layout.setAnimation(animation);
//                    recy_con_layout.setVisibility(mView.VISIBLE);
//                    adapter.test();
                    //sival
//                    recy_add_btn.setAnimation(animation);
//                    recy_add_btn.setVisibility(mView.VISIBLE);
//                    recy_card_layout.setAnimation(animation);
//                    recy_card_layout.setVisibility(View.VISIBLE);
//                    Toast.makeText(getApplicationContext(), "spinner 터치", Toast.LENGTH_LONG).show();

>>>>>>> 367a33e516d7bf8ce0808a272087776e99544dd8:LSsunghun/sunghunPart/app/src/main/java/com/example/Myfrag/MainActivity.java
                    recyFrag = true;

                } else if (event.getAction() == MotionEvent.ACTION_DOWN && recyFrag == true) {
                    Animation animationH = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translatehide);
<<<<<<< HEAD:LSsunghun/sunghunPart/app/src/main/java/com/example/myfragment1/MainActivity.java
                    recy_con_layout.setAnimation(animationH);
                    recy_con_layout.setVisibility(View.GONE);
                    //recyclerView.setAnimation(animationH);

                    Animation animationt = new AlphaAnimation(0, 1);
                    animationt.setDuration(1000);
                    recyclerView.setAnimation(animationt);
                    recyclerView.setVisibility(View.GONE);
                    recy_allSee.setVisibility(View.GONE);

=======
                    asdf.setAnimation(animationH);
                    asdf.setVisibility(mView.GONE);
//                    recyclerView.setAnimation(animationH);
//                    recyclerView.setVisibility(mView.GONE);
                    Log.d("Search", "2");
//                    Log.d("Search","길이확인  " + test_view_2.length);

//                    test_view_2[0].setVisibility(mView.GONE);
//                    test_view_2[1].setVisibility(mView.GONE);
//                    test_view_2[2].setVisibility(mView.GONE);
//                    test_view_2[3].setVisibility(mView.GONE);
//                    test_view_2[4].setVisibility(mView.GONE);
//                    for(int i =0; i<test_view_2.length; i++){
//                        Log.d("Search","확인");
////                        test_view_2[i].setAnimation(animationH);
//                        test_view_2[i].setVisibility(View.GONE);
//                    }
//                    test_view.setAnimation(animationH);
//                    test_view.setVisibility(mView.GONE);
//                    adapter.test();
//                    for(int i = 0 ; i<test_view.size();i++){
//                        test_view.get(i).setVisibility(mView.GONE);
////                        test_view.get(i).view.setVisibility(mView.GONE);
//                    }


//                    recy_con_layout.setAnimation(animationH);
//                    recy_con_layout.setVisibility(mView.GONE);
//                    Log.d("1",test_view.get(0).toString());

                    //sival
//                    recy_add_btn.setAnimation(animationH);
//                    recy_add_btn.setVisibility(mView.GONE);
//                    recy_card_layout.setAnimation(animationH);
//                    recy_card_layout.setVisibility(View.GONE);
//                    Toast.makeText(getApplicationContext(), "spinner 터치", Toast.LENGTH_LONG).show();
>>>>>>> 367a33e516d7bf8ce0808a272087776e99544dd8:LSsunghun/sunghunPart/app/src/main/java/com/example/Myfrag/MainActivity.java
                    recyFrag = false;
                }

                recy_allSee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mView.getContext(), AllSeeActivity.class);
                        startActivity(intent);
                        CustomIntent.customType(mView.getContext(), "left-to-right");
                        Toast.makeText(getApplicationContext(), "전체보기클릭", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.d("Search", "3");
                return true;
            }
        });
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
        return true;
    }
}
