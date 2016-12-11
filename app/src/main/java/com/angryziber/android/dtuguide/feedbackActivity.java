package com.angryziber.android.dtuguide;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;



public class feedbackActivity extends AppCompatActivity {

    public static final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL
    public static final String URL="https://docs.google.com/forms/d/e/1FAIpQLScBegvi-zM_BcUrrpqd7Q2XfmjXz68AseckjCnZDDIK-5Uk0Q/formResponse";
    //input element ids found from the live form page
    public static final String NAME_KEY="entry.212472135";
    public static final String PHONE_KEY="entry.780310857";
    public static final String FEEDBACK_KEY="entry.278861858";


//    name : entry.212472135
//    phone:entry.780310857
//    feedback: entry.278861858

    private  Context context;
   private EditText nameText, phoneText, feedbackText;
    Button sendButton;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //save the activity in a context variable to be used afterwards
        context =this;
        final Intent backToMainIntent = new Intent (this,MainActivity.class);
        //Get references to UI elements in the layout
        nameText        = (EditText)    findViewById(R.id.nameText);
        phoneText       = (EditText)    findViewById(R.id.phoneNumberText);
        feedbackText    = (EditText)    findViewById(R.id.feedbackText);
        sendButton      = (Button)      findViewById(R.id.button);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_feedback);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Make sure all the fields are filled with values
                if(TextUtils.isEmpty(nameText.getText().toString()) ||
                        TextUtils.isEmpty(phoneText.getText().toString()) ||
                        TextUtils.isEmpty(feedbackText.getText().toString()))
                {
                    Toast.makeText(context,"All fields are mandatory.",Toast.LENGTH_LONG).show();
                    return;
                }
                //Check if a valid email is entered
//                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches())
//                {
//                    Toast.makeText(context,"Please enter a valid email.",Toast.LENGTH_LONG).show();
//                    return;
//                }

                //Create an object for PostDataTask AsyncTask
                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL,nameText.getText().toString(),
                        phoneText.getText().toString(),
                        feedbackText.getText().toString());


                startActivity(backToMainIntent);
            }
        });
    }

    //AsyncTask to send data as a http POST request
    private class PostDataTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... contactData) {
            Boolean result = true;
            String url = contactData[0];
            String name = contactData[1];
            String phone = contactData[2];
            String feedback = contactData[3];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = NAME_KEY+"=" + URLEncoder.encode(name,"UTF-8") +
                        "&" + PHONE_KEY + "=" + URLEncoder.encode(phone,"UTF-8") +
                        "&" + FEEDBACK_KEY + "=" + URLEncoder.encode(feedback,"UTF-8");
            } catch (UnsupportedEncodingException ex) {
                result=false;
            }

            /*
            //If you want to use HttpRequest class from http://stackoverflow.com/a/2253280/1261816
            try {
			HttpRequest httpRequest = new HttpRequest();
			httpRequest.sendPost(url, postBody);
		}catch (Exception exception){
			result = false;
		}
            */

            try{
                //Create OkHttpClient for sending request
                OkHttpClient client = new OkHttpClient();
                //Create the request body with the help of Media Type
                RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                //Send the request
                Response response = client.newCall(request).execute();
            }catch (IOException exception){
                result=false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result){
            //Print Success or failure message accordingly
//            Snackbar snackbar = Snackbar
//                    .make(relativeLayout, result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.", Snackbar.LENGTH_LONG);
//            snackbar.show();
            // NOT WORKING SNACKBAR
            Toast.makeText(context,result?"Feedback successfully sent!":"There was some error in sending Feedback. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }
}
