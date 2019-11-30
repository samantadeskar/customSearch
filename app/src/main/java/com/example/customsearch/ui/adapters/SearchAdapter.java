package com.example.customsearch.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.customsearch.R;
import com.example.customsearch.listeners.SearchResultClickListener;
import com.example.customsearch.pojo.SearchResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<SearchResult> resultList;
    private SearchResultClickListener clickListener;

    public SearchAdapter() {
        resultList = new ArrayList<>();
    }

    public SearchAdapter(List<SearchResult> resultList, SearchResultClickListener clickListener) {
        this.resultList = resultList;
        this.clickListener = clickListener;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new SearchViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        SearchResult searchResult = resultList.get(position);
        holder.setData(searchResult);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_searchResult)
        TextView textView_title;
        @BindView(R.id.textView_searchResultURL)
        TextView textView_link;
        @BindView(R.id.textView_htmlSnippet)
        TextView textView_htmlSnippet;

        public SearchViewHolder(View itemView, SearchResultClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(SearchResult searchResult) {

            textView_title.setText(searchResult.getTitle());
            textView_link.setText(searchResult.getLink());
            textView_htmlSnippet.setText(searchResult.getHtmlSnippet());

        }

        @OnClick(R.id.textView_searchResultURL)
        public void onLinkClick(){
            clickListener.onClickURL(resultList.get(getAdapterPosition()));
        }
    }
}
