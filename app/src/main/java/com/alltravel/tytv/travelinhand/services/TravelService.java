package com.alltravel.tytv.travelinhand.services;

import com.alltravel.tytv.travelinhand.model.TravelList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TravelService {
    @GET("travel/get")
    Call<TravelList> getTravels(@Query("username") String username);
}
