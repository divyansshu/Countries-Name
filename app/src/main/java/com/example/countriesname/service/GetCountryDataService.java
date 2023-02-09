package com.example.countriesname.service;

import com.example.countriesname.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {

    //Retrofit interface
    @GET("countries")
    Call<Result> getResult();
}
