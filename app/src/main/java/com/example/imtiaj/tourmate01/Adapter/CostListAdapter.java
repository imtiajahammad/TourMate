package com.example.imtiaj.tourmate01.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.imtiaj.tourmate01.Model.CostModel;
import com.example.imtiaj.tourmate01.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imtiaj on 2/10/2017.
 */

public class CostListAdapter extends ArrayAdapter<CostModel> {

    private Context context;
    ArrayList<CostModel>costModels;

    public CostListAdapter(Context context,ArrayList<CostModel> costModels) {
        super(context, R.layout.costlist_row, costModels);
        this.costModels=costModels;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CostModel costModel=costModels.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.costlist_row,parent,false);
        TextView date= (TextView) convertView.findViewById(R.id.costDateTV);
        TextView time= (TextView) convertView.findViewById(R.id.costTimeTV);
        TextView description= (TextView) convertView.findViewById(R.id.costDescriptionTV);
        TextView budget= (TextView) convertView.findViewById(R.id.costamountTV);

        date.setText(costModel.getDate());
        time.setText(costModel.getTime());
        description.setText(costModel.getCostDetails());
        budget.setText(String.valueOf(costModel.getCostAmount()));


        return convertView;
    }
}

/*
public class EventListAdapter extends ArrayAdapter<TourEvent> {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TourEvent tourEvent=tourEvents.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.eventlist_row,parent,false);

        TextView eventName= (TextView) convertView.findViewById(R.id.eventNameTV);
        TextView fromDate= (TextView) convertView.findViewById(R.id.fromDateTV);
        TextView toDate= (TextView) convertView.findViewById(R.id.toDateTV);
        TextView eventBudget= (TextView) convertView.findViewById(R.id.eventBudgetTV);


        eventName.setText(tourEvent.getDestination());
        fromDate.setText(tourEvent.getFromDate());
        toDate.setText(tourEvent.getToDate());
        eventBudget.setText(String.valueOf(tourEvent.getEventBudget()));

        return convertView;


    }
}
*/