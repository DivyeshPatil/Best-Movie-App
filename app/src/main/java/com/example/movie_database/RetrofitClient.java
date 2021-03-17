package com.example.movie_database;

import com.google.gson.Gson;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static retrofit2.Retrofit retrofit;
    public static retrofit2.Retrofit getRetrofit()
    {
        if (retrofit == null) {
            String baseUrl = "https://api.themoviedb.org/3/movie/";

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
