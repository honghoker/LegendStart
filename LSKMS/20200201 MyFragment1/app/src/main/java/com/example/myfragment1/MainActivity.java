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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    //프래그먼트는  xml레이아웃 파일 하나랑 자바소스 파일 하나로 정의할 수 있다.
    //이게 하나의 뷰처럼 쓸 수 있는데 뷰하고 약간 다른특성들이 있다.
    //엑티비티를 본떠 만들었기 떄문에 프래그먼트 매니저가 소스코드에서 담당한다.
    MainFragment fragment1;
    MenuFragment fragment2;
    private boolean menuFlag = true;
    //프래그먼트 유지
    private FragmentManager fragmentManager;
    private Fragment fa, fb = null;


    //상단 메뉴 달기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //메뉴
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.eventmenu, menu);

        //상단 메뉴 java에서 변경 https://www.it-swarm.dev/ko/android/android%EC%97%90%EC%84%9C-%EB%A9%94%EB%89%B4-%ED%95%AD%EB%A
        // A%A9%EC%9D%98-%ED%85%8D%EC%8A%A4%ED%8A%B8-%EC%83%89%EC%83%81%EC%9D%84-%EB%B3%80%EA%B2%BD%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95%EC%9D%80-%EB%AC%B4%EC%97%87%EC%9E%85%EB%8B%88%EA%B9%8C/969218497/

        /*
        int positionOfMenuItem = 2; // 1번 인덱스 메뉴아이템

        MenuItem item = menu.getItem(positionOfMenuItem);

        SpannableString s = new SpannableString("My red MenuItem");
        s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
        item.setTitle(s);

        menu.findItem(R.id.minus).setIcon(R.drawable.ic_map);
        */

        return true;
    }

    public void changeMenu(Menu menu) {
        if (menuFlag == true) {
            menu.findItem(R.id.plus).setIcon(R.drawable.ic_map);
        } else if (menuFlag == false) {
            menu.findItem(R.id.plus).setIcon(R.drawable.ic_map);
        }
    }

    //상단 메뉴 이벤트
    //액션버튼을 클릭했을때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //or switch문을 이용하면 될듯 하다.
        if (id == android.R.id.home) {
            Toast.makeText(this, "홈 클릭", Toast.LENGTH_SHORT).show();

            //Intent homeIntent = new Intent(this, HomeActivity.class);
            //startActivity(homeIntent);
        }
        if (id == R.id.plus) {
            Toast.makeText(this, "플러스 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.minus) {
            Toast.makeText(this, "마이너스 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.ten) {
            Toast.makeText(this, "10 클릭", Toast.LENGTH_SHORT).show();

            /*
            Intent settingIntent = new Intent(this, SettingActivity.class);
            startActivity(settingIntent);
            */

        }

        return super.onOptionsItemSelected(item);
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

        //프래그먼트 유지

        fragmentManager = getSupportFragmentManager();
        fa = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fa).commit();


        //상단 메뉴

        //액션바 타이틀 변경하기
        getSupportActionBar().setTitle("App");
        //액션바 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        //홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 home


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
}
