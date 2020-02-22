package com.example.myflowlayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HashTagCheckBoxManager extends AppCompatActivity {
    public HashTag hs[] = MainActivity.hashTag;
    public static Context mContext;

    public HashTagCheckBoxManager(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public void HashTagClickEvent(View v, HashTag[] hashTag){

        for (int j = 1; j < hashTag.length; j++) {
            if (v.getId() == j) {
                hashTag[j].init(hashTag[j].getHashText(), "#3F729B", R.drawable.hashtagclick, MainActivity.params);
                //hastTag[j].setBackgroundResource(R.drawable.hashtagclick); //만약 초기값이면 클릭상태로 바꿔준다.
                //Toast.makeText(MainActivity.this, aa.toString() + "id확인 : " +v.getId(), Toast.LENGTH_SHORT).show();
                hashTag[j].setId(-j);
                //count = false;
                break;
            } else if (v.getId() == -j) {
                // count = true;
                hashTag[j].init(hashTag[j].getHashText(), "#3F729B", R.drawable.hashtagunclick, MainActivity.params);
                hashTag[j].setId(j);
                break;
            }
        }//for 문 종료
    }

    public void CheckBoxAllClick(HashTag[] hashTags) {
        HashTag hs[] = MainActivity.hashTag;
        for (int j = 1; j < hs.length; j++) {
            hs[j].init(hs[j].getHashText(), "#3F729B", R.drawable.hashtagclick, MainActivity.params);
            hs[j].setId(-j);
        } //for 종료
    }

    public void CheckBoxAllClick2() {
        int a = MainActivity.hashTag.length;
        Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
    }
    public void CheckBoxAllUnClick(HashTag[] hashTags) {
        HashTag hs[] = MainActivity.hashTag;
        for (int j = 1; j < hs.length; j++) {
            hs[j].init(hs[j].getHashText(), "#3F729B", R.drawable.hashtagunclick, MainActivity.params);
            hs[j].setId(j);
        } //for 종료
    }

}
