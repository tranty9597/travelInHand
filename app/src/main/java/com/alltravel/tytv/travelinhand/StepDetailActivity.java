package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.adapters.RestaurantBookingAdapter;
import com.alltravel.tytv.travelinhand.model.base.RestaurantBooking;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.model.base.TravelStep;
import com.alltravel.tytv.travelinhand.services.TravelStepService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.singleton.UserInstance;
import com.alltravel.tytv.travelinhand.utils.DownloadImageTask;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StepDetailActivity extends AppCompatActivity {
    TravelStepService travelStepService;

    ImageView fromImg, toImg;
    TextView timeTxt, hotelTxt, tranportTxt;
    TravelStep travelStep;
    Button sBtn ;
    ListView restaurantBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        fromImg = findViewById(R.id.fromImg);
        toImg = findViewById(R.id.toImg);
        timeTxt = findViewById(R.id.timeStep);
        hotelTxt = findViewById(R.id.hotelStep);
        tranportTxt = findViewById(R.id.tranportstep);
        restaurantBooking = findViewById(R.id.restaurantBkList);
        sBtn = findViewById(R.id.changeStatusBtn);

        Intent commingIntent = getIntent();
        travelStep = new TravelStep();
        int stepID = commingIntent.getIntExtra("stepID", -1);
        travelStepService = RetrofitInstance.getRetrofitInstance().create(TravelStepService.class);
        fetchDetail(stepID);
    }


    private void fetchDetail(int stepID){
       retrofit2.Call<Object> call = travelStepService.getStepDetail(stepID);

        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(retrofit2.Call<Object> call, Response<Object> response) {
                JsonParser parser = new JsonParser();

                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else {
                    JsonObject data = resJson.get("data").getAsJsonObject();
                    JsonObject fromCity = data.get("fromCity").getAsJsonObject();
                    JsonArray fromCityImg = fromCity.get("images").getAsJsonArray();
                    travelStep.setId(data.get("ID").getAsInt());
                    travelStep.setStatus(data.get("status").getAsInt());

                    setButton(travelStep.getStatus());
                    for (JsonElement e: fromCityImg) {
                        new DownloadImageTask(fromImg).execute(RetrofitInstance.BASE_URL + "image/getByID?id=" + e.getAsString());
                    }

                    JsonObject toCity = data.get("toCity").getAsJsonObject();
                    JsonArray toCityImg = fromCity.get("images").getAsJsonArray();

                    for (JsonElement e: fromCityImg) {
                        new DownloadImageTask(toImg).execute(RetrofitInstance.BASE_URL + "image/getByID?id=" + e.getAsString());
                    }

                    JsonObject hotel = data.get("hotel").getAsJsonObject();

                    hotelTxt.setText("Hotel:         " + hotel.get("hotelNm").getAsString());

                    JsonObject tranport = data.get("tranpostation").getAsJsonObject();

                    tranportTxt.setText("Tranpostation: " + tranport.get("transpotationNm").getAsString());

                    JsonArray resBookingsJson = data.get("restaurantBooking").getAsJsonArray();

                    ArrayList<RestaurantBooking> restaurantBooking = new ArrayList<>();
                    for (JsonElement e: resBookingsJson ){
                        JsonObject b = (JsonObject) e;
                        restaurantBooking.add(new RestaurantBooking(
                               b.get("ID").getAsInt(),
                                b.get("travelStepID").getAsInt(),
                                b.get("imageID").getAsInt(),
                                b.get("restaurantID").getAsString(),
                                b.get("bookingTime").getAsString().substring(0, 10),
                                b.get("restaurantNm").getAsString()
                        ));

                    }
                    setBookingList(restaurantBooking);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Object> call, Throwable t) {

            }
        });
    }
    private void setBookingList(ArrayList<RestaurantBooking> list){
        RestaurantBookingAdapter adapter = new RestaurantBookingAdapter(this, list);

        restaurantBooking.setAdapter(adapter);
    }

    private void setButton(int status){
        switch (status){
            case 0:
                sBtn.setText("Start");
                sBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeStaus(1);
                    }
                });

                break;
            case 1:
                sBtn.setText("Done");
                sBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeStaus(2);
                    }
                });

                break;
            case 2:
                sBtn.setText("Done");
                sBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeStaus(3);
                    }
                });
        }
    }
    private void changeStaus(int staus){
        retrofit2.Call<Object> call = travelStepService.changeStatusStep(travelStep.getId(), staus);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(retrofit2.Call<Object> call, Response<Object> response) {
                JsonParser parser = new JsonParser();

                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else {
                    JsonObject data = resJson.get("data").getAsJsonObject();
                    finish();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Object> call, Throwable t) {

            }
        });
    }
}
