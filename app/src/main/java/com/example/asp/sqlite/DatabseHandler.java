package com.example.asp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asp on 3/15/2018.
 */

public class DatabseHandler extends SQLiteOpenHelper {

    private static final String TAG = "DatabseHandler";

    public DatabseHandler(Context context) {
        super(context, Constants.DATABSE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "onCreate: Table created..");

        //creation of table
        db.execSQL(" CREATE TABLE " + Constants.TABLE_NAME + "( "
                + Constants.KEY_ID + " INTEGER PRIMARY KEY, " + Constants.KEY_NAME + " TEXT,"
                + Constants.KEY_AGE + " TEXT)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //droping means delete
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        //created new databse table
        onCreate(db);

    }

    /*

    CRUD = create,read,update,delete
     */

    /*
    method for setting data into databse
     */
    public void addData(Data data) {

        Log.d(TAG, "addData: added data " + data.getName() + data.getAge());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Constants.KEY_NAME,data.getName());
        value.put(Constants.KEY_AGE,data.getAge());

        db.insert(Constants.TABLE_NAME,null,value);
        db.close();

    }

    /*
        method for getting one data from database
     */
    public Data getOneData(int id) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME,new String[]{
                Constants.KEY_ID,Constants.KEY_NAME,Constants.KEY_AGE
        },Constants.KEY_ID + " =? ",new String[]{String.valueOf(id)},null,null,null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        Data data = new Data(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

        return data;
    }

    /*
     Method for getting all data from databse
     */

    public List<Data> gettingAllData() {

        List<Data> dataList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        do{
            Data data = new Data();
            data.setId(Integer.parseInt(cursor.getString(0)));
            data.setName(cursor.getString(1));
            data.setAge(cursor.getString(2));
            dataList.add(data);
        }while (cursor.moveToNext());

        return dataList;
    }

    public int updateData(Data data) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME,data.getName());
        values.put(Constants.KEY_AGE,data.getAge());

        return db.update(Constants.TABLE_NAME,values,
                Constants.KEY_ID + "=?",new String[]{String.valueOf(data.getId())});
    }

    public void deleteData(int id) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME,Constants.KEY_ID + "=?",new String[]{String.valueOf(id)});
    }

    public int getDatabaseCount() {

        SQLiteDatabase db = getReadableDatabase();
        String selectAll = "SELECT * FROM " + Constants.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll,null);

        return cursor.getCount();
    }

}

