package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.ImageView;

import com.alltravel.tytv.travelinhand.model.base.TravelStep;
import com.alltravel.tytv.travelinhand.services.TravelStepService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.utils.DownloadImageTask;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

public class StepDetailActivity extends AppCompatActivity {
    TravelStepService travelStepService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        Intent commingIntent = getIntent();

        int stepID = commingIntent.getIntExtra("stepID", -1);
        travelStepService = RetrofitInstance.getRetrofitInstance().create(TravelStepService.class);
        new DownloadImageTask((ImageView) findViewById(R.id.testimg)).execute(RetrofitInstance.BASE_URL+"image/getByID?id=2");
    }

    private void fetchDetail(){
       retrofit2.Call<Object> call = travelStepService.getTravelSteps(1);

        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(retrofit2.Call<Object> call, Response<Object> response) {
                JsonParser parser = new JsonParser();

                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else {
                    JsonArray data = resJson.get("data").getAsJsonArray();
                    ArrayList<TravelStep> travelSteps = new ArrayList<>();
                    for (JsonElement step: data) {
                        JsonObject stepObj = (JsonObject) step;
                        travelSteps.add(new TravelStep(
                                stepObj.get("ID").getAsInt(),
                                stepObj.get("startDate").getAsString(),
                                stepObj.get("endDate").getAsString(),
                                stepObj.get("fromCity").getAsString(),
                                stepObj.get("toCity").getAsString(),
                                stepObj.get("status").getAsInt()
                        ));
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Object> call, Throwable t) {

            }
        });
    }
}
