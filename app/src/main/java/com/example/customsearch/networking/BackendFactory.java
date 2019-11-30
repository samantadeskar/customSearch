package com.example.customsearch.networking;

import com.example.customsearch.constants.Constants;
import com.example.customsearch.interactors.SearchInteractor;
import com.example.customsearch.interactors.SearchInteractorImpl;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackendFactory {

    private static Retrofit retrofit = null;

    private static Interceptor getInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static OkHttpClient getHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .build();
    }

    private static Retrofit createRetrofit(){
        retrofit = new Retrofit.Builder()
                .client(getHttpClient())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private static Retrofit getClient(){

        if(retrofit==null){
            createRetrofit();
        }
        return retrofit;
    }

    private static BackendInterface getService(){
        return getClient().create(BackendInterface.class);
    }

    public static SearchInteractor getSearchInteractor(){
        return new SearchInteractorImpl(getService());
    }

}
