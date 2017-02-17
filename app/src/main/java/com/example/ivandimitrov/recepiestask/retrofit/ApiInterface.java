package com.example.ivandimitrov.recepiestask.retrofit;

import com.example.ivandimitrov.recepiestask.retrofit.beer.Example;
import com.example.ivandimitrov.recepiestask.retrofit.brewery.ExampleBrewery;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Ivan Dimitrov on 2/10/2017.
 */

public interface ApiInterface {
    @GET("beer/random?key=ae6f8ff58acfc1a25628b22bf59f0153")
    Observable<Example> getBeerData();

    @GET("brewery/random?key=ae6f8ff58acfc1a25628b22bf59f0153")
    Observable<ExampleBrewery> getBreweryData();
}
