package com.example.mynavermap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    MainFragment fragment1;
    private boolean menuFlag = true;

    //프래그먼트 유지

    private FragmentManager fragmentManager; //프래그먼트 매니저
    private Fragment fa = null; //프래그먼트
    MainFragment mf = new MainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //기존의 프래그먼트 만들어서 불러오기
        fragmentManager = getSupportFragmentManager(); //위에 선언된 프래그매니저에 겟서포트
        fa = new MainFragment(); //프래그먼트를 메인프레그먼트로
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fa).commit(); // 프래그매니저에게 명령 프레임 레이아웃에 fa 를 replace


        //Button b = findViewById(R.id.btncameraLocation);

        //Toast.makeText(this,"버튼높이 : " , Toast.LENGTH_SHORT).show();
    } //oncreate 종료


    public void onClickFragmentBtn(View v) {
        switch (v.getId()) {
            case R.id.btncameraLocation:
                mf.setLocation(this);
                break;
            case R.id.btngetLocationBtn :
                mf.getLocationPosition(this,MainFragment.NMap);
                break;
            default:
                break;
        }
    }
}
