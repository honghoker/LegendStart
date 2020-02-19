package com.example.myflowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn[] = new Button[100];
    TextView tv[] = new TextView[30];
    CheckBox checkBoxAll; //체크박스 명 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(20, 20);
        HasTagOnClickListener ob = new HasTagOnClickListener();

//        for (int i = 1; i < tv.length; i++) {
//            tv[i] = new Button(this);
//            final Integer aa = i;
//            tv[i].setText("temp");
//            tv[i].setId(i);
//            tv[i].setLayoutParams(params);
//            tv[i].setTextColor(Color.YELLOW);
//            tv[i].setBackgroundResource(R.drawable.hashtagunclick); //초기 디자인
//            tv[i].setOnClickListener(ob);
//
//            ((FlowLayout) findViewById(R.id.flowlayout)).addView(tv[i]);
//        } //해시태그 아이디 입력 및 추가 종료
//
        String Hash = "33";

        for(int i = 1; i < 25; i++) {
            HashTag hashtag = new HashTag(this);
            if(i % 2 == 0)
            hashtag.init(Hash, "#3F729B", R.drawable.hashtagborder, params);
            else
                hashtag.init("asdfan32of2ofndladf", "#3F729B", R.drawable.hashtagborder, params);
            hashtag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "클릭가능", Toast.LENGTH_SHORT).show();
                }
            });
            ((FlowLayout) findViewById(R.id.flowlayout)).addView(hashtag);
        }//for 종료


//        for (int i = 1; i < btn.length; i++) {
//            btn[i] = new Button(this);
//            final Integer aa = i;
//            btn[i].setText("temp");
//            btn[i].setId(i);
//            btn[i].setLayoutParams(params);
//            btn[i].setTextColor(Color.YELLOW);
//            btn[i].setBackgroundResource(R.drawable.hashtagunclick); //초기 디자인
//            btn[i].setOnClickListener(ob);
//
//            ((FlowLayout) findViewById(R.id.flowlayout)).addView(btn[i]);
//        } //해시태그 아이디 입력 및 추가 종료

        //체크박스
        checkBoxAll = findViewById(R.id.checkBox);
    } //oncreate 종료

    class HasTagOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            for (int j = 1; j < btn.length; j++) {
                if (v.getId() == j) {
                    btn[j].setBackgroundResource(R.drawable.hashtagclick); //만약 초기값이면 클릭상태로 바꿔준다.
                    //Toast.makeText(MainActivity.this, aa.toString() + "id확인 : " +v.getId(), Toast.LENGTH_SHORT).show();
                    btn[j].setId(-j);
                    //count = false;
                    break;
                } else if (v.getId() == -j) {
                    // count = true;
                    btn[j].setBackgroundResource(R.drawable.hashtagunclick); //만약 초기값이면 클릭상태로 바꿔준다.
                    btn[j].setId(j);
                    break;
                }
            }//for 문 종료
        }
    }

    public void CheckBoxOnClick(View v) {
        switch (v.getId()) {
            case R.id.checkBox:
                if (checkBoxAll.isChecked()) { //체크 상태면 실행
                    //전부 선택한다.
                    CheckBoxAllClick();
                } //if 종료
                else {
                    CheckBoxAllUnClick();
                    //전부 해제한다.
                }
        }
    }

    public void CheckBoxAllClick() {
        for (int j = 1; j < btn.length; j++) {
            btn[j].setBackgroundResource(R.drawable.hashtagclick); // 클릭상태로 바꿔준다.
            btn[j].setId(-j);
        } //for 종료
    }

    public void CheckBoxAllUnClick() {
        for (int j = 1; j < btn.length; j++) {
            btn[j].setBackgroundResource(R.drawable.hashtagunclick); // 클릭상태로 바꿔준다.
            btn[j].setId(j);
        } //for 종료
    }
}
