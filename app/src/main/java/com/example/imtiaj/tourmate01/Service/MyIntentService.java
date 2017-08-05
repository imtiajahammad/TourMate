package com.example.imtiaj.tourmate01.Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;

import com.example.imtiaj.tourmate01.Activity.EventListActivity;
import com.example.imtiaj.tourmate01.Database.CostManager;
import com.example.imtiaj.tourmate01.Database.EventManager;
import com.example.imtiaj.tourmate01.Model.CostModel;
import com.example.imtiaj.tourmate01.Model.TourEvent;
import com.example.imtiaj.tourmate01.R;

import java.util.ArrayList;

import static android.R.attr.name;

public class MyIntentService extends IntentService {

    EventManager eventManager;
    ArrayList<TourEvent> tourEvents;

    CostManager costManager;
    ArrayList<CostModel>costModels;




    Handler handler;


    public MyIntentService() {
        super("MyIntentService");
        handler=new Handler(Looper.getMainLooper());
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        handler.post(new Runnable() {
            @Override
            public void run() {

                eventManager=new EventManager(MyIntentService.this);
                tourEvents=eventManager.getAllEvents();
                for (TourEvent atour:tourEvents) {
                    String tourName=atour.getDestination();
                    double tourBudget=atour.getEventBudget();
                    costManager=new CostManager(MyIntentService.this);
                    costModels=costManager.getAllCosts(tourName);

                    double calculationTotal=0;
                    for (CostModel acostModel:costModels) {

                        calculationTotal=calculationTotal+acostModel.getCostAmount();
                    }
                    double seventyFivePercentage=tourBudget*(75/100);
                    double hundredPercentage=tourBudget;

                    if(calculationTotal>=seventyFivePercentage)
                    {
                        NotificationCompat.Builder builder =
                                new NotificationCompat.Builder(MyIntentService.this)
                                        .setSmallIcon(R.mipmap.ic_launcher)

                                        .setContentTitle("Tk 75% expended")
                                        .setContentText(tourName +"event has gone over 75% on expense");

                        Intent notificationIntent = new Intent(MyIntentService.this, EventListActivity.class);
                        PendingIntent contentIntent = PendingIntent.getActivity(MyIntentService.this, 0, notificationIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(contentIntent);

                        // Add as notification
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(0, builder.build());
                    }

                    if(calculationTotal>=hundredPercentage)
                    {
                        NotificationCompat.Builder builder =
                                new NotificationCompat.Builder(MyIntentService.this)
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("Tk 100% expended")
                                        .setContentText(tourName +"event has gone over 100% on expense");

                        Intent notificationIntent = new Intent(MyIntentService.this, EventListActivity.class);
                        PendingIntent contentIntent = PendingIntent.getActivity(MyIntentService.this, 0, notificationIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(contentIntent);

                        // Add as notification
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(0, builder.build());
                    }
                }






                //notification code


            }
        });

    }
}
