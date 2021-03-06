package com.alltravel.tytv.travelinhand.services;

import com.alltravel.tytv.travelinhand.model.TravelList;
import com.alltravel.tytv.travelinhand.model.base.StepTravel;
import com.alltravel.tytv.travelinhand.model.base.StepTravel1;
import com.alltravel.tytv.travelinhand.model.base.Travel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface TravelStepService {

    @GET("travelStep/get")
    Call<Object> getTravelSteps(@Query("travelID") int travelId);

    @GET("travel/getActive")
    Call<Object> getActiveTravel(@Query("username") String username);

    @GET("travelStep/getDetail")
    Call<Object> getStepDetail(@Query("id") int id);

    @GET("travelStep/getHistoryDetail")
    Call<Object> getHistoryStepDetail(@Query("id") int id);

    @POST("travelStep/create")
    Call<Object> addStepTravel(@Body StepTravel1 stepTravel);

    @PUT("travelStep/changeStatus")
    Call<Object> changeStatusStep(@Query("ID") int id, @Query("status") int status);

    @PUT("travel/changeStatus")
    Call<Object> changeStatusTravel(@Query("ID") int id, @Query("status") int status);

}
