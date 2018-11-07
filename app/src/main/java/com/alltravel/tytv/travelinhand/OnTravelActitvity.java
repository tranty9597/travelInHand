package com.alltravel.tytv.travelinhand;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
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
    private ArrayList<TravelStep> travelSteps;
    TravelStepService travelStepService;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_doneStep:
                    ArrayList<TravelStep> doneSteps = new ArrayList<>();

                    for (TravelStep s: travelSteps) {
                        if(s.getStatus() == 2){
                            doneSteps.add(s);
                        }
                    }
                    setListView(doneSteps);
                    return true;
                case R.id.navigation_onGoingStep:
                    ArrayList<TravelStep> onGoingSteps = new ArrayList<>();

                    for (TravelStep s: travelSteps) {
                        if(s.getStatus() == 1){
                            onGoingSteps.add(s);
                        }
                    }
                    setListView(onGoingSteps);
                    return true;
                case R.id.navigation_aboutTo:
                    ArrayList<TravelStep> aboutToStep = new ArrayList<>();

                    for (TravelStep s: travelSteps) {
                        if(s.getStatus() == 0){
                            aboutToStep.add(s);
                        }
                    }
                    setListView(aboutToStep);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_travel);
        stepListView = findViewById(R.id.stepListView);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        travelStepService = RetrofitInstance.getRetrofitInstance().create(TravelStepService.class);
        getTravelSteps();
    }
    private void getTravelSteps(){
        Call<Object> call = travelStepService.getTravelSteps(1);

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
                                stepObj.get("status").getAsInt()
                        ));
                    }
                    setTravelSteps(travelSteps);
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
}
