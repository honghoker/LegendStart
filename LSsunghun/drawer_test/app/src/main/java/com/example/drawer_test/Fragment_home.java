package com.example.drawer_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_home extends Fragment{
    private int count;
    TextView home_frag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Button countUp = (Button)getView().findViewById(R.id.btn_count);
//        countUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count++;
//                String str = Integer.toString(count);
//                home_frag.setText(str);
//            }
//        });
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

}
