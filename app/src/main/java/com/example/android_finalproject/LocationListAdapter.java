package com.example.android_finalproject;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {
    private Cursor mCursor;
    private Context mContext;

    public LocationListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.location_list, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }


    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;
        long id = mCursor.getLong(mCursor.getColumnIndex("_id"));
        double longitude = mCursor.getDouble(mCursor.getColumnIndex("longitude"));
        double latitude = mCursor.getDouble(mCursor.getColumnIndex("latitude"));
        String name=mCursor.getString(mCursor.getColumnIndex("name"));

        holder.IDTextView.setText(String.valueOf(id));
        holder.longitudeTextView.setText(String.valueOf(longitude));
        holder.latitudeTextView.setText(String.valueOf(latitude));
        holder.NameTextView.setText(name);
        holder.itemView.setTag(id);
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView IDTextView;
        TextView longitudeTextView;
        TextView latitudeTextView;
        TextView NameTextView;
        public LocationViewHolder(View itemView) {
            super(itemView);
            IDTextView = (TextView) itemView.findViewById(R.id.tv_ID);
            longitudeTextView = (TextView) itemView.findViewById(R.id.tv_Lon);
            latitudeTextView=(TextView) itemView.findViewById(R.id.tv_Lat);
            NameTextView=(TextView)itemView.findViewById(R.id.tv_N);
        }

    }
}
