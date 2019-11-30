package com.example.customsearch.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customsearch.R;
import com.example.customsearch.listeners.SearchResultClickListener;
import com.example.customsearch.pojo.SearchResult;
import com.example.customsearch.presenters.SearchPresenter;
import com.example.customsearch.presenters.SearchPresenterImpl;
import com.example.customsearch.ui.adapters.SearchAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchView {

    @BindView(R.id.editText_search)
    EditText editText_search;
    @BindView(R.id.recycler_search)
    RecyclerView recycler_search;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textView_search)
    TextView textView_search;

    SearchPresenter presenter = new SearchPresenterImpl();
    SearchAdapter searchAdapter;

    SearchResultClickListener clickListener = new SearchResultClickListener() {
        @Override
        public void onClickURL(SearchResult searchResult) {
            String link = searchResult.getLink();
            openInBrowser(link);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        presenter.setBaseView(this);

        searchAdapter = new SearchAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_search.setLayoutManager(linearLayoutManager);
        recycler_search.setItemAnimator(new DefaultItemAnimator());
        recycler_search.setAdapter(searchAdapter);
    }

    @OnClick(R.id.textView_search)
    public void search() {
        textView_search.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        String query = editText_search.getText().toString();
        presenter.getSearchResults(query);

    }

    @Override
    public void addSearchResults(List<SearchResult> searchResultsList) {

        searchAdapter = new SearchAdapter(searchResultsList, clickListener);
        recycler_search.setAdapter(searchAdapter);
        progressBar.setVisibility(View.INVISIBLE);
        textView_search.setVisibility(View.VISIBLE);
    }

    @Override
    public void noResultMessage() {
        Toast.makeText(this, "noResult", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unsuccessfulResponseMessage() {
        Toast.makeText(this, "unsuccesfullResponse", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureMessage() {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }

    private void openInBrowser(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }
}
