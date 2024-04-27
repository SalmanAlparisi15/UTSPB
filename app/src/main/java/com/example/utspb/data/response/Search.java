package com.example.utspb.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("items")
    private List<Responses> users;

    public List<Responses> getUsers(){
        return users;
    }
}
