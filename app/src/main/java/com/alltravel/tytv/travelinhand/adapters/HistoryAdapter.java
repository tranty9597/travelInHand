package com.alltravel.tytv.travelinhand.adapters;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.services.TravelService;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.singleton.UserInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryAdapter extends
        RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


    ArrayList<Travel> lstTravel;
    public void setLstTravel(ArrayList<Travel> lstTravel) {
        this.lstTravel = lstTravel;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())

                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemTravelName.setText(lstTravel.get(i).getTravelNm() + "-" + lstTravel.get(i).getTravelDes());
        viewHolder.itemDateCreated.setText(lstTravel.get(i).getDateCreated());
        viewHolder.itemStatus.setText(lstTravel.get(i).getStatus() + "");

    }

    @Override
    public int getItemCount() {
        return lstTravel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemImage;
        public TextView itemTravelName;
        public TextView itemDateCreated;
        public TextView itemStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTravelName =
                    (TextView)itemView.findViewById(R.id.item_Travel_name);
            itemDateCreated =
                    (TextView)itemView.findViewById(R.id.item_travel_date_created);
            itemStatus =
                    (TextView)itemView.findViewById(R.id.item_status);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    Snackbar.make(v, "Click detected on item " + position,

                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }
    }

