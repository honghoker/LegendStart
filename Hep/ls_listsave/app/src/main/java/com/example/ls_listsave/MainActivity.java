package com.example.ls_listsave;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private List<String> list;
    EditText Location_Name;
    EditText Location_Address;
    EditText Location_DetailAddress;
    EditText Location_Number;
    EditText Location_Comment;


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
        Location_Name = findViewById(R.id.Text_Name);
        Location_Address = findViewById(R.id.Text_Addr);
        Location_DetailAddress = findViewById(R.id.Text_DetailAddr);
        Location_Number = findViewById(R.id.Text_Number);
        Location_Comment = findViewById(R.id.Text_Comment);

        for(String s : HashTag.getHashTagar()){
            // 어레이리스트 값 가져올때 사용
        }

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
        hashtag_Add(((AutoCompleteTextView)findViewById(R.id.clearable_edit)).getText().toString().trim());
    }

    public void hashtag_Add(String Hash){
        AutoCompleteTextView HashText = findViewById(R.id.clearable_edit);

        if(HashText.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            hashtext_set(Hash);
        }
        else if(HashTag.getHashTagar().size() == 5){
            Toast.makeText(getApplicationContext(), "태그는 5개까지 추가할 수 있습니다.", Toast.LENGTH_SHORT).show();
            hashtext_set(Hash);
        }
        else if(HashTag.getHashTagar().contains(Hash)){
            Toast.makeText(getApplicationContext(), "이미 추가한 태그입니다.", Toast.LENGTH_SHORT).show();
            hashtext_set(Hash);
        }
        else {
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(20, 20);
            HashTag hashtag = new HashTag(this);

            hashtag.init(Hash, "#3F729B", R.drawable.hashtagborder, params);

            ((FlowLayout)findViewById(R.id.flowlayout)).addView(hashtag);

            HashText.setText("");
            HashTag.getHashTagar().add(Hash);
        }
    }

    public void hashtext_set(String Hash){
        ((AutoCompleteTextView)findViewById(R.id.clearable_edit)).setText(Hash);
        ((AutoCompleteTextView)findViewById(R.id.clearable_edit)).setSelection(((AutoCompleteTextView)findViewById(R.id.clearable_edit)).length());
    }
}