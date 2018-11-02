package com.alltravel.tytv.travelinhand.services;

import com.alltravel.tytv.travelinhand.model.TravelList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TravelStepService {

    @GET("travelStep/get")
    Call<Object> getTravelSteps(@Query("travelID") int travelId);
}
