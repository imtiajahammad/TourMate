package com.example.imtiaj.tourmate01.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.imtiaj.tourmate01.Adapter.CostListAdapter;
import com.example.imtiaj.tourmate01.Database.CostManager;
import com.example.imtiaj.tourmate01.Model.CostModel;
import com.example.imtiaj.tourmate01.R;

import java.util.ArrayList;

public class CostListActivity extends AppCompatActivity {

    String clickedEvent;

    ListView costListView;
    CostListAdapter costListAdapter;
    CostManager costManager;
    ArrayList<CostModel>costModels;

    private AlertDialog.Builder build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_list);

        costListView= (ListView) findViewById(R.id.costsListViewId);
        costManager=new CostManager(this);

        clickedEvent=getIntent().getStringExtra("clickedEvent");

        costModels=costManager.getAllCosts(clickedEvent);
        costListAdapter=new CostListAdapter(this,costModels);
        costListView.setAdapter(costListAdapter);


        costListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                build = new AlertDialog.Builder(CostListActivity.this);
                build.setTitle("Apply Change " + costModels.get(position).getCostDetails().toString());
                build.setMessage("Do you want to update/delete the record?(Hit back to cancel)");

                build.setNegativeButton("UPDATE",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //Update record selected
                                Intent ir = new Intent(CostListActivity.this,AddCostActivity.class);
                                ir.putExtra("costId", costModels.get(position).getCostId());
                                startActivity(ir);
                                dialog.cancel();
                            }
                        });



                //user select DELETE
                build.setPositiveButton("DELETE",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,int which) {

                                costListView= (ListView) findViewById(R.id.costsListViewId);
                                costManager=new CostManager(CostListActivity.this);
                                costModels=costManager.getAllCosts(clickedEvent);
                                costListAdapter=new CostListAdapter(CostListActivity.this,costModels);
                                int id=costModels.get(position).getCostId();
                                costManager.deleteCost(costModels.get(position).getCostId());






                           /*     dataBase.delete(
                                        DBHelper.TABLE_NAME,
                                        DBHelper.STAFID + "="
                                                + stafid.get(arg2), null);*/
                                Toast.makeText(
                                        getApplicationContext(),
                                        costModels.get(position).getCostDetails().toString()+
                                                " is deleted.", Toast.LENGTH_SHORT).show();

                                // eventListView.setAdapter(eventListAdapter);
                              /*  Intent intent = new Intent(CostListActivity.this, CostListActivity.class);
                                intent.putExtra("clickedEvent",clickedEvent);
                                startActivity(intent);*/

                                finish();
                                startActivity(getIntent());
                                dialog.cancel();
                            }
                        });//end DELETE
                AlertDialog alert = build.create();
                alert.show();

                return true;
            }
        });





    }


    public void addCostFunction(View view) {

        Intent inten=new Intent(this,AddCostActivity.class);
        inten.putExtra("clickedEvent",clickedEvent);
        startActivity(inten);


    }
}
/*public class EventListActivity extends AppCompatActivity {

    ListView eventListView;
    EventListAdapter eventListAdapter;
    EventManager eventManager;
    ArrayList<TourEvent>tourEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        eventListView= (ListView) findViewById(R.id.eventsListViewId);
        eventManager=new EventManager(this);
        tourEvents=eventManager.getAllEvents();
        eventListAdapter=new EventListAdapter(this,tourEvents);
        eventListView.setAdapter(eventListAdapter);
    }

    public void addEventFunction(View view) {
        Intent inten=new Intent(this,AddEventActivity.class);
        startActivity(inten);
    }


}
*/