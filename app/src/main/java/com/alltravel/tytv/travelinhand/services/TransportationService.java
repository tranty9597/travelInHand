package com.alltravel.tytv.travelinhand.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TransportationService {

    @GET("transportation/get")
    Call<Object> getTransportation(@Query("fromCityID") String from_city_id, @Query("toCityID") String to_city_id);
}
