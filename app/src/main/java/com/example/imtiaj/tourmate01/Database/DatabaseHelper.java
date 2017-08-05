package com.example.imtiaj.tourmate01.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by imtiaj on 2/8/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="tourMate01_database";
    public static final int DATABASE_VERSION=1;


    public static final String EVENT_TABLE="event_tbl";
    public static final String COLUMN_EVENT_ID="eventId";
    public static final String COLUMN__EVENT_DESTINATION="eventDestination";
    public static final String COLUMN_EVENT_BUDGET="eventBudget";
    public static final String COLUMN_EVENT_FROMTIME="eventFromTime";
    public static final String COLUMN_EVENT_TOTIME="eventToTime";


  //  private final String CREATE_EVENT_TABLE="create table "+EVENT_TABLE+"("+COLUMN_EVENT_ID+" integer primary key autoincrement,"+COLUMN__EVENT_DESTINATION+" text,"+COLUMN_EVENT_FROMTIME+" text,"+COLUMN_EVENT_TOTIME+" test);";
    private final String CREATE_EVENT_TABLE="create table "+EVENT_TABLE+"("+COLUMN_EVENT_ID+" integer primary key autoincrement,"+COLUMN__EVENT_DESTINATION+" text,"+COLUMN_EVENT_FROMTIME+" date,"+COLUMN_EVENT_TOTIME+" date,"+COLUMN_EVENT_BUDGET+" double);";


//cost database

    public static final String COST_TABLE="cost_tbl";
    public static final String COLUMN_COST_ID="costId";
    public static final String COLUMN__COST_DESCRIPTION="costDescription";
    public static final String COLUMN_COST_COSTAMOUNT="costAmount";
    public static final String COLUMN_COST_DATE="costDate";
    public static final String COLUMN_COST_TIME="costTime";
    public static final String COLUMN_COST_EVENTNAME="eventName";


    //  private final String CREATE_EVENT_TABLE="create table "+EVENT_TABLE+"("+COLUMN_EVENT_ID+" integer primary key autoincrement,"+COLUMN__EVENT_DESTINATION+" text,"+COLUMN_EVENT_FROMTIME+" text,"+COLUMN_EVENT_TOTIME+" test);";
    private final String CREATE_COST_TABLE="create table "+COST_TABLE+"("+COLUMN_COST_ID+" integer primary key autoincrement,"+COLUMN__COST_DESCRIPTION+" text,"+COLUMN_COST_COSTAMOUNT+" real,"+COLUMN_COST_TIME+" text,"+COLUMN_COST_DATE+" text,"+COLUMN_COST_EVENTNAME+" text);";
//private final String CREATE_EVENT_TABLE="create table "+EVENT_TABLE+"("+COLUMN_EVENT_ID+" integer primary key autoincrement,"+COLUMN__EVENT_DESTINATION+" text,"+COLUMN_EVENT_FROMTIME+" date,"+COLUMN_EVENT_TOTIME+" date,"+COLUMN_EVENT_BUDGET+" double);";


    //cost database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_EVENT_TABLE);
        sqLiteDatabase.execSQL(CREATE_COST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table "+EVENT_TABLE+" if exists");
        sqLiteDatabase.execSQL("drop table "+COST_TABLE+" if exists");
        onCreate(sqLiteDatabase);

    }
}
