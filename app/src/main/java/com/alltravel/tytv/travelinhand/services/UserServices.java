package com.alltravel.tytv.travelinhand.services;

import com.alltravel.tytv.travelinhand.model.base.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServices {
    @POST("user/login")
    Call<Object> login(@Body User user);

}
