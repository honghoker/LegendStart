package com.example.ls_listsave;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class UndoPopup extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.undo_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.2), (int)(height*.08));

        /*
        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);

         */
    }
}
