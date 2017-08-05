package com.example.imtiaj.tourmate01.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imtiaj.tourmate01.Database.EventManager;
import com.example.imtiaj.tourmate01.Model.TourEvent;
import com.example.imtiaj.tourmate01.R;
import com.example.imtiaj.tourmate01.Service.MyIntentService;

public class AddEventActivity extends AppCompatActivity {

    private EditText destinationEdit;
    private EditText budgetEdit;
    private EditText fromdateEdit;
    private EditText todateEdit;

    private Button eventCrtBTN;

    EventManager eventManager;

    int eventId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        destinationEdit = (EditText) findViewById(R.id.destinationET);
        budgetEdit = (EditText) findViewById(R.id.budgetET);
        fromdateEdit = (EditText) findViewById(R.id.fromdateET);
        todateEdit = (EditText) findViewById(R.id.todateET);
        eventCrtBTN = (Button) findViewById(R.id.eventCreateBTN);


        eventManager = new EventManager(this);


        eventId = getIntent().getIntExtra("eventId", 0);
        if (eventId > 0) {
            TourEvent tourEvent = eventManager.getEventsById(eventId);
            eventCrtBTN.setText("Update");
            budgetEdit.setText(String.valueOf(tourEvent.getEventBudget()));
            fromdateEdit.setText(tourEvent.getFromDate());
            todateEdit.setText(tourEvent.getToDate());
            destinationEdit.setText(tourEvent.getDestination());


        }
    }

    public void createEventFunction(View view) {


        TourEvent tourEvent= new
                TourEvent(destinationEdit.getText().toString(),
                fromdateEdit.getText().toString(),todateEdit.getText().toString(),
                Double.parseDouble(budgetEdit.getText().toString()));


        if (eventId > 0) {
            long insertResult = eventManager.updateEvent(tourEvent,eventId);
            if (insertResult > 0) {

                Toast.makeText(this, "UpdatedSuccess" + insertResult, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, EventListActivity.class);
                startActivity(intent);

            }
        }
        else {

            long insertResult = eventManager.addEvent(tourEvent);
            if (insertResult > 0) {

                Toast.makeText(this, "AddSuccess" + insertResult, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, EventListActivity.class);
                startActivity(intent);
            }



        }


        startService(new Intent(this,MyIntentService.class));



    }
}
/*        if (idget > 0) {
            long insertResult = productManager.updateProduct(product);
            if (insertResult > 0) {

                Toast.makeText(this, "UpdatedSuccess" + insertResult, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CategoryListActivity.class);
                startActivity(intent);

            }
        }
            else {

                long insertResult = productManager.addProduct(product);
                if (insertResult > 0) {

                    Toast.makeText(this, "AddSuccess" + insertResult, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CategoryListActivity.class);
                    startActivity(intent);
                }
            }*/