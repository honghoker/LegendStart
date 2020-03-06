package com.example.myfragment1.MSMain;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfragment1.R;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ms_activity_add_location);

        //Button mainBtn = (Button)findViewById(R.id.)
    }
    public void onIntentButtonClicked(View v){
        switch (v.getId()) {
            case R.id.btnMain:
                finish();

                break;
            default: break;
        }
    }
}
