package com.example.customsearch.networking;

import com.example.customsearch.response.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BackendInterface {

    @GET("v1?")
    Call<SearchResponse> getSearchResults(@Query("q") String query, @Query("cx") String search_id,
                                          @Query("key") String api_key);

}
