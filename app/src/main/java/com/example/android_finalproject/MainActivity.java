package com.example.android_finalproject;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;


import com.example.android_finalproject.data.DbHelper;
import com.example.android_finalproject.data.TestUtil;

public class MainActivity extends AppCompatActivity {
    private LocationListAdapter mAdapter;
    private SQLiteDatabase mDb;
    private EditText mNewlongitudeEditText;
    private EditText mNewlatitudeEditText;
    private EditText mNewNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView locationRecyclerView;
        locationRecyclerView=(RecyclerView)this.findViewById(R.id.rv_location);
        mNewlongitudeEditText=(EditText)this.findViewById(R.id.edit_Longitude);
        mNewlatitudeEditText=(EditText) this.findViewById(R.id.edit_Latitude);
        mNewNameEditText=(EditText)this.findViewById(R.id.edit_Name);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DbHelper dbHelper=new DbHelper(this);
        mDb=dbHelper.getWritableDatabase();
        TestUtil.insertFakeData(mDb);
        Cursor cursor=getAllLocation();
        mAdapter=new LocationListAdapter(this,cursor);
        locationRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                                long id = (long) viewHolder.itemView.getTag();
                                removeLocation(id);
                                mAdapter.swapCursor(getAllLocation());
            }
        }).attachToRecyclerView(locationRecyclerView);
    }

    public void addToLocationList(View view) {
        if (mNewNameEditText.getText().length() == 0 ||
                mNewlatitudeEditText.getText().length() == 0 ||
                mNewlongitudeEditText.getText().length() ==0) {
            return;
        }
        double longitude=0.0, latitude=0.0;
        longitude=Double.parseDouble(mNewlongitudeEditText.getText().toString());
        latitude=Double.parseDouble(mNewlatitudeEditText.getText().toString());
        addNewLocation(longitude, latitude,mNewNameEditText.getText().toString());

        mAdapter.swapCursor(getAllLocation());


        mNewNameEditText.clearFocus();
        mNewlongitudeEditText.getText().clear();
        mNewlatitudeEditText.getText().clear();
        mNewNameEditText.getText().clear();
    }

    private long addNewLocation(double longitude, double latitude,String name) {
        ContentValues cv = new ContentValues();
        cv.put("longitude", longitude);
        cv.put("latitude",latitude);
        cv.put("name",name);
        return mDb.insert("location", null, cv);
    }


    private Cursor getAllLocation() {
        return mDb.query(
                "location",
                null,
                null,
                null,
                null,
                null,
               null
        );
    }

    private boolean removeLocation(long id) {
        return mDb.delete("location",   "_id=" + id, null) > 0;
    }
}
