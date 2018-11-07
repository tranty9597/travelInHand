package com.alltravel.tytv.travelinhand.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HotelService {
    @GET("hotel/getByCityOrLocation")
    Call<Object> getHotels(@Query("cityID") String cityID);
}
