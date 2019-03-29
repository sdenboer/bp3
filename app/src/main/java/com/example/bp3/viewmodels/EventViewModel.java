package com.example.bp3.viewmodels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.bp3.R;
import com.example.bp3.custom.EventRecyclerViewAdapter;
import com.example.bp3.models.AanbodEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EventViewModel extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event);

        //mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((EventRecyclerViewAdapter) mAdapter).setOnItemClickListener(new EventRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<AanbodEvent> getDataSet() {
        ArrayList results = new ArrayList<AanbodEvent>();
        /*String sDate1="29/03/2019";
        Date datum = null;
        try {
            datum = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int index = 0; index < 20; index++) {
            AanbodEvent obj = new AanbodEvent(index, "Naam" , "Loatie", datum,
                    5, "Omschrijving","Email");
            results.add(index, obj);
        }*/
        return results;
    }
}
