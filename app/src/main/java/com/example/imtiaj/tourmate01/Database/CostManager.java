package com.example.imtiaj.tourmate01.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.imtiaj.tourmate01.Model.CostModel;
import com.example.imtiaj.tourmate01.Model.TourEvent;

import java.util.ArrayList;

/**
 * Created by imtiaj on 2/9/2017.
 */

public class CostManager {

    private DatabaseHelper databaseHelper;
    private Context context;

    CostModel costModel;

    public CostManager(Context context) {
        this.context = context;
        databaseHelper=new DatabaseHelper(context);
    }

    public long addCost(CostModel costModel) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN__COST_DESCRIPTION, costModel.getCostDetails());
        contentValues.put(DatabaseHelper.COLUMN_COST_COSTAMOUNT, costModel.getCostAmount());
        contentValues.put(DatabaseHelper.COLUMN_COST_DATE, costModel.getDate());
        contentValues.put(DatabaseHelper.COLUMN_COST_TIME, costModel.getTime());
        contentValues.put(DatabaseHelper.COLUMN_COST_EVENTNAME, costModel.getEventName());



        long insertRow = sqLiteDatabase.insert(DatabaseHelper.COST_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return insertRow;
    }

    public ArrayList<CostModel> getAllCosts(String event) {
// String selectQuery = "select * from " + DatabaseHelper.PRODUCT_TABLE + " where " + DatabaseHelper.COLUMN_PRODUCT_ID + " = " + id;
        ArrayList<CostModel> costs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "select * from " + DatabaseHelper.COST_TABLE +" where " +DatabaseHelper.COLUMN_COST_EVENTNAME + " ='"+event+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int costId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_ID));
                String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN__COST_DESCRIPTION));
                double costAmount = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_COSTAMOUNT));
                String date= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_TIME));
                String eventName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_EVENTNAME));
                CostModel costModel= new CostModel(costId,description,costAmount,date,time,eventName);
                costs.add(costModel);
            } while (cursor.moveToNext());

        }
        return costs;

    }


    public void deleteCost(int id){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        sqLiteDatabase.delete(DatabaseHelper.COST_TABLE,DatabaseHelper.COLUMN_COST_ID+" =?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();

    }



    public long updateCost(CostModel costModel,int id) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN__COST_DESCRIPTION, costModel.getCostDetails());
        contentValues.put(DatabaseHelper.COLUMN_COST_COSTAMOUNT, costModel.getCostAmount());

        long insertedRow = sqLiteDatabase.update(DatabaseHelper.COST_TABLE, contentValues, DatabaseHelper.COLUMN_COST_ID+ " =? ", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return insertedRow;


    }


    public CostModel getCodtsById(int id) {


        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "select * from " + DatabaseHelper.COST_TABLE+ " where " + DatabaseHelper.COLUMN_COST_ID+ " = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            String costName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN__COST_DESCRIPTION));
            double costAmount= cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_COSTAMOUNT));
            String costEvent= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_EVENTNAME));

            costModel = new CostModel(costName,costAmount,costEvent);

        }


        return costModel;

    }
}
/*private DatabaseHelper databaseHelper;
    private Context context;

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
                TourEvent tourEvent= new TourEvent(destination,fromTime,toTime,budget);
                tours.add(tourEvent);
            } while (cursor.moveToNext());

        }
        return tours;

    }

}
*/