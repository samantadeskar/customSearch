package com.example.customsearch.pojo;

import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("htmlSnippet")
    private String htmlSnippet;

    public SearchResult() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHtmlSnippet() {
        return htmlSnippet;
    }

    public void setHtmlSnippet(String htmlSnippet) {
        this.htmlSnippet = htmlSnippet;
    }
}
