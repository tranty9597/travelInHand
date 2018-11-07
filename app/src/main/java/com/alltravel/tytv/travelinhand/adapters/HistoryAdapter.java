package com.alltravel.tytv.travelinhand.adapters;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.HistoryAcivity;
import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.model.base.Travel;
import com.alltravel.tytv.travelinhand.singleton.TravelInstance;

import java.util.ArrayList;

public class HistoryAdapter extends
        RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private HistoryAcivity historyAcivity;
    public HistoryAdapter(HistoryAcivity historyAcivity) {
        this.historyAcivity = historyAcivity;
    }
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
        viewHolder.itemTravelName.setText(lstTravel.get(i).getTravelNm());
        viewHolder.itemDateCreated.setText(lstTravel.get(i).getDateCreated());
        String status = "";
        switch(lstTravel.get(i).getStatus()) {
            case 0:
                status = "About to";
                break;
            case 1:
                status = "On going";
                break;
            case 2:
                status = "Done";
                break;
            case 3:
                status = "Cancle";
                break;
        }
        viewHolder.itemStatus.setText(status);
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
                    Travel travelInstance = lstTravel.get(position);
                    doDisplay(travelInstance);
                }
            });
        }

        public void doDisplay(Travel travel) {
            historyAcivity.doDisplayDetail(travel);
        }
    }
}

