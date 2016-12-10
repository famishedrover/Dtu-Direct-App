package com.example.android.dtuguide;


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


public class NoticesFragment extends Fragment {

    private RecyclerView mNoticesList;
    private DatabaseReference mDatabase;
//    private ProgressDialog vProgress;
    private View rootview;
    private FirebaseRecyclerAdapter<Notices,NoticeViewHolder> firebaseRecyclerAdapter;


    public NoticesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootview = inflater.inflate(R.layout.fragment_notices, container, false);
        Toast.makeText(getActivity(),"Please Wait While Load , It May Take Some Time ...", Toast.LENGTH_SHORT).show();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("notice");

        mNoticesList = (RecyclerView) rootview.findViewById(R.id.notices_list);

        mNoticesList.setLayoutManager(new LinearLayoutManager(  getActivity() ) );

        mNoticesList.setHasFixedSize(true);

        return rootview;
    }


    @Override
    public void onStart() {
        super.onStart();

         firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Notices, NoticeViewHolder>(

                Notices.class,
                R.layout.list_view_row,
                NoticeViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(NoticeViewHolder viewHolder, Notices model, int position) {
                    viewHolder.setRefertext(model.getRefer());
                    viewHolder.setDatetext(model.getDate());
                    viewHolder.setLink(model.getLink());
            }
        };

//
//
//        vProgress = new ProgressDialog(getActivity());
//        vProgress.setMessage("Data NOTICES is Loading ...");
//        vProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        vProgress.setIndeterminate(true);
//
//        firebaseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//
//            @Override
//            public void onChanged() {
//                vProgress.show();
//                firebaseRecyclerAdapter.unregisterAdapterDataObserver(this);
//            }
//        });
//
//        vProgress.hide();


        mNoticesList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder{

        final View mView;
        private String link;
        private Intent myIntent;
         public NoticeViewHolder(View itemView) {
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
