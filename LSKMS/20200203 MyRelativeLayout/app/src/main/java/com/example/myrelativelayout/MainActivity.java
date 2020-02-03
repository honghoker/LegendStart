package com.example.myrelativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ImageView ibtn;
    LinearLayout lout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    ibtn = findViewById(R.id.imageButton);
    lout = findViewById(R.id.LinearLayout);


        //view.startAnimation(animation);





    }
    public void onClickButtoned(View v){
        switch (v.getId()){
            case R.id.buttonHide:
                Animation animationH = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translatehide);
                ibtn.setAnimation(animationH);
               // lout.setAnimation(animationH);
                ibtn.setVisibility(v.GONE);
              //  lout.setVisibility(v.GONE);
                break;
            case R.id.buttonShow:
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
                ibtn.setAnimation(animation);
              //  lout.setAnimation(animation);
                ibtn.setVisibility(v.VISIBLE);
             //   lout.setVisibility(v.VISIBLE);


                break;
                default: break;
        }
    }


}
