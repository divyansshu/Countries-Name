package com.example.countriesname.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://api.printful.com/";

    //singleton pattern to create instance of retrofit
    public static GetCountryDataService getService() {
        //if instance is not created
        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //if instance is created
        return retrofit.create(GetCountryDataService.class);
    }
}
