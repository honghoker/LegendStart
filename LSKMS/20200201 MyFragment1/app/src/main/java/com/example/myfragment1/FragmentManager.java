/*
package com.example.myfragment1;

import android.app.Activity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentManager extends AppCompatActivity {
    private Toast toast;                    // 종료 안내 문구 Toast
    private Activity activity;              // 종료할 액티비티의 Activity 객체

    public FragmentManager(Activity _activity) {
        this.activity = _activity;
    }
    public void onFragmentChange(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment2).commit();
        }
    }
}
*/
