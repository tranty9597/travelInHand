package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CreateTravelLocation extends AppCompatActivity {

    private TextView txtDeparture;
    private TextView txtDestination;
    private String departure = "";
    private String destination = "";
    private int insertKey=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        insertKey = intent.getIntExtra("insertKey",-1);
        setContentView(R.layout.activity_create_travel_location);
        this.txtDeparture = findViewById(R.id.txtDeparture);
        this.txtDestination = findViewById(R.id.txtDestination);
    }

    public void nextHandle(View v){
        departure = txtDeparture.getText().toString();
        destination = txtDestination.getText().toString();
        if(this.destination.equals("") || this.departure.equals("")){
            Toast.makeText(getApplicationContext(), "Enter all blank", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, CreateTravelActivity.class);
            intent.putExtra("FromLocation", this.departure);
            intent.putExtra("ToLocation", this.destination);
            System.out.println(">>>>Add Location"+insertKey);
            intent.putExtra("insertKey",insertKey);
            startActivity(intent);
            finish();
        }
    }
}
