package com.alltravel.tytv.travelinhand.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestaurantService {
    @GET("restaurant/getByCityOrLocation")
    Call<Object> getRestaurant(@Query("cityID") String cityID);
}
