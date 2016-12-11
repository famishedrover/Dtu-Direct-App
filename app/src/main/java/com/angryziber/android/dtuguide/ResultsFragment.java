package com.angryziber.android.dtuguide;


import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ResultsFragment extends Fragment {

    private RecyclerView mResultsList;
    private DatabaseReference mDatabase;
    private View rootview;
    private ProgressDialog progressBar;
    private FirebaseRecyclerAdapter<Results,ResultViewHolder> firebaseRecyclerAdapterForResults;


    public ResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_notices, container, false);
        Toast.makeText(getActivity(),"Please Wait While Load , It May Take Some Time ...", Toast.LENGTH_SHORT).show();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("results");

        mResultsList = (RecyclerView) rootview.findViewById(R.id.notices_list);

        mResultsList.setLayoutManager(new LinearLayoutManager(  getActivity() ) );

        mResultsList.setHasFixedSize(true);


        progressBar = new ProgressDialog(getContext());
        progressBar.setMessage("Page is Loading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(true);
        progressBar.show();
        // Prevent Cancellation
        progressBar.setCancelable(false);
        progressBar.setCanceledOnTouchOutside(false);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //onDataChange called so remove progress bar
                progressBar.dismiss();
                //make a call to dataSnapshot.hasChildren() and based
                //on returned value show/hide empty view

                //use helper method to add an Observer to RecyclerView
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return rootview;
    }

    @Override
    public void onStart() {
        super.onStart();

        firebaseRecyclerAdapterForResults = new FirebaseRecyclerAdapter< Results, ResultViewHolder>(

                Results.class,
                R.layout.list_view_row,
                ResultViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(ResultViewHolder viewHolder, Results model, int position) {
                viewHolder.setRefertext(model.getRefer());
                viewHolder.setDatetext(model.getDate());
                viewHolder.setLink(model.getLink());
            }
        };

        mResultsList.setAdapter(firebaseRecyclerAdapterForResults);

    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder{

        final View mView;
        private String link;
        private Intent myIntent;
        public ResultViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                        mView.getContext().startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(mView.getContext(),"No Activity found to handle the request ",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            });

        }
        public void setRefertext(String refertext){
            TextView referText = (TextView) mView.findViewById(R.id.refer_text);
            referText.setText(refertext);
        }

        public void setDatetext (String datetext){
            TextView dateText = (TextView) mView.findViewById(R.id.date_text);
            dateText.setText(datetext);

        }

        public void setLink (String link){
            this.link = link;

        }

    }


}
