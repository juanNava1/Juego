package com.example.siluetas.ui.encuentra;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EncuentraViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EncuentraViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}