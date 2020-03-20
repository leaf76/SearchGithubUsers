package com.leaf76.githubusers.api;

import android.graphics.pdf.PdfDocument;

import com.leaf76.githubusers.models.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {
    @GET("search/users")
    Call<ItemResponse> searchUsers(
            @Query("q") String query
    );

}
