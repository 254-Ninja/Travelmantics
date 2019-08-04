package com.example.travelmantics;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {
    ArrayList<TravelDeal> deals;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;

   public DealAdapter(){
       FirebaseUtil.openFbReference("traveldeals");
       mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
       mDatabaseReference = FirebaseUtil.mDatabaseReference;
       deals = FirebaseUtil.mDeals;
       mChildListener = new ChildEventListener() {

           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               TravelDeal td = dataSnapshot.getValue(TravelDeal.class);
               Log.d("Deal: ", td.getTitle());
               td.setId(dataSnapshot.getKey());
               deals.add(td);
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       };

       mDatabaseReference.addChildEventListener(mChildListener);

   }
    @Override
    public DealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DealViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DealViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        public DealViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }

        public void bind(TravelDeal deal){
            tvTitle.setText(deal.getTitle());
        }
    }
}
