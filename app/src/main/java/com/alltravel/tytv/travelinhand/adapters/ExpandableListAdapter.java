package com.alltravel.tytv.travelinhand.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.R;
import com.alltravel.tytv.travelinhand.model.base.TravelStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listData= new ArrayList<>();
    private HashMap<String,List<String>>  listHashMap = new HashMap<>();

    public ExpandableListAdapter(Context context, List<String> listData, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listData = listData;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listData.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {

        return listData.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listData.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return  childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        String header = (String) getGroup(groupPosition);
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_historydetail,null);
        }
        TextView lbListHeader = (TextView) view.findViewById(R.id.listHeaderFromCity);
        lbListHeader.setTypeface(null,Typeface.BOLD);
        lbListHeader.setText(header);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        final  String childText = (String)getChild(groupPosition, childPosition);
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_history,null);
        }
        TextView lbFromCity = (TextView) view.findViewById(R.id.valCity);
        lbFromCity.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
