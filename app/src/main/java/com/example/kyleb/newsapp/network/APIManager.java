package com.example.kyleb.newsapp.network;

import retrofit.Retrofit;

/**
 * Created by kyleb on 2015-09-16.
 */
public class APIManager {

    private static APIManager instance = null;
    protected APIManager() {
        Retrofit retrofit =  new Retrofit.Builder().build();

    }
    public static APIManager getInstance() {
        if(instance == null) {
            instance = new APIManager();
        }
        return instance;
    }
}
