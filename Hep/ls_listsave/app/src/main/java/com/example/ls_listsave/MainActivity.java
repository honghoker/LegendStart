package com.example.ls_listsave;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends Activity {
    ArrayList<String> Hashtagar = new ArrayList<String>();
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listinit();
        init();
    }

    public void listinit() {
        list = new ArrayList<String>();
        list.add("소고기");
        list.add("돼지고기");
        list.add("오리고기");
        list.add("닭고기");
        list.add("양고기");
        list.add("개고기");
    }

    public void init() {
        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.clearable_edit);

        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list)); // 글자 자동완성

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hashtag_Add(((TextView)view).getText().toString());
            }
        });
    }

    public void onButtonHashTagAddClicked(View v) {
        hashtag_Add(((AutoCompleteTextView)findViewById(R.id.clearable_edit)).getText().toString());
    }


    public void hashtag_Add(String Hash){
        AutoCompleteTextView HashText = findViewById(R.id.clearable_edit);

        if(HashText.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(Hashtagar.contains(Hash)){
            Toast.makeText(getApplicationContext(), "이미 추가한 태그입니다.", Toast.LENGTH_SHORT).show();
            HashText.setText(HashText.getText().toString().trim());
        }
        else if(Hashtagar.size() == 5){
            Toast.makeText(getApplicationContext(), "태그는 5개까지 추가할 수 있습니다.", Toast.LENGTH_SHORT).show();
        }
        else {
            TextView text = new TextView(this);
            text.setText("#"+Hash);

            Drawable x = getResources().getDrawable(R.drawable.x);
            x.setBounds(0, 0, 40, 40);
            text.setCompoundDrawables(null, null, x, null);

            text.setTextColor(Color.parseColor("#3F729B")); // 인스타 태그 컬러
            text.setBackgroundResource(R.drawable.hashtag); // 라운드 테두리
            text.setId(Hashtagar.size());

            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(20, 20);


            text.setLayoutParams(params);
            ((FlowLayout)findViewById(R.id.flowlayout)).addView(text);

            HashText.setText("");
            Hashtagar.add(Hash);
        }
    }

    public void onButtondeleteclicked(View v){
        if(!Hashtagar.isEmpty()) {
            ((FlowLayout) findViewById(R.id.flowlayout)).removeView(findViewById(Hashtagar.size() - 1));
            Hashtagar.remove(Hashtagar.size() - 1);
        }
    }
}