package com.example.asp.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabseHandler db = new DatabseHandler(this);
//
//        Data data = db.getOneData(1);
//        data.setName("Asim");
//        data.setAge("45");

        db.deleteData(1);
        //db.updateData(data);

        //db.addData(new Data("Mustufa","23"));
//        db.addData(new Data("Asim","19"));
//        db.addData(new Data("Sharjeel","20"));
//        db.addData(new Data("Najeeb Bhai","43"));

//        Data data = db.getOneData(1);
//        Log.d(TAG, "onCreate: got a data " + data.getName() + data.getAge());
//
        List<Data> dataList = db.gettingAllData();

        for(Data d : dataList) {

            Log.d(TAG, "onCreate: ID " + d.getId());
            Log.d(TAG, "onCreate: name " + d.getName());
            Log.d(TAG, "onCreate: age " + d.getAge());
        }

        Log.d(TAG, "onCreate: All database count " + db.getDatabaseCount());




       // Log.d(TAG, "onCreate: Data updated " + String.valueOf(updatedData));

    }
}
