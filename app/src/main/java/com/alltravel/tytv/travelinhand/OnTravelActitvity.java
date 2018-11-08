package com.alltravel.tytv.travelinhand;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.adapters.StepAdapter;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.model.base.TravelStep;
import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.services.TravelService;
import com.alltravel.tytv.travelinhand.services.TravelStepService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.singleton.UserInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnTravelActitvity extends AppCompatActivity {

    private ListView stepListView;
    private ArrayList<TravelStep>travelSteps;
    TravelStepService travelStepService;
    int tabIndex = 0;
    int travelID = -1;
    CardView compteBtn;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_doneStep:
                    ArrayList<TravelStep> doneSteps= setListByStaus(2);
                    if(doneSteps.size() == travelSteps.size()){
                        compteBtn.setVisibility(View.VISIBLE);

                    }
                    setListView(doneSteps);
                    return true;
                case R.id.navigation_onGoingStep:
                    setListByStaus(1);
                    tabIndex = 1;
                    return true;
                case R.id.navigation_aboutTo:
                    setListByStaus(0);
                    tabIndex = 2;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onResume() {
        if(travelSteps != null) getTravelSteps();
        switch (tabIndex){
            case 0:
                setListByStaus(0);
                break;
            case 1:
                setListByStaus(1);
                break;
            case 2:
                setListByStaus(2);
                break;
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_travel);
        compteBtn=findViewById(R.id.completeBtn);
        compteBtn.setVisibility(View.INVISIBLE);
        stepListView = findViewById(R.id.stepListView);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        travelStepService = RetrofitInstance.getRetrofitInstance().create(TravelStepService.class);
        fetchActiveTravel();

    }

    private void fetchActiveTravel(){
        retrofit2.Call<Object> call = travelStepService.getActiveTravel(UserInstance.getUserInstance().getUsername());

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(retrofit2.Call<Object> call, Response<Object> response) {
                JsonParser parser = new JsonParser();

                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else {
                    JsonObject data = resJson.get("data").getAsJsonObject();
                    travelID = data.get("ID").getAsInt();
                    getTravelSteps();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Object> call, Throwable t) {

            }
        });
    }
    private void getTravelSteps(){
        Call<Object> call = travelStepService.getTravelSteps(travelID);

        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                JsonParser parser = new JsonParser();

                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    System.out.println("Is  error");
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
                                stepObj.get("status").getAsInt(),
                                stepObj.get("imageID").getAsInt()
                        ));
                    }
                    setTravelSteps(travelSteps);
                    ArrayList<TravelStep> doneSteps= setListByStaus(2);
                    if(doneSteps.size() == travelSteps.size()){
                        compteBtn.setVisibility(View.VISIBLE);
                        compteBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    public void setTravelSteps(ArrayList<TravelStep> doneTravelSteps) {
        this.travelSteps = doneTravelSteps;
    }

    private void setListView(ArrayList<TravelStep> steps){
        StepAdapter stepAdapter = new StepAdapter(this, steps);
        stepListView.setAdapter(stepAdapter);
    }
    private ArrayList<TravelStep> setListByStaus(int status){
        ArrayList<TravelStep> list = new ArrayList<>();
        if(travelSteps == null) return new ArrayList<>();
        for (TravelStep s: travelSteps) {
            if(s.getStatus() == status){
                list.add(s);
            }
        }

        setListView(list);
        return  list;
    }
    public void changeStaus(View v){
        retrofit2.Call<Object> call = travelStepService.changeStatusTravel(travelID, 2);

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
