package com.angryziber.android.dtuguide;


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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SyllabusFragment extends Fragment {


    private RecyclerView mSyllabusList;
    private DatabaseReference mDatabase;

    private View rootview;
    private FirebaseRecyclerAdapter<Syllabus, SyllabusViewHolder> firebaseRecyclerAdapterForSyllabus;

    public SyllabusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_notices, container, false); //fragment_notices used for all three

        mDatabase = FirebaseDatabase.getInstance().getReference().child("syllabus");

        mSyllabusList = (RecyclerView) rootview.findViewById(R.id.notices_list);

        mSyllabusList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSyllabusList.setHasFixedSize(true);

        return rootview;
    }


    @Override
    public void onStart() {
        super.onStart();

        firebaseRecyclerAdapterForSyllabus = new FirebaseRecyclerAdapter< Syllabus,SyllabusViewHolder >(

                Syllabus.class,
                R.layout.list_view_row,
                SyllabusViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder (SyllabusViewHolder viewHolder, Syllabus model, int position) {
                viewHolder.setRefertext(model.getRefer());
                viewHolder.setLink(model.getLink());
            }
        };


        mSyllabusList.setAdapter(firebaseRecyclerAdapterForSyllabus);
    }


    public static class SyllabusViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        private String link;
        private Intent myIntent;

        public SyllabusViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                        mView.getContext().startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(mView.getContext(), "No Activity found to handle the request ", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            });

        }

        public void setRefertext(String refertext) {
            TextView referText = (TextView) mView.findViewById(R.id.refer_text);
            referText.setText(refertext);
        }


        public void setLink(String link) {
            this.link = link;

        }

    }


}
