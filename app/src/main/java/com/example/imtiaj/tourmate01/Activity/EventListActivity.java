package com.example.imtiaj.tourmate01.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imtiaj.tourmate01.Adapter.EventListAdapter;
import com.example.imtiaj.tourmate01.Database.EventManager;
import com.example.imtiaj.tourmate01.Model.TourEvent;
import com.example.imtiaj.tourmate01.R;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    ListView eventListView;
    EventListAdapter eventListAdapter;
    EventManager eventManager;
    ArrayList<TourEvent>tourEvents;

//for dialog
private AlertDialog.Builder build;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        eventListView= (ListView) findViewById(R.id.eventsListViewId);
        eventManager=new EventManager(this);
        tourEvents=eventManager.getAllEvents();
        eventListAdapter=new EventListAdapter(this,tourEvents);
        eventListView.setAdapter(eventListAdapter);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(EventListActivity.this,CostListActivity.class);
                String clickedEvent =((TextView)view.findViewById(R.id.eventNameTV)).getText().toString();
                intent.putExtra("clickedEvent",clickedEvent);
             //   String iu=tourEvents.get(i).getDestination().toString();
                startActivity(intent);

            }
        });
        eventListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                build = new AlertDialog.Builder(EventListActivity.this);
                build.setTitle("Apply Change " + tourEvents.get(position).getDestination().toString());
                build.setMessage("Do you want to update/delete the record?(Hit back to cancel)");

                build.setNegativeButton("UPDATE",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //Update record selected
                                Intent ir = new Intent(EventListActivity.this,AddEventActivity.class);
                                ir.putExtra("eventId", tourEvents.get(position).getEventId());
                                startActivity(ir);
                                dialog.cancel();
                            }
                        });



                //user select DELETE
                build.setPositiveButton("DELETE",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,int which) {

                                eventListView= (ListView) findViewById(R.id.eventsListViewId);
                                eventManager=new EventManager(EventListActivity.this);
                                tourEvents=eventManager.getAllEvents();
                                eventListAdapter=new EventListAdapter(EventListActivity.this,tourEvents);
                                int id=tourEvents.get(position).getEventId();
                                eventManager.deleteEvent(tourEvents.get(position).getEventId());


                           /*     dataBase.delete(
                                        DBHelper.TABLE_NAME,
                                        DBHelper.STAFID + "="
                                                + stafid.get(arg2), null);*/
                                Toast.makeText(
                                        getApplicationContext(),
                                        tourEvents.get(position).getDestination().toString()+
                                                " is deleted.", Toast.LENGTH_SHORT).show();

                               // eventListView.setAdapter(eventListAdapter);
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


        /*
        for dialog

          //long-press to update data
        userList.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    final int arg2, long arg3) {

                //invoking AlertDialog box
                build = new AlertDialog.Builder(DisplayList.this);
                build.setTitle("Update/Delete staff " + nama.get(arg2));
                build.setMessage("Do you want to update/delete the record?(Hit back to cancel)");

                //user select UPDATE
                build.setNegativeButton("UPDATE",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog,
                                int which) {
                            //Update record selected
                            Intent i = new Intent(getApplicationContext(),
                                    UpdateRecord.class);
                            i.putExtra("stafid", stafid.get(arg2));
                            startActivity(i);
                            dialog.cancel();
                        }
                    });//end UPDATE

                //user select DELETE
                build.setPositiveButton("DELETE",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,int which) {
                                dataBase.delete(
                                        DBHelper.TABLE_NAME,
                                        DBHelper.STAFID + "="
                                                + stafid.get(arg2), null);
                                Toast.makeText(
                                        getApplicationContext(),
                                        nama.get(arg2)+
                                        " is deleted.", Toast.LENGTH_SHORT).show();
                                displayData();
                                dialog.cancel();
                            }
                        });//end DELETE
                AlertDialog alert = build.create();
                alert.show();

                return true;
            }
        });//end setOnItemLongClickListener

        * */


        /*
        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(CategoryListActivity.this,ProductListActivity.class);
                //intent.putExtra("category",products.get(position).getProductCategory().toString());
                String selected =((TextView)view.findViewById(R.id.singleCategoryRow)).getText().toString();
                intent.putExtra("category",selected);
                startActivity(intent);
                //TextView textview =((TextView)view.findViewById(R.id.tvInVisitorName)).getText().toString();
                // String selected =((TextView)view.findViewById(R.id.your_textView_item_id)).getText().toString();

            }
        });*/
    }

    public void addEventFunction(View view) {
        Intent inten=new Intent(this,AddEventActivity.class);
        startActivity(inten);
    }


}
