package com.leaf76.githubusers.manager;

import com.leaf76.githubusers.api.GithubService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ClientManager {
    // Use  Retrofit to implement API
    private static ClientManager mInstance = new ClientManager();
    private static final String BASE_URL = "https://api.github.com/";
    private GithubService githubService;

    private ClientManager(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        githubService = retrofit.create(GithubService.class);
    }

    public static GithubService getAPI() {return  mInstance.githubService; }


}
