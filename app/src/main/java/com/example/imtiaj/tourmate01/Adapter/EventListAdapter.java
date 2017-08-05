package com.example.imtiaj.tourmate01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.imtiaj.tourmate01.Model.TourEvent;
import com.example.imtiaj.tourmate01.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imtiaj on 2/8/2017.
 */

public class EventListAdapter extends ArrayAdapter<TourEvent> {

    Context context;
    ArrayList<TourEvent>tourEvents;

    public EventListAdapter(Context context, ArrayList<TourEvent> tourEvents) {
        super(context, R.layout.eventlist_row, tourEvents);
        this.tourEvents=tourEvents;
        this.context=context;
    }


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
