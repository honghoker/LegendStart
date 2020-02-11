package com.example.myfragment1;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Clearsington extends AppCompatActivity {
    private static Clearsington instance_ = new Clearsington();
    private Toast toast;
    EditText editText;
    Button btneditClear;
    private Clearsington()
    {
        editText = findViewById(R.id.editTextSearch);
        btneditClear = findViewById(R.id.editTextClear);
        //생성자
    }
    public static Clearsington getInstance()
    {
        return instance_; //내부에 있는 instance 를 리턴함 singleton
    }

    private void clearText() {
        btneditClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.makeText(v.getContext(), "삭제됨", Toast.LENGTH_LONG).show();
                editText.setText("");
            }
        });
    } //clear 종료


}
