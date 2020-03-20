package com.leaf76.githubusers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {

    @SerializedName("total_count")
    @Expose
    private float total_count;
    @SerializedName("incomplete_results")
    @Expose
    private boolean incomplete_results;
    @SerializedName("items")
    private List<Item> items;


    // Getter Methods

    public float getTotal_count() {
        return total_count;
    }

    public boolean getIncomplete_results() {
        return incomplete_results;
    }

    public List<Item> getItems() {return items;}

    // Setter Methods

    public void setTotal_count(float total_count) {
        this.total_count = total_count;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }
    public void setItems(List<Item> items){ this.items = items; }
}
