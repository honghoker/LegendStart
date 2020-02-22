package com.example.myflowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBoxAll; //체크박스 명 선언
    public static HashTag[] hashTag = new HashTag[100]; //태그 배열
    public static FlowLayout.LayoutParams params; //해시태그 레이아웃을 위한 parms
    HashTagCheckBoxManager hashTagCheckBoxManager;
    Ex EEx = new Ex();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        params = new FlowLayout.LayoutParams(20, 20);
        HasTagOnClickListener ob = new HasTagOnClickListener();
        //((Ex)Ex.mContext2).Toastt();
        EEx.Toastt(this.getApplicationContext());
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

        for(int i = 1; i < hashTag.length; i++) {
            hashTag[i]= new HashTag(this);
            hashTag[i].setOnClickListener(ob);
            hashTag[i].setId(i);
            if(i % 2 == 0)
                hashTag[i].init(Hash, "#22FFFF", R.drawable.hashtagborder, params);
            else
                hashTag[i].init("asdfan32of2ofndladf", "#3F729B", R.drawable.hashtagborder, params);

            ((FlowLayout) findViewById(R.id.flowlayout)).addView(hashTag[i]);
        }//for 종료

        //체크박스
        checkBoxAll = findViewById(R.id.checkBox);

        checkBoxAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBoxAll.isChecked()) {
                    // Toast.makeText(getApplicationContext(),"dfd",Toast.LENGTH_SHORT).show();
                    hashTagCheckBoxManager.CheckBoxAllClick2();
                    //((HashTagCheckBoxManager)HashTagCheckBoxManager.mContext).CheckBoxAllClick2();

                }
                //else hashTagCheckBoxManager.CheckBoxAllUnClick(hashTag);
            }
        });
    } //oncreate 종료

    //해시태그 선택
    class HasTagOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            for (int j = 1; j < hashTag.length; j++) {
                if (v.getId() == j) {
                    hashTag[j].init(hashTag[j].getHashText(), "#3F729B", R.drawable.hashtagclick, params);
                    //hastTag[j].setBackgroundResource(R.drawable.hashtagclick); //만약 초기값이면 클릭상태로 바꿔준다.
                    //Toast.makeText(MainActivity.this, aa.toString() + "id확인 : " +v.getId(), Toast.LENGTH_SHORT).show();

                    hashTag[j].setId(-j);
                    //count = false;
                    break;
                } else if (v.getId() == -j) {
                    // count = true;
                    hashTag[j].init(hashTag[j].getHashText(), "#3F729B", R.drawable.hashtagunclick, params);
                    hashTag[j].setId(j);
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
                    hashTagCheckBoxManager.CheckBoxAllClick(hashTag);
                } //if 종료
                else {
                    hashTagCheckBoxManager.CheckBoxAllUnClick(hashTag);
                    //전부 해제한다.
                }
        }
    }
}
