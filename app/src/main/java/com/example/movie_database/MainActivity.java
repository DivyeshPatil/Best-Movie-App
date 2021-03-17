package com.example.movie_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Start: BLINK TEXT
        TextView textView = findViewById(R.id.txt);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(300);
        animation.setStartOffset(20);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        textView.startAnimation(animation);
// End: BLINK TEXT

        recyclerView = findViewById(R.id.recyclerView);

        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        retrofitInterface.getModelMovies("5cde978fba55f18d7d15a62d6a29be42").enqueue(new Callback<ModelMovie>() {
            @Override
            public void onResponse(Call<ModelMovie> call, Response<ModelMovie> response) {
//                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this, response.body().getResults()));
            }

            @Override
            public void onFailure(Call<ModelMovie> call, Throwable t) {

            }
        });
    }
}
