package com.example.ls_listsave;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements
        HashTagHelper.OnHashTagClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private HashTagHelper mTextHashTagHelper;
    private HashTagHelper mEditTextHashTagHelper;

    private TextView mEditTextView;

    private Toast mToast;

    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        listsetting();
    }

    public void init() {
        list = new ArrayList<String>();

        mEditTextView = (EditText) findViewById(R.id.edit_text_field);
        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.edit_text_field);

        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list)); // 글자 자동완성

        char[] additionalSymbols = new char[]{'_', '$'};
        // If you set additional symbols not only letters and digits will be a
        // valid symbols for hashtag
        // Example:
        // "hash_tag_with_underscore_and$dolar$sign$is$also$valid_hashtag"

        mTextHashTagHelper = HashTagHelper.Creator.create(getResources()
                .getColor(R.color.colorPrimary), this, additionalSymbols);

        //mTextHashTagHelper.handle(mHashTagText);

        // Here we don't specify additionalSymbols. It means that in EditText
        // only letters and digits will be valid symbols
        mEditTextHashTagHelper = HashTagHelper.Creator.create(getResources()
                .getColor(R.color.colorPrimaryDark), null);
        mEditTextHashTagHelper.handle(mEditTextView);
    }

    public void listsetting() {
        list.add("소고기");
        list.add("돼지고기");
        list.add("오리고기");
        list.add("닭고기");
        list.add("양고기");
        list.add("개고기");
    }

    @Override
    public void onHashTagClicked(String hashTag) {
        Log.v(TAG, "onHashTagClicked [" + hashTag + "]");
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(MainActivity.this, hashTag,
                Toast.LENGTH_SHORT);
        mToast.show();
    }

    ArrayList<String> Hashtagar = new ArrayList<String>();

    public void onButtonHashTagAddClicked(View v) {
        if (mEditTextView.getText().length() != 0) {
            if (Hashtagar.contains(mEditTextView.getText().toString())) {
                Toast.makeText(getApplicationContext(), "이미 추가한 태그입니다.", Toast.LENGTH_SHORT).show();
            } else {
                TextView text = new TextView(this);
                text.setText(mEditTextView.getText());
                text.setTextColor(Color.parseColor("#000000"));
                text.setBackgroundResource(R.drawable.hashtag);
                        /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(5,5,10,5);  // 왼쪽, 위, 오른쪽, 아래 순서
                        text.setLayoutParams(params);*/

                FlowLayout.LayoutParams paramss = new FlowLayout.LayoutParams(20, 20);
                FlowLayout layout = findViewById(R.id.flowlayout);
                text.setLayoutParams(paramss);
                layout.addView(text);

                mEditTextView.setText("");
                //((LinearLayout) findViewById(R.id.HashTagLayout)).addView(text);
                Hashtagar.add(text.getText().toString());
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "추가할 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

}