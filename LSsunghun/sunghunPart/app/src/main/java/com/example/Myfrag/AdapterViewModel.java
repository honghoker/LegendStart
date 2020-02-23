package com.example.Myfrag;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AdapterViewModel extends AndroidViewModel {

    AppDatabase db;

    public AdapterViewModel(@NonNull Application application) {
        super(application);


    }
}
