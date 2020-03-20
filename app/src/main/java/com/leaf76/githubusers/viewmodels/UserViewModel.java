package com.leaf76.githubusers.viewmodels;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leaf76.githubusers.models.DataModel;
import com.leaf76.githubusers.models.Item;

import java.util.List;

public class UserViewModel extends ViewModel {

    public final ObservableBoolean isLoading = new ObservableBoolean(false);

    private  final MutableLiveData<List<Item>> items = new MutableLiveData<>();

    private DataModel dataModel;

    public UserViewModel(DataModel dataModel){
        super();
        this.dataModel = dataModel;
    }

    public LiveData<List<Item>> getUsers() { return items; }

    public void searchUser(String query){
        isLoading.set(true);

        dataModel.searchUser(query, new DataModel.OnDataCallback() {
            @Override
            public void onDataReady(List<Item> data) {
                items.setValue(data);
                isLoading.set(false);
            }
        });
    }


}
