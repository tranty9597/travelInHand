package com.alltravel.tytv.travelinhand.services;

import com.alltravel.tytv.travelinhand.model.TravelList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface TravelService {
    @GET("travel/get")
    Call<Object> getTravels(@Query("username") String username);

    @GET("travelStep/get")
    Call<Object> getTravelSteps(@Query("travelID") int travelId);


}
