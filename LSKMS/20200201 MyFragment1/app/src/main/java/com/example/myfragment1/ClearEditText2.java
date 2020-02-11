/*
package com.example.myfragment1;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ClearEditText2 extends LinearLayout {
    LayoutInflater inflater = null;
    //AutoCompleteTextView editText;
    EditText editText;
    Button btnClear;
    private Activity activity;
    private Toast toast;                    // 종료 안내 문구 Toast


    public static Context mContext;




    public ClearEditText2(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayout();
        mContext = context;
    }

    private void setLayout() {
        //레이아웃을 설정
        inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout., this, true);

        editText = findViewById(R.id.editTextSearch);
        btnClear = (Button) findViewById(R.id.editTextClear);
        btnClear.setVisibility(RelativeLayout.INVISIBLE);

        clearText();
        showHideClearButton();
    }

    //X버튼이 나타났다 사라지게하는 메소드
    private void showHideClearButton() {
        //TextWatcher를 사용해 에디트 텍스트 내용이 변경 될 때 동작할 코드를 입력
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //에디트 텍스트 안 내용이 변경될 때마다 호출되는 메소드
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    btnClear.setVisibility(RelativeLayout.VISIBLE);
                } else {
                    btnClear.setVisibility(RelativeLayout.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
*/
/*
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().contains(" ")){
                    ((MainActivity)mContext).hashtag_Add(s.toString().replaceAll(" ", "").trim());
                }
            }*//*


        });
    }

            //텍스트 삭제
            private void clearText() {
                btnClear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast.makeText(getContext(), "실행됨", Toast.LENGTH_LONG).show();
                        editText.setText("");
                    }
                });
            } //clear 종료
    }*/
