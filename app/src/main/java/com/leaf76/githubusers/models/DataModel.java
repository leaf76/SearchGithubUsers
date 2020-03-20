package com.leaf76.githubusers.models;

import com.leaf76.githubusers.api.GithubService;
import com.leaf76.githubusers.manager.ClientManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataModel {

    private GithubService githubService = ClientManager.getAPI();

    public void searchUser(String query, final OnDataCallback callback){
        githubService.searchUsers(query)
                .enqueue(new Callback<ItemResponse>() {
                    @Override
                    public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                        callback.onDataReady(response.body().getItems());
                    }
                    @Override
                    public void onFailure(Call<ItemResponse> call, Throwable t) {
                        // error handlers
                    }
                });

    }

    public interface OnDataCallback {
        void onDataReady(List<Item> data);
    }

}
