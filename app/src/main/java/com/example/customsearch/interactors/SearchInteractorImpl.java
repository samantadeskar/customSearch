package com.example.customsearch.interactors;

import com.example.customsearch.constants.Constants;
import com.example.customsearch.networking.BackendInterface;
import com.example.customsearch.response.SearchResponse;

import retrofit2.Callback;

public class SearchInteractorImpl implements SearchInteractor{

    private final BackendInterface backendInterface;

    public SearchInteractorImpl(BackendInterface backendInterface){
        this.backendInterface = backendInterface;
    }


    @Override
    public void getSearchResults(String query, Callback<SearchResponse> searchResponseCallback) {
        backendInterface.getSearchResults(query, Constants.SEARCH_ID, Constants.API_KEY).enqueue(searchResponseCallback);
    }

}
