package com.alltravel.tytv.travelinhand.adapters;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.CreateTravelActivity;
import com.alltravel.tytv.travelinhand.DetailTransportation;
import com.alltravel.tytv.travelinhand.Fragment.TransportationFragment;
import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.model.base.Transportation;

import java.util.ArrayList;

public class TransportationListAdapter extends BaseAdapter {

    private TransportationFragment transportationFragment;
    private ArrayList<Transportation> listTrans;
    private CreateTravelActivity activity;
    private RadioButton selected = null;
    private MyHolder myholder = null;
    public TransportationListAdapter(CreateTravelActivity activity, TransportationFragment transportationFragment, ArrayList<Transportation> listTrans) {
        this.transportationFragment = transportationFragment;
        this.listTrans = listTrans;
        this.activity = activity;
    }

    int selectedPosition = 0;

    @Override
    public int getCount() {
        return listTrans.size();
    }

    @Override
    public Object getItem(int position) {
        return listTrans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = transportationFragment.getLayoutInflater().inflate(R.layout.listviewtransportation, null);
            myholder = new MyHolder();
            myholder.txtTransName = convertView.findViewById(R.id.txtTransName);
            myholder.txtTransOpenTime = convertView.findViewById(R.id.txtTransOpenTime);
            myholder.rdBtnCheck = convertView.findViewById(R.id.rdBtnCheck);
            convertView.setTag(myholder);
        } else {
            myholder = (MyHolder) convertView.getTag();
        }
        myholder.txtTransName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Detail Screen
                Intent intent = new Intent(activity, DetailTransportation.class);
                intent.putExtra("Transport", listTrans.get(position));
                System.out.println("==============");
                System.out.println(listTrans.get(position).getOpen_time());
                System.out.println(listTrans.get(position).getPhone());
                System.out.println(listTrans.get(position).getTime_distance());
                System.out.println(listTrans.get(position).getTransportation_name());
                activity.startActivity(intent);
            }
        });
        myholder.txtTransName.setText(listTrans.get(position).getTransportation_name());
        myholder.txtTransOpenTime.setText(listTrans.get(position).getOpen_time());
        System.out.println("==============");
        System.out.println(listTrans.get(position).getId());
        myholder.rdBtnCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                activity.setTransportation(listTrans.get(position).getId());
            }
        });
//        myholder.rdBtnCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.setTransportation(listTrans.get(position).getId());
//
//            }
//        });
        return convertView;
    }
}

class MyHolder {
    TextView txtTransName;
    TextView txtTransOpenTime;
    RadioButton rdBtnCheck;
}