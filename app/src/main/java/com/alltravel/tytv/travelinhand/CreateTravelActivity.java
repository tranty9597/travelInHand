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
import com.alltravel.tytv.travelinhand.model.base.StepTravel;
import com.alltravel.tytv.travelinhand.model.base.Transportation;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.model.base.TravelStep;
import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.singleton.UserInstance;

import java.util.ArrayList;
import java.util.Date;

public class CreateTravelActivity extends AppCompatActivity implements TransportationFragment.OnFragmentInteractionListener, HotelFragment.OnFragmentInteractionListener, RestaurantFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CreateTravelViewPagerAdapter adapter;
    private String fromLocation;
    private String toLocation;
    private Button btnCreateNewStep;
    private String transportation_id;
    private String restaurantID;
    private String hotelID;
    private Travel travel;

    public void setTransportation(String transportation_id) {
        this.transportation_id = transportation_id;
    }

    public void setRestaurant(String restaurantID) {
        this.restaurantID = restaurantID;
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
        if(intent != null){
            this.fromLocation = intent.getStringExtra("FromLocation");
            this.toLocation = intent.getStringExtra("ToLocation");
        }
        this.tabLayout = findViewById(R.id.tabLayout);
        this.viewPager = findViewById(R.id.viewPager);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.transportation_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.hotel_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.restaurant_icon);
        this.btnCreateNewStep = findViewById(R.id.btnCreateNewStep);
        User user=UserInstance.getUserInstance();
        travel = new Travel(user.getUsername(),new Date().toString(),"New Travel of"+user.getFullName(),"",1);
    }

    private void setupViewPager(){
        adapter = new CreateTravelViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TransportationFragment(), "");
        adapter.addFragment(new HotelFragment(), "");
        adapter.addFragment(new RestaurantFragment(), "");
        viewPager.setAdapter(adapter);
    }

    public void createNewStep(View v){
        Toast.makeText(this, "Transporation ID: " + transportation_id+"\n Restaurant:"+restaurantID+"\nHotel:"+hotelID, Toast.LENGTH_SHORT).show();
        StepTravel stepTravel = new StepTravel(travel.getID(),fromLocation,toLocation,Integer.parseInt(transportation_id),hotelID,restaurantID,new Date().toString(),new Date().toString());
        Intent intent = new Intent();
        intent.putExtra("travel",travel);
        intent.putExtra("stepTravel",stepTravel);
        setResult(200, intent);
        finish();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
