package com.jakting.news.ui.phone;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PhoneViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PhoneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is phone fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}