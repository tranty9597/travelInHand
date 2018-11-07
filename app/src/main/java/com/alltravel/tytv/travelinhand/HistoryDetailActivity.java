package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.model.base.HistoryStepDetail;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.model.base.TravelStep;
import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.services.TravelService;
import com.alltravel.tytv.travelinhand.services.TravelStepService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.singleton.TravelInstance;
import com.alltravel.tytv.travelinhand.singleton.UserInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryDetailActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeadder;
    private HashMap<String, List<String>> lisHash;
    private Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detail);

        Intent i = getIntent();
        travel = (Travel) i.getSerializableExtra("Travel");
        TextView travel_name = (TextView) findViewById(R.id.travel_Name);
        travel_name.setText(travel.getTravelNm());
        TextView travel_status = (TextView) findViewById(R.id.status);
        switch(travel.getStatus()) {
            case 0:
                travel_status.setText("About to");
                break;
            case 1:
                travel_status.setText("On going");
                break;
            case 2:
                travel_status.setText("Done");
                break;
            case 3:
                travel_status.setText("Cancle");
                break;
        }
        TextView travel_createdDate = (TextView) findViewById(R.id.createdDate);
        travel_createdDate.setText(travel.getDateCreated());
        TextView travel_description = (TextView) findViewById(R.id.description);
        travel_description.setText(travel.getTravelDes());
        initData();

    }
    private void initData(){
        lisHash = new HashMap<>();
        listDataHeadder = new ArrayList<>();
        TravelStepService travelStepService = RetrofitInstance.getRetrofitInstance().create(TravelStepService.class);
        Call<Object> call = travelStepService.getHistoryStepDetail(travel.getID());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                JsonParser parser = new JsonParser();
                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else{
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
                    JsonArray travelJson = resJson.getAsJsonArray("data");
                    List<String> temp;
                    int i = 0;
                    for(Object o : travelJson) {
                        try {
                            JsonObject travelJsonEle = (JsonObject) o;
                            temp = new ArrayList<>();
                            String strStartDate = null;
                            Date startDate = df1.parse(travelJsonEle.get("startDate").getAsString());
                            strStartDate = df2.format(startDate);
                            listDataHeadder.add("Step " + (i + 1) + " - "  + strStartDate);
                            String fromCityNm = travelJsonEle.get("fromCityNm") == null? "": "From city: " + travelJsonEle.get("fromCityNm").getAsString();
                            temp.add(fromCityNm);
                            String hotelNm = travelJsonEle.get("hotelNm") == null? "" : "Hotel: " + travelJsonEle.get("hotelNm").getAsString();
                            temp.add(hotelNm);
                            String tranpostationNm = travelJsonEle.get("tranpostationNm") == null ? "" : "Transportation: " + travelJsonEle.get("tranpostationNm").getAsString();
                            temp.add(tranpostationNm);
                            if(travelJsonEle.get("restaurantNm") == null) {
                                temp.add("Restaurant: " + "");
                            } else {
                                temp.add("Restaurant: " + travelJsonEle.get("restaurantNm").getAsString());
                            }
                            lisHash.put(listDataHeadder.get(i), temp);
                            i++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    transferData();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.wtf("Error cmnr", t.getMessage());
            }
        });
    }

    private void transferData() {
        listView= (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter =  new com.alltravel.tytv.travelinhand.adapters.ExpandableListAdapter(this, listDataHeadder,lisHash);
        listView.setAdapter(listAdapter);
    }
}
