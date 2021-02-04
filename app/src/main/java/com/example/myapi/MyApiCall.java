package com.example.myapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiCall {
    //https://run.mocky.io/v3/   ecd0b8ea-c290-44af-a6a6-ce3cd5bd5854
    @GET("rates")
    Call<DataModel> getData();

}
