package com.alltravel.tytv.travelinhand.adapters;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.CreateTravelActivity;
import com.alltravel.tytv.travelinhand.DetailHotel;
import com.alltravel.tytv.travelinhand.Fragment.RestaurantFragment;
import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.model.base.Hotel;
import com.alltravel.tytv.travelinhand.model.base.Restaurant;

import java.util.ArrayList;

public class AddTravelRestaurantAdapter extends BaseAdapter {

    private RestaurantFragment restaurantFragmentFrag;
    private ArrayList<Restaurant> list;
    private CreateTravelActivity activity;

    public AddTravelRestaurantAdapter(RestaurantFragment hotelFrag,CreateTravelActivity activity , ArrayList<Restaurant> list) {
        this.restaurantFragmentFrag = hotelFrag;
        this.list = list;
        this.activity =activity;
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
        AddTravelRestaurantAdapter.Holder holder = null;
        if (convertView == null) {
            convertView = restaurantFragmentFrag.getLayoutInflater().inflate(R.layout.list_restaurant, null);
            holder = new AddTravelRestaurantAdapter.Holder();
            holder.textView = convertView.findViewById(R.id.textView22);
            holder.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        }else{
            holder = (AddTravelRestaurantAdapter.Holder) convertView.getTag();
        }
        holder.checkBox.setTag(list.get(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Restaurant restaurant1 =(Restaurant) buttonView.getTag();
                if(isChecked){
                    activity.setRestaurant(restaurant1);
                }
                else{
                    activity.removeRestaurant(restaurant1);
                }
            }
        });
        Restaurant restaurant = list.get(position);
        holder.textView.setText(restaurant.getRestaurantNm()+"\n"+restaurant.getPhone());
        holder.textView.setTag(restaurant);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurant restaurant1 =(Restaurant) v.getTag();
                Intent intent = new Intent(restaurantFragmentFrag.getContext(), DetailHotel.class);
                intent.putExtra("object",restaurant1);
                intent.putExtra("class","Restaurant");
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    class Holder {
        TextView textView;
        CheckBox checkBox;
    }
}
