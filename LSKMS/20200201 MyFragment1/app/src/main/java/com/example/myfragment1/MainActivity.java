package com.example.myfragment1;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
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
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {



    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //프래그먼트는  xml레이아웃 파일 하나랑 자바소스 파일 하나로 정의할 수 있다.
    //이게 하나의 뷰처럼 쓸 수 있는데 뷰하고 약간 다른특성들이 있다.
    //엑티비티를 본떠 만들었기 떄문에 프래그먼트 매니저가 소스코드에서 담당한다.
    MainFragment fragment1; //맵 프레그먼트
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

    View mView; //디렉토리를 위해 뷰를 추가해줌

    //서치 누르면 상단 탭 교체
    private boolean searchFlag = false;

    private EditText searchBar;


    ClearEditText clearedittext;

    @Override //메뉴 생성 메소드
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_list,menu);

        //프래그먼트에 따른 툴바 변경
        if (toolbarFlag) {
            inflater.inflate(R.menu.menu_list, menu);//xml로 메뉴를 만드는 부분
        } else {
            inflater.inflate(R.menu.menu_list2, menu);//xml로 메뉴를 만드는 부분
        }

        if (searchFlag) { //디렉토리 오픈 시, 스피너 클릭 이벤트 확인 후 하단에 추가해야함
            inflater.inflate(R.menu.menu_directory, menu);//xml로 메뉴를 만드는 부분

        }

        //출처: https: gakari.tistory.com/entry/안드로이다-실행-중에-메뉴를-교체하기 [가카리의 공부방]
        return true;
    }

    //상단 툴바 클릭 이벤트
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_search: //상단 검색 버튼 클릭 시
                Toast.makeText(getApplicationContext(), "검색할 장소를 입력하세요.", Toast.LENGTH_LONG).show();
//                Animation animationH = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translatehide); //애니메이션 디렉토리의 애니종류 xml 선택
//                ibtn.setAnimation(animationH); //각 버튼에 애니메이션 세팅
//                ibtn.setVisibility(mView.GONE);
               // Toast.makeText(this, "i wanna go home. .2 . ", Toast.LENGTH_SHORT).show();
                //툴바 제거
                if (getSupportActionBar().isShowing()) {
                    searchFlag = true;
                    getSupportActionBar().hide();
                    imm.showSoftInput(searchBar, 0);
                }
                return true;
            default:
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

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
//        //액션바 타이틀 변경하기
//        getSupportActionBar().setTitle("App");
//        //액션바 배경색 변경
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
//        //홈버튼 표시
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 home

        ibtn = findViewById(R.id.imageButton);

        //백 프레스 클래스 가져옴
        backPressedForFinish = new BackPressedForFinish(this);

        //포커스 주기
        searchBar = findViewById(R.id.editTextSearch);
        searchBar.requestFocus();
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){

                    return true;
                }
                return false;
            }
        });


        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }
    InputMethodManager imm;



    public void onButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.btnMapfrag:
                toolbarFlag = true;
                invalidateOptionsMenu();//여기서 onCreateOptionsMenu 메소드를 다시 부르게됨 / https://gakari.tistory.com/entry/안드로이다-실행-중에-메뉴를-교체하기

                //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();/*프래그먼트 매니저가 프래그먼트를 담당한다!*/
                if (fa == null) {
                    fa = new MenuFragment();
                    fragmentManager.beginTransaction().add(R.id.frameLayout, fa).commit();
                }

                if (fa != null) fragmentManager.beginTransaction().show(fa).commit();
                if (fb != null) fragmentManager.beginTransaction().hide(fb).commit();

                break;

            case R.id.btnListfrag:
                toolbarFlag = false;
                invalidateOptionsMenu();
                if (fb == null) {
                    fb = new MenuFragment();
                    fragmentManager.beginTransaction().add(R.id.frameLayout, fb).commit();
                }
                if (fa != null) fragmentManager.beginTransaction().hide(fa).commit();
                if (fb != null) fragmentManager.beginTransaction().show(fb).commit();
                break;

            case R.id.floatingActionButton:
                Toast.makeText(this, "i wanna go home. . . ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editTextClear :
                Toast.makeText(this, "삭제됨", Toast.LENGTH_LONG).show();
                searchBar.setText("");
            default:
                break;
        }
    }

/*

    //프래그먼트와 프래그먼트끼리 직접접근을하지않는다. 프래그먼트와 엑티비티가 접근함
    public void onFragmentChange(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment2).commit();
        }
    }
*/ //프레그먼트 replace 방법으로 교체하는 코드

    private BackPressedForFinish backPressedForFinish; //백프레스 클래스

    @Override
    public void onBackPressed() {
        // BackPressedForFinish 클래시의 onBackPressed() 함수를 호출한다.
        if (searchFlag == false)
            backPressedForFinish.onBackPressed();

        //서치상태 아닐때만 종료 가능
        if (drawerLayout.isDrawerOpen(GravityCompat.START) && searchFlag == false) {
            drawerLayout.closeDrawers();
        } else if (searchFlag == true) {
            getSupportActionBar().show();
            searchFlag = false;
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
