package com.rinndp.retrofitexample.recyclerview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rinndp.retrofitexample.Drinks;

import java.util.List;

public class CocktailViewModel extends AndroidViewModel {

    private final LiveData<Drinks> mListDrinks;

    public CocktailViewModel(@NonNull Application application, LiveData<Drinks> mListDrinks) {
        super(application);
        this.mListDrinks = mListDrinks;
    }

    public LiveData<Drinks> getListDrinks() {
        return this.mListDrinks;
    }
}
