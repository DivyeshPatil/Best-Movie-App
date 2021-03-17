package com.example.movie_database;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("popular")
    Call<ModelMovie> getModelMovies(@Query("api_key") String string_key);

    @GET("{id}")
    Call<ModelMovieDetails> getMovieDetails(@Path("id")String stringId,
                                              @Query("api_key")String string_key);
}
