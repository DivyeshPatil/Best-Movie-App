package com.example.movie_database;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {
    ImageView imageViewDetails;
//    TextView titleDetals, overview, overviewdetails;
    TextView textViewtitle, titledetail, textViewover, releaseDate, movietagline;
    RatingBar ratingBardetails;

    private String URL = "https://image.tmdb.org/t/p/w342";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imageViewDetails = findViewById(R.id.imgdetails);
        titledetail = findViewById(R.id.textView_title_detail);
        //overview = findViewById(R.id.overview);
        textViewtitle = findViewById(R.id.textView_title);
        textViewover = findViewById(R.id.textView);
        ratingBardetails = findViewById(R.id.ratingBar_details);
        releaseDate = findViewById(R.id.release_date);
        movietagline = findViewById(R.id.tagline);
        moviedetail();
    }

    public void moviedetail() {
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        int id= getIntent().getIntExtra("movie_id",0);
        retrofitInterface.getMovieDetails(String.valueOf(id),"5cde978fba55f18d7d15a62d6a29be42").enqueue(new Callback<ModelMovieDetails>() {
            @Override
            public void onResponse(Call<ModelMovieDetails> call, Response<ModelMovieDetails> response) {
             //   Toast.makeText(MovieDetailsActivity.this, response.body().getOverview(), Toast.LENGTH_SHORT).show();
                Glide.with(MovieDetailsActivity.this).load(URL+response.body().getPosterPath()).into(imageViewDetails);
                textViewtitle.setText(response.body().getTitle());
                textViewover.setText(response.body().getOverview());
                releaseDate.setText(response.body().getReleaseDate());
                movietagline.setText(response.body().getTagline());
            }

            @Override
            public void onFailure(Call<ModelMovieDetails> call, Throwable t) {

            }
        });
    }
}
