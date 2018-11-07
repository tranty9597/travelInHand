package com.alltravel.tytv.travelinhand.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.model.base.RestaurantBooking;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.utils.DownloadImageTask;

import java.util.ArrayList;

public class RestaurantBookingAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<RestaurantBooking> restaurantBookings;

    public RestaurantBookingAdapter(Activity activity, ArrayList<RestaurantBooking> restaurantBookings) {
        this.activity = activity;
        this.restaurantBookings = restaurantBookings;
    }

    @Override
    public int getCount() {
        return restaurantBookings.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantBookings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ResHolder resHolder;
        RestaurantBooking booking = restaurantBookings.get(position);
        if(convertView == null){
            resHolder = new ResHolder();
            convertView = activity.getLayoutInflater().inflate(R.layout.restaurant_booking, null);

            resHolder.restaurantImg = convertView.findViewById(R.id.resatarantImg);
            resHolder.restaurantName = convertView.findViewById(R.id.restaurantName);
            resHolder.bookingTimeTxt = convertView.findViewById(R.id.bkTime);

            convertView.setTag(resHolder);
        }else {
            resHolder = (ResHolder) convertView.getTag();
        }

        new DownloadImageTask(resHolder.restaurantImg).execute(RetrofitInstance.BASE_IMG + booking.getRestaurantID());
        resHolder.restaurantName.setText(booking.getRestaurantNm());

        resHolder.bookingTimeTxt.setText(booking.getBookingTime());
        return  convertView;
    }
}

class ResHolder{
    TextView restaurantName, bookingTimeTxt;
    ImageView restaurantImg;

}
