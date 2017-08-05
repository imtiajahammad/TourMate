package com.example.imtiaj.tourmate01.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imtiaj.tourmate01.Database.CostManager;
import com.example.imtiaj.tourmate01.Model.CostModel;
import com.example.imtiaj.tourmate01.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddCostActivity extends AppCompatActivity {

    private EditText descriptionEdiT;
    private EditText costAmountEdiT;

    private CostManager costManager;



    int costId;


    Button entryBtn;


    String clickedEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);

        descriptionEdiT= (EditText) findViewById(R.id.descriptionET);
        costAmountEdiT= (EditText) findViewById(R.id.costAmountET);
        entryBtn= (Button) findViewById(R.id.entryCostBtn);


        costManager=new CostManager(this);
        clickedEvent=getIntent().getStringExtra("clickedEvent");


        costId = getIntent().getIntExtra("costId", 0);
        if (costId > 0) {
            CostModel costModel= costManager.getCodtsById(costId);
            entryBtn.setText("Update");
            costAmountEdiT.setText(String.valueOf(costModel.getCostAmount()));
            descriptionEdiT.setText(costModel.getCostDetails());
            clickedEvent=costModel.getEventName();




        }

    }

    public void EntryCostFunction(View view) {

        String monthname = (String) android.text.format.DateFormat.format("MMM", new Date());
        String datename = new SimpleDateFormat("dd").format(new Date());
        String date = datename + " " + monthname;

        String time = new SimpleDateFormat("hh:mm a").format(new Date());

        //String
                //clickedEvent=getIntent().getStringExtra("clickedEvent");

        CostModel costModel = new CostModel(descriptionEdiT.getText().toString(), Double.parseDouble(costAmountEdiT.getText().toString()), date, time,clickedEvent);

        if (costId > 0) {
            long insertResult = costManager.updateCost(costModel,costId);
            if (insertResult > 0) {

              /*  Toast.makeText(this, "UpdatedSuccess" + insertResult, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CostListActivity.class);
                intent.putExtra("clickedEvent",clickedEvent);
                startActivity(intent);*/

                Intent intent=new Intent(this,CostListActivity.class);
                intent.putExtra("clickedEvent",clickedEvent);
                //   String iu=tourEvents.get(i).getDestination().toString();
                startActivity(intent);

            }
        }
        else
        {
            long insertResult = costManager.addCost(costModel);
            if (insertResult > 0)
            {
                Toast.makeText(this, "AddSuccess" + insertResult, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CostListActivity.class);
                intent.putExtra("clickedEvent",clickedEvent);
                startActivity(intent);

            }

        }




    }


}
