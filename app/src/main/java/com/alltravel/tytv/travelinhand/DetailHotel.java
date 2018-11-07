package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.model.base.Hotel;
import com.alltravel.tytv.travelinhand.model.base.Restaurant;

public class DetailHotel extends AppCompatActivity {

    ImageView imageView;
    TextView name;
    TextView number;
    TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        Intent intent = getIntent();
        imageView=findViewById(R.id.imageView);
        name = findViewById(R.id.textView11);
        number = findViewById(R.id.textView12);
        description =findViewById(R.id.textView13);
        String type = intent.getStringExtra("class");
        if(type.equals("Hotel")){
            Hotel hotel = (Hotel) intent.getSerializableExtra("object");
            name.setText(hotel.getHotelNm().toString());
            number.setText(hotel.getPhone().toString());
            description.setText("It is a small, comfortable hotel, situated on the Canale di Cannaregio. \n" +
                    "The Derai family and their staff offer an attentive, personalized service and are always available to offer any help to guests. \n" +
                    "The hotel is arranged on three floors, without a lift. ... \n" +
                    "The rooms are arranged on the first, second and third floors.");
        } else if(type.equals("Restaurant")){
            Restaurant restaurant = (Restaurant) intent.getSerializableExtra("object");
            name.setText(restaurant.getRestaurantNm().toString());
            number.setText(restaurant.getPhone().toString());
            description.setText("We cordially invite you to our restaurant Platan. \n" +
                    "It combines tradition with modernity in an exceptional way.\n" +
                    " Its interior is unique because fragments of old walls have been preserved and they have been ingeniously blended with the modern decor.");
        }

    }
}
