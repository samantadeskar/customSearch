package com.example.customsearch.ui;

import com.example.customsearch.pojo.SearchResult;

import java.util.List;

public interface SearchView {

    void addSearchResults(List<SearchResult> searchResultsList);

    void noResultMessage();

    void unsuccessfulResponseMessage();

    void failureMessage();
}
