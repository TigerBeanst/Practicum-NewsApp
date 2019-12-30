package com.jakting.news.ui.digital;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DigitalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DigitalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is digital fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}