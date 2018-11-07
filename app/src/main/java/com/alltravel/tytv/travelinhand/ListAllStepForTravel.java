package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.alltravel.tytv.travelinhand.adapters.AddTravelHotelAdapter;
import com.alltravel.tytv.travelinhand.adapters.NewTravelAdapter;
import com.alltravel.tytv.travelinhand.model.base.Hotel;
import com.alltravel.tytv.travelinhand.model.base.StepTravel;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.services.TravelStepService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAllStepForTravel extends AppCompatActivity {

    private ArrayList<StepTravel> stepTravels;
    private ListView listView;
    private int insertKey =-1;
    private Button btnDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        insertKey =intent.getIntExtra("insertKey",-1);
        setContentView(R.layout.activity_list_all_step_for_travel);
        ImageView imageView = findViewById(R.id.addImage);
        listView = findViewById(R.id.createTravelList);
        stepTravels = new ArrayList<>();
        btnDone=findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenttt= new Intent(getApplicationContext(),DasboardActivity.class);
                startActivity(intenttt);
                finish();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateTravelLocation.class);
                intent.putExtra("insertKey",insertKey);
                System.out.println(">>>>list Step"+insertKey);
                startActivity(intent);
                finish();
            }
        });
        TravelStepService travelStepService = RetrofitInstance.getRetrofitInstance().create(TravelStepService.class);
        Call<Object> call = travelStepService.getTravelSteps(insertKey);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                JsonParser parser = new JsonParser();
                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else{
                    JsonArray data = resJson.getAsJsonArray("data");
                    for (JsonElement step: data) {
                        JsonObject stepObj = (JsonObject) step;
                        stepTravels.add(new StepTravel(
                                stepObj.get("ID").getAsInt(),
                                stepObj.get("travelID").getAsInt(),
                                stepObj.get("fromCity").getAsString(),
                                stepObj.get("toCity").getAsString(),
                                stepObj.get("tranpostationID").getAsString(),
                                stepObj.get("hotelID").getAsString(),
                                stepObj.get("restaurantBookingID").getAsInt(),
                                stepObj.get("startDate").getAsString(),
                                stepObj.get("endDate").getAsString()
                        ));
                    }
                    setList();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    private void setList(){
        NewTravelAdapter adapter = new NewTravelAdapter(stepTravels,this);
        listView.setAdapter(adapter);
    }

}
