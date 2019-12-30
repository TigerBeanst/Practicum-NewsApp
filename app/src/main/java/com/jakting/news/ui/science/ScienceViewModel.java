package com.jakting.news.ui.science;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScienceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScienceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is science fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}