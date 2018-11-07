package com.alltravel.tytv.travelinhand.services;

import com.alltravel.tytv.travelinhand.model.base.RestaurantBooking;
import com.alltravel.tytv.travelinhand.model.base.Travel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestaurantBookingService {
    @POST("restaurantBooking/create")
    Call<Object> addRestaurantBooking(@Body RestaurantBooking restaurantBooking);
}
