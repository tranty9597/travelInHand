package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alltravel.tytv.travelinhand.Fragment.HotelFragment;
import com.alltravel.tytv.travelinhand.Fragment.RestaurantFragment;
import com.alltravel.tytv.travelinhand.Fragment.TransportationFragment;
import com.alltravel.tytv.travelinhand.adapters.CreateTravelViewPagerAdapter;
import com.alltravel.tytv.travelinhand.model.base.Restaurant;
import com.alltravel.tytv.travelinhand.model.base.RestaurantBooking;
import com.alltravel.tytv.travelinhand.model.base.RestaurantBookingGetting;
import com.alltravel.tytv.travelinhand.model.base.StepTravel;
import com.alltravel.tytv.travelinhand.model.base.StepTravel1;
import com.alltravel.tytv.travelinhand.model.base.Transportation;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.model.base.Travel1;
import com.alltravel.tytv.travelinhand.model.base.TravelStep;
import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.services.RestaurantBookingService;
import com.alltravel.tytv.travelinhand.services.TravelService;
import com.alltravel.tytv.travelinhand.services.TravelStepService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.singleton.UserInstance;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTravelActivity extends AppCompatActivity implements TransportationFragment.OnFragmentInteractionListener, HotelFragment.OnFragmentInteractionListener, RestaurantFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CreateTravelViewPagerAdapter adapter;
    private String fromLocation;
    private String toLocation;
    private Button btnCreateNewStep;
    private int stepTravelID;
    private String transportation_id;
    private ArrayList<Restaurant> restaurants;
    private String hotelID;
    private Travel1 travel;
    private StepTravel1 stepTravel;
    private int insertKey;
    private String fromCity;
    private String toCity;

    public void setTransportation(String transportation_id) {
        this.transportation_id = transportation_id;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public void removeRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }

    public void setHotel(String hotelID) {
        this.hotelID = hotelID;
    }


    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_travel);
        Intent intent = getIntent();
        if (intent != null) {
            this.fromLocation = intent.getStringExtra("FromLocation");
            switch (fromLocation) {
                case "Ha Noi":
                    fromCity = "CITY-2";
                    break;
                case "ha noi":
                    fromCity = "CITY-2";
                    break;
                case "hn":
                    fromCity = "CITY-2";
                    break;
                case "HN":
                    fromCity = "CITY-2";
                    break;
                case "HP":
                    fromCity = "City-1";
                    break;
                case "hp":
                    fromCity = "City-1";
                    break;
                case "Hai Phong":
                    fromCity = "City-1";
                    break;
                case "hai phong":
                    fromCity = "City-1";
                    break;
            }
            this.toLocation = intent.getStringExtra("ToLocation");
            switch (toLocation) {
                case "Ha Noi":
                    toCity = "CITY-2";
                    break;
                case "ha noi":
                    toCity = "CITY-2";
                    break;
                case "hn":
                    toCity = "CITY-2";
                    break;
                case "HN":
                    toCity = "CITY-2";
                    break;
                case "HP":
                    toCity = "City-1";
                    break;
                case "hp":
                    toCity = "City-1";
                    break;
                case "Hai Phong":
                    toCity = "City-1";
                    break;
                case "hai phong":
                    toCity = "City-1";
                    break;
            }
        }
        int insertKey = intent.getIntExtra("insertKey", -1);
        travel = new Travel1();
        travel.setID(insertKey);
        System.out.println(">>>>>>>>>>>>>> InsertedKey:" + insertKey);
        restaurants = new ArrayList<>();
        this.tabLayout = findViewById(R.id.tabLayout);
        this.viewPager = findViewById(R.id.viewPager);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.transportation_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.hotel_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.restaurant_icon);
        this.btnCreateNewStep = findViewById(R.id.btnCreateNewStep);
        User user = UserInstance.getUserInstance();
    }

    private void setupViewPager() {
        adapter = new CreateTravelViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TransportationFragment(), "");
        adapter.addFragment(new HotelFragment(), "");
        adapter.addFragment(new RestaurantFragment(), "");
        viewPager.setAdapter(adapter);
    }

    public String convertDate(Date date) {
        return new SimpleDateFormat("yyyy-dd-MM").format(date);
    }

    public void createNewStep(View v) {
        System.out.println(">>>>>>adding key" + travel.getID());
        insertKey=travel.getID();
        User user = UserInstance.getUserInstance();
        travel = new Travel1(user.getUsername(), "New Travel of " + user.getFullName(), "", convertDate(new Date()));
        Toast.makeText(this, "Transporation ID: " + transportation_id + "\n Restaurant:" + restaurants.size() + "\nHotel:" + hotelID, Toast.LENGTH_SHORT).show();

        if(insertKey==-1){
            TravelService travelService = RetrofitInstance.getRetrofitInstance().create(TravelService.class);
            Call<Object> call = travelService.addTravel(travel);
            call.enqueue(new Callback<Object>() {

                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    JsonParser parser = new JsonParser();
                    JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                    if (resJson.get("isError").getAsBoolean()) {
                    } else {
                        JsonObject travelJson = resJson.getAsJsonObject("data");
                        if (insertKey == -1) {
                            insertKey = travelJson.get("insertId").getAsInt();

                            System.out.println(">>>>>>>" + insertKey);
                        }
                        stepTravel = new StepTravel1(insertKey, fromCity, toCity, transportation_id, hotelID, 1, convertDate(new Date()), convertDate(new Date()));
                        TravelStepService travelStepService = RetrofitInstance.getRetrofitInstance().create(TravelStepService.class);
                        Call<Object> call2 = travelStepService.addStepTravel(stepTravel);
                        call2.enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {
                                JsonParser parser = new JsonParser();
                                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
//                System.out.println("......" + resJson.get("message").getAsString());
                                if (resJson.get("isError").getAsBoolean()) {
                                } else {
                                    JsonObject travelJson = resJson.getAsJsonObject("data");
                                    stepTravelID = travelJson.get("insertId").getAsInt();
                                    for (Restaurant rb : restaurants) {
                                        RestaurantBookingGetting restaurantBooking = new RestaurantBookingGetting(stepTravelID, rb.getID(), convertDate(new Date()));
                                        RestaurantBookingService restaurantBookingService = RetrofitInstance.getRetrofitInstance().create(RestaurantBookingService.class);
                                        retrofit2.Call<Object> call3 = restaurantBookingService.addRestaurantBooking(restaurantBooking);
                                        call3.enqueue(new Callback<Object>() {
                                            @Override
                                            public void onResponse(retrofit2.Call<Object> call, Response<Object> response) {
                                                JsonParser parser = new JsonParser();
                                                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();

                                            }

                                            @Override
                                            public void onFailure(retrofit2.Call<Object> call, Throwable t) {

                                            }
                                        });
                                    }

                                    Intent intent = new Intent(getApplicationContext(), ListAllStepForTravel.class);

                                    System.out.println(">>>>Create travel" + insertKey);
                                    intent.putExtra("insertKey", insertKey);
                                    startActivity(intent);
                                    finish();


                                }
                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {

                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {

                }
            });
        }else {
            stepTravel = new StepTravel1(insertKey, fromCity, toCity, transportation_id, hotelID, 1, convertDate(new Date()), convertDate(new Date()));
            TravelStepService travelStepService = RetrofitInstance.getRetrofitInstance().create(TravelStepService.class);
            Call<Object> call2 = travelStepService.addStepTravel(stepTravel);
            call2.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    JsonParser parser = new JsonParser();
                    JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
//                System.out.println("......" + resJson.get("message").getAsString());
                    if (resJson.get("isError").getAsBoolean()) {
                    } else {
                        JsonObject travelJson = resJson.getAsJsonObject("data");
                        stepTravelID = travelJson.get("insertId").getAsInt();
                        for (Restaurant rb : restaurants) {
                            RestaurantBookingGetting restaurantBooking = new RestaurantBookingGetting(stepTravelID, rb.getID(), convertDate(new Date()));
                            RestaurantBookingService restaurantBookingService = RetrofitInstance.getRetrofitInstance().create(RestaurantBookingService.class);
                            retrofit2.Call<Object> call3 = restaurantBookingService.addRestaurantBooking(restaurantBooking);
                            call3.enqueue(new Callback<Object>() {
                                @Override
                                public void onResponse(retrofit2.Call<Object> call, Response<Object> response) {
                                    JsonParser parser = new JsonParser();
                                    JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();

                                }

                                @Override
                                public void onFailure(retrofit2.Call<Object> call, Throwable t) {

                                }
                            });
                        }

                        Intent intent = new Intent(getApplicationContext(), ListAllStepForTravel.class);

                        System.out.println(">>>>Create travel" + insertKey);
                        intent.putExtra("insertKey", insertKey);
                        startActivity(intent);
                        finish();


                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {

                }
            });
        }



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
