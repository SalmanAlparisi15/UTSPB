package com.example.utspb.data.retrofit;

import com.example.utspb.data.response.Responses;
import com.example.utspb.data.response.Search;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //@Headers("Authorization: token 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")
    @GET("users/{username}")
    Call<Responses> getuserService (@Path("username") String username);
    //@Headers("Authorization: token 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")
    @GET("search/users")
    Call<Search> searchUser(@Query("q") String query);

}
