package com.alltravel.tytv.travelinhand;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.alltravel.tytv.travelinhand.adapters.HistoryAdapter;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.services.TravelService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.singleton.UserInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryAcivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        makeList();
    }

    public void makeList() {
        User user = UserInstance.getUserInstance();
        TravelService travelService = RetrofitInstance.getRetrofitInstance().create(TravelService.class);
        Call<Object> call = travelService.getTravels(user.getUsername());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                ArrayList<Travel> lstTravel = new ArrayList<>();
                JsonParser parser = new JsonParser();
                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else{
                    JsonArray travelJson = resJson.getAsJsonArray("data");
                    for(Object o : travelJson) {
                        Log.d("Error", "Loi Cmnr");
                        JsonObject travelJsonEle = (JsonObject) o;
                        Travel travel = new Travel();
                        travel.setID(travelJsonEle.get("ID").getAsInt());
                        travel.setUssername(travelJsonEle.get("username").getAsString());
                        travel.setDateCreated(travelJsonEle.get("dateCreated").getAsString());
                        travel.setTravelNm(travelJsonEle.get("travelNm").getAsString());
                        travel.setTravelDes(travelJsonEle.get("travelDes").getAsString());
                        travel.setStatus(travelJsonEle.get("status").getAsInt());
                        lstTravel.add(travel);
                    }
                    transferList(lstTravel);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.wtf("Error cmnr", t.getMessage());
            }
        });
    }

    public void transferList(ArrayList<Travel> lstTravel) {
        recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HistoryAdapter();
        ((HistoryAdapter) adapter).setLstTravel(lstTravel);
        recyclerView.setAdapter(adapter);
    }

}
