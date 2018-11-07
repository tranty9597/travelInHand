package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.alltravel.tytv.travelinhand.adapters.NewTravelAdapter;
import com.alltravel.tytv.travelinhand.model.base.StepTravel;
import com.alltravel.tytv.travelinhand.model.base.Travel;

import java.util.ArrayList;

public class ListAllStepForTravel extends AppCompatActivity {

    ArrayList<StepTravel> stepTravels;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_step_for_travel);
        ImageView imageView = findViewById(R.id.addImage);
        listView = findViewById(R.id.createTravelList);
        stepTravels = new ArrayList<>();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateTravelLocation.class);
                startActivityForResult(intent,200);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == resultCode && resultCode == 200) {
            Travel travel = (Travel) data.getSerializableExtra("travel");
            StepTravel stepTravel = (StepTravel) data.getSerializableExtra("stepTravel");
            stepTravels.add(stepTravel);
            NewTravelAdapter newTravelAdapter = new NewTravelAdapter(stepTravels,this);
//            Adapter adapter = new Adapter(this, students);
            listView.setAdapter(newTravelAdapter);
        }
    }
}
