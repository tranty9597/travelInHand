package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.model.base.Transportation;

import org.w3c.dom.Text;

public class DetailTransportation extends AppCompatActivity {

    private TextView txtName;
    private TextView txtPhone;
    private TextView txtOpenTime;
    private TextView txtTimeDistance;
    private TextView txtPrice;
    private TextView txtDescription;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transportation);
        Intent intent = getIntent();
        Transportation transportation = (Transportation) intent.getSerializableExtra("Transport");
        this.txtName = findViewById(R.id.txtTransName);
        this.txtPhone = findViewById(R.id.txtPhone);
        this.txtOpenTime = findViewById(R.id.txtOpenTime);
        this.txtTimeDistance = findViewById(R.id.txtTimeDistance);
        this.txtPrice = findViewById(R.id.txtPrice);
        this.txtDescription = findViewById(R.id.txtDescription);
//        imageView.setImageResource(R.drawable.transportation_icon);
        txtName.setText(transportation.getTransportation_name());
        txtPhone.setText(transportation.getPhone());
        txtOpenTime.setText("Open time: " + transportation.getOpen_time());
        txtTimeDistance.setText(String.valueOf(transportation.getTime_distance()) + " minutes");
        if(transportation.getPrice() != 0){
            txtPrice.setText(String.valueOf(transportation.getPrice()) + " dong");
        }
        if(transportation.getDescription() != null && !transportation.getDescription().equals("")){
            txtDescription.setText(transportation.getDescription());
        }
    }
}
