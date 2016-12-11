package com.angryziber.android.dtuguide;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class CCouncilFragment extends Fragment {


    public CCouncilFragment() {
        // Required empty public constructor
    }
//    String url="https://www.facebook.com/dtucc/?";
    String url = "https://m.facebook.com/profile.php?id=125440381131402&tsid=0.8450324379628109&source=typeahead";
    private WebView webView;
    private  ProgressDialog progressBar;
    private SwipeRefreshLayout swipe;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_ccouncil, container, false);
        webView = (WebView)rootview.findViewById(R.id.web_view);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.loadUrl(url);
        webView.postDelayed(new Runnable() {

            @Override
            public void run() {
                webView.loadUrl(url);
            }
        }, 500);


//        new Thread(new Runnable() {
//            public void run() {
//
//            }
//        }).start();







        webView.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });


        progressBar = new ProgressDialog(getContext());
        progressBar.setMessage("Page is Loading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(true);
        progressBar.show();


        // Prevent Cancellation
        progressBar.setCancelable(false);
        progressBar.setCanceledOnTouchOutside(false);

        swipe=(SwipeRefreshLayout)rootview.findViewById(R.id.swipe_view);
        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        webView.reload();
                    }
                }
        );

        return rootview;



    }




    private class MyBrowser extends WebViewClient {



        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if(!url.startsWith("tel:")){
                    view.loadUrl(url);}
            else{
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(myIntent);
            }

            return true;
        }



        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.dismiss();
            swipe.setRefreshing(false);
        }
    }



// not working
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_BACK:
//
//                // back button is pressed.. Do your stuff here
//                if(webView!=null)
//                {
//                    if(webView.canGoBack())
//                    {
//                        webView.goBack();
//                    }
//                }
//
//                return true;
//        }
//        return false;
//    }

}
