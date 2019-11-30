package com.example.customsearch.presenters;

import com.example.customsearch.interactors.SearchInteractor;
import com.example.customsearch.networking.BackendFactory;
import com.example.customsearch.pojo.SearchResult;
import com.example.customsearch.response.SearchResponse;
import com.example.customsearch.ui.SearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenterImpl implements SearchPresenter {

    private SearchView view;
    private SearchInteractor searchInteractor = BackendFactory.getSearchInteractor();

    @Override
    public void setBaseView(SearchView view) {

        this.view = view;
    }

    @Override
    public void getSearchResults(String query) {
        searchInteractor.getSearchResults(query, getSearchResultsCallback());
    }

    private Callback<SearchResponse> getSearchResultsCallback(){
        return new Callback<SearchResponse>(){

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getSearchResultList()!=null){
                        view.addSearchResults(response.body().getSearchResultList());
                    }else {
                        view.noResultMessage();
                    }
                }
                else {
                    view.unsuccessfulResponseMessage();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                view.failureMessage();
            }
        };
    }
}
