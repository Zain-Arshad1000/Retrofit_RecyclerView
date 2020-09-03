package com.example.retrofitrecyclerview.api;

import com.example.retrofitrecyclerview.Model.Anime;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    String BASE_URL="https://www.simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<Anime>> getMovies();
}