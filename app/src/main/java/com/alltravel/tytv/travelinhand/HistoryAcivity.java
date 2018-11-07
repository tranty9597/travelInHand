package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.alltravel.tytv.travelinhand.adapters.HistoryAdapter;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.services.TravelService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.singleton.TravelInstance;
import com.alltravel.tytv.travelinhand.singleton.UserInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
                    JsonArray travelJson = resJson.getAsJsonArray("data");
                    for(Object o : travelJson) {
                        try {
                        JsonObject travelJsonEle = (JsonObject) o;
                        Travel travel = new Travel();
                        travel.setID(travelJsonEle.get("ID").getAsInt());
                        travel.setUssername(travelJsonEle.get("username").getAsString());
                        String strDateCreated = null;
                        Date dateCreated = df1.parse(travelJsonEle.get("dateCreated").getAsString());
                        strDateCreated = df2.format(dateCreated);
                        travel.setDateCreated(strDateCreated);
                        travel.setTravelNm(travelJsonEle.get("travelNm").getAsString());
                        travel.setTravelDes(travelJsonEle.get("travelDes").getAsString());
                        travel.setStatus(travelJsonEle.get("status").getAsInt());
                        lstTravel.add(travel);
                        } catch (Exception e) {
                            Log.d("Error", "Loi Cmnr");
                        }
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
        adapter = new HistoryAdapter(this);
        ((HistoryAdapter) adapter).setLstTravel(lstTravel);
        recyclerView.setAdapter(adapter);
    }

    public void doDisplayDetail(Travel travel) {
        Intent i = new Intent(this, HistoryDetailActivity.class);
        i.putExtra("Travel", travel);
        startActivity(i);
    }
}
