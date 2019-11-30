package com.example.customsearch.presenters;

import com.example.customsearch.pojo.SearchResult;
import com.example.customsearch.ui.SearchView;

public interface SearchPresenter {

    void setBaseView(SearchView view);

    void getSearchResults(String query);
}
