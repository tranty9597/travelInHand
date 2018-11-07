package com.alltravel.tytv.travelinhand.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.ListAllStepForTravel;
import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.model.base.StepTravel;

import java.util.ArrayList;

public class NewTravelAdapter extends BaseAdapter {

    private ArrayList<StepTravel> stepTravels;
    private ListAllStepForTravel listAllStepForTravel;

    public NewTravelAdapter(ArrayList<StepTravel> stepTravels, ListAllStepForTravel listAllStepForTravel) {
        this.stepTravels = stepTravels;
        this.listAllStepForTravel = listAllStepForTravel;
    }

    @Override
    public int getCount() {
        return stepTravels.size();
    }

    @Override
    public Object getItem(int position) {
        return stepTravels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = listAllStepForTravel.getLayoutInflater().inflate(R.layout.new_travel_layout, null);
            holder = new Holder();
            holder.textView = convertView.findViewById(R.id.textView14);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        StepTravel stepTravel=stepTravels.get(position);
        holder.textView.setText("From: "+stepTravel.getFromCity()+" \nTo: "+stepTravel.getToCity());
        return convertView;
    }

    class Holder {
        TextView textView;
    }
}
