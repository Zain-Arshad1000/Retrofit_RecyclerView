package com.example.retrofitrecyclerview.Activities;

import com.example.retrofitrecyclerview.Adapters.AnimeAdapter;
import com.example.retrofitrecyclerview.api.Service;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitrecyclerview.Model.Anime;
import com.example.retrofitrecyclerview.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Anime> movieArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
        loadJSON();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewId);
    }

    private void loadJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service apiService = retrofit.create(Service.class);
        Call<List<Anime>> call = apiService.getMovies();
        call.enqueue(new Callback<List<Anime>>() {

            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                movieArrayList = response.body();
                AnimeAdapter adapter = new AnimeAdapter(getApplicationContext(), movieArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}