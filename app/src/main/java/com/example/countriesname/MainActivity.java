package com.example.countriesname;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.countriesname.adapters.CountryAdapter;
import com.example.countriesname.model.CountryModel;
import com.example.countriesname.model.Result;
import com.example.countriesname.service.GetCountryDataService;
import com.example.countriesname.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;
    private ArrayList<CountryModel> countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetCountries();
    }

    public Object GetCountries() {
        GetCountryDataService getCountryDataService = RetrofitInstance.getService();
        Call<Result> call = getCountryDataService.getResult();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if(result != null && result.getResult() != null) {
                    countries = (ArrayList<CountryModel>) result.getResult();
                }
//                for (CountryModel c: countries) {
//                    Log.i("TAG", ""+c.getName());
//                }
                viewData();

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return countries;
    }

    private void viewData() {
        recyclerView = findViewById(R.id.recyclerView);
        countryAdapter = new CountryAdapter(countries);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);
    }
}