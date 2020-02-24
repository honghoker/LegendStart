package com.example.myincludexml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

//https://whyprogrammer.tistory.com/447 툴바 설명

public class MainActivity extends AppCompatActivity {
    public static RelativeLayout relativelayout_sub;  // SelectLocation 단의 리니어 레이아웃
    SelectLocation sl = new SelectLocation();
    public static Toolbar toolbarMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarMain.setTitle("메인 세팅");
        setSupportActionBar(toolbarMain); //toolbarMain 에 액션바 권환

        relativelayout_sub = findViewById(R.id.relativeLayout);

    }


    public void onButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.button1:
                SetToolbar(); //툴바 세팅
                sl.SetLinearLayout(getApplicationContext(), relativelayout_sub);

                break;

            default:

                break;
        }
    }


    public void SetToolbar() { //액션바 상태에 따라서 세팅해준다.
        if (getSupportActionBar().isShowing()) { //만약 액션바 보이고 있으면 숨기기
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show(); //만약 액션바 안보이면 보이게
        }
    }
}
