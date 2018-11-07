package com.alltravel.tytv.travelinhand.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.CreateTravelActivity;
import com.alltravel.tytv.travelinhand.DetailHotel;
import com.alltravel.tytv.travelinhand.Fragment.HotelFragment;
import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.model.base.Hotel;
import com.alltravel.tytv.travelinhand.model.base.Restaurant;

import java.util.ArrayList;

public class AddTravelHotelAdapter extends BaseAdapter {

    private HotelFragment hotelFrag;
    private ArrayList<Hotel> list;
    private CreateTravelActivity activity;

    public AddTravelHotelAdapter(HotelFragment hotelFrag, CreateTravelActivity activity, ArrayList<Hotel> list) {
        this.hotelFrag = hotelFrag;
        this.list = list;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = hotelFrag.getLayoutInflater().inflate(R.layout.list_hotel, null);
            holder = new Holder();
            holder.textView = convertView.findViewById(R.id.textView2);
            holder.radioButton = convertView.findViewById(R.id.radioButton);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        Hotel hotel = list.get(position);
        holder.radioButton.setTag(list.get(position));
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hotel hotel1 =(Hotel) v.getTag();
                activity.setHotel(hotel1.getID());
            }
        });
        holder.textView.setText(hotel.getHotelNm()+"\n"+hotel.getPhone());
        holder.textView.setTag(hotel);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.parseColor("#cecece"));
                Hotel hotel1 =(Hotel) v.getTag();
                Intent intent = new Intent(hotelFrag.getContext(), DetailHotel.class);
                intent.putExtra("object",hotel1);
                intent.putExtra("class","Hotel");
                v.getContext().startActivity(intent);
                v.setBackgroundColor(Color.WHITE);
            }
        });

        return convertView;
    }

    class Holder {
        TextView textView;
        RadioButton radioButton;

    }
}
