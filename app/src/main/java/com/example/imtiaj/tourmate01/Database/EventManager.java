package com.example.imtiaj.tourmate01.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.imtiaj.tourmate01.Model.TourEvent;

import java.util.ArrayList;

/**
 * Created by imtiaj on 2/8/2017.
 */

public class EventManager {

    private DatabaseHelper databaseHelper;
    private Context context;

    TourEvent tourEvent;

    public EventManager(Context context) {
        this.context = context;
        databaseHelper=new DatabaseHelper(context);

    }


    public long addEvent(TourEvent tourEvent) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN__EVENT_DESTINATION, tourEvent.getDestination());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_BUDGET, tourEvent.getEventBudget());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_FROMTIME, tourEvent.getFromDate());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_TOTIME, tourEvent.getToDate());

        long insertRow = sqLiteDatabase.insert(DatabaseHelper.EVENT_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return insertRow;
    }


    public ArrayList<TourEvent> getAllEvents() {

        ArrayList<TourEvent> tours = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "select * from " + DatabaseHelper.EVENT_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_ID));
                String destination = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN__EVENT_DESTINATION));
                String fromTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_FROMTIME));
                String toTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_TOTIME));
                double budget= cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_BUDGET));
                TourEvent tourEvent= new TourEvent(id,destination,fromTime,toTime,budget);
                tours.add(tourEvent);
            } while (cursor.moveToNext());

        }
        return tours;

    }


    public void deleteEvent(int id){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        sqLiteDatabase.delete(DatabaseHelper.EVENT_TABLE,DatabaseHelper.COLUMN_EVENT_ID+" =?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();

    }



    public long updateEvent(TourEvent tourEvent,int id) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN__EVENT_DESTINATION, tourEvent.getDestination());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_BUDGET, tourEvent.getEventBudget());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_FROMTIME, tourEvent.getFromDate());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_TOTIME, tourEvent.getToDate());

        long insertedRow = sqLiteDatabase.update(DatabaseHelper.EVENT_TABLE, contentValues, DatabaseHelper.COLUMN_EVENT_ID + " =? ", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return insertedRow;


    }


    public TourEvent getEventsById(int id) {


        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "select * from " + DatabaseHelper.EVENT_TABLE + " where " + DatabaseHelper.COLUMN_EVENT_ID + " = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            String eventName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN__EVENT_DESTINATION));
            double eventBudget = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_BUDGET));
            String eventFrom = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_FROMTIME));
            String eventTo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_TOTIME));
            tourEvent = new TourEvent(eventName, eventFrom, eventTo,eventBudget);

        }


        return tourEvent;

    }


}
