package com.example.customsearch.interactors;
import com.example.customsearch.response.SearchResponse;

import retrofit2.Callback;

public interface SearchInteractor {

    void getSearchResults(String query, Callback<SearchResponse> searchResponseCallback);

}
