package com.alltravel.tytv.travelinhand.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.LoginActivity;
import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.StepDetailActivity;
import com.alltravel.tytv.travelinhand.model.base.TravelStep;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.alltravel.tytv.travelinhand.utils.DownloadImageTask;

import java.util.ArrayList;

public class StepAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<TravelStep> travelSteps;

    public StepAdapter(Activity activity, ArrayList<TravelStep> travelSteps) {
        this.activity = activity;
        this.travelSteps = travelSteps;
    }

    @Override
    public int getCount() {
        return travelSteps.size();
    }

    @Override
    public Object getItem(int position) {
        return travelSteps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StepHolder stepHolder;
        final TravelStep step = travelSteps.get(position);
        if(convertView == null){
            stepHolder = new StepHolder();
            convertView = activity.getLayoutInflater().inflate(R.layout.step_item, null);

            stepHolder.stepImg = convertView.findViewById(R.id.stepItemImg);
            stepHolder.stepDesc = convertView.findViewById(R.id.stepDes);
            stepHolder.stepTitle = convertView.findViewById(R.id.stepTitle);

            convertView.setTag(stepHolder);
        }else{
            stepHolder = (StepHolder) convertView.getTag();
        }
        if(step != null){
            new DownloadImageTask(stepHolder.stepImg).execute(RetrofitInstance.BASE_URL + "image/getByID?id=1");
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, StepDetailActivity.class);
                intent.putExtra("stepID", step.getId());

                activity.startActivity(intent);
            }
        });
        stepHolder.stepTitle.setText(step.getFromCity() + " - " + step.getToCity());
        stepHolder.stepDesc.setText(step.getStardDate() + step.getEndDate());
        return convertView;
    }
}
class StepHolder {
    TextView stepTitle;
    TextView stepDesc;
    ImageView stepImg;
}
