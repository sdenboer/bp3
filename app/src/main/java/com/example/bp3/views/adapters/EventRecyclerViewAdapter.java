package com.example.bp3.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.AanbodEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koen Franken
 */

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "EventRecyclerViewAdapter";
    private List<AanbodEvent> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;
        TextView label2;
        TextView label3;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
            label2 = (TextView) itemView.findViewById(R.id.textView2);
            label3 = (TextView) itemView.findViewById(R.id.textView3);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public EventRecyclerViewAdapter(List<AanbodEvent> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_cardview, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(String.valueOf(mDataset.get(position).getEventnummer()));
        holder.label2.setText(String.valueOf(mDataset.get(position).getNaam()));
        holder.label3.setText(String.valueOf(mDataset.get(position).getDatumentijd()));
    }

    public void addItem(AanbodEvent dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
