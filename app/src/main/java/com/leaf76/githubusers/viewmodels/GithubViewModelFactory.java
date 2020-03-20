package com.leaf76.githubusers.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.leaf76.githubusers.models.DataModel;

public class GithubViewModelFactory implements ViewModelProvider.Factory {

    private DataModel dataModel;

    public GithubViewModelFactory() { this.dataModel = new DataModel(); }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(UserViewModel.class)){
            return (T) new UserViewModel(dataModel);
        }
        throw new IllegalArgumentException("Unknow ViewModel class");
    }
}
