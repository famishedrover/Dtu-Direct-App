package com.angryziber.android.dtuguide;


import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.content.Context.DOWNLOAD_SERVICE;


public class NotesFragment extends Fragment {


    public NotesFragment() {
        // Required empty public constructor
    }
//    String url="https://drive.google.com/file/d/0B3SlXVFCF5ZYZ0FIejBTOGJuME0";
    String url = "https://drive.google.com/open?id=0B1Np6680AusWMUFFMEJWYkFQRm8";
    private WebView wv;
    //private SwipeRefreshLayout swipe;
    private ProgressDialog progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootview= inflater.inflate(R.layout.fragment_notes, container, false);
        Toast.makeText(getActivity(),"Please Wait While Load , It May Take Some Time ...", Toast.LENGTH_LONG).show();
        wv = (WebView)rootview.findViewById(R.id.web_view);
        wv.setWebViewClient(new MyBrowser());
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.loadUrl(url);

        wv.setDownloadListener(new DownloadListener()
        {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
            {
                //for downloading directly through download manager
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //---this stops the app if download folder is not found---  //request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download");
                DownloadManager dm = (DownloadManager)getActivity().getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
            }
        });

        progressBar = new ProgressDialog(getContext());
        progressBar.setMessage("Page is Loading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(true);
        progressBar.show();

        progressBar.setCancelable(false);
        progressBar.setCanceledOnTouchOutside(false);

       /* swipe=(SwipeRefreshLayout)rootview.findViewById(R.id.swipe_view);
        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        wv.reload();
                    }
                }
        );*/
        return rootview;
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.dismiss();
            //swipe.setRefreshing(false);
        }
    }

}