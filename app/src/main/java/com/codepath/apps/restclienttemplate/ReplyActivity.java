package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ReplyActivity extends AppCompatActivity {

    public static final String TAG = "ReplyActivity";
    public static final int MAX_TWEET_LENGTH = 280;
    EditText etReply;
    Button btnReply;

    TwitterClient client;

    Tweet tweet = new Tweet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        client = TwitterApp.getRestClient(this);

        etReply = findViewById(R.id.etReply);
        btnReply = findViewById(R.id.btnReply);

        // To automatically get the user to reply to
        Intent intent = getIntent();
        String screenName = intent.getExtras().getString("key");
        etReply.append("@" + screenName + " ");

        // Set click listener on button
        btnReply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String tweetContent = etReply.getText().toString();
                if (tweetContent.isEmpty()) {
                    Toast.makeText(ReplyActivity.this, "Sorry, your tweet cannot be empty.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (tweetContent.length() > MAX_TWEET_LENGTH) {
                    Toast.makeText(ReplyActivity.this, "Sorry, your tweet is too long.", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(ReplyActivity.this, tweetContent, Toast.LENGTH_LONG).show();
                // Make an API call to Twitter to publish the tweet
                client.publishTweet(tweetContent, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.i(TAG, "onSuccess to publish tweet");
                        try {
                            Tweet tweet = Tweet.fromJson(json.jsonObject);
                            Log.i(TAG, "Published tweet says: " + tweet.body);
                            Intent intent = new Intent();
                            intent.putExtra("tweet", Parcels.wrap(tweet));
                            // Set result code and bundle data for response
                            setResult(RESULT_OK, intent);
                            // Closes the activity, pass data to parent
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.e(TAG, "onFailure to publish tweet", throwable);
                        Log.e(TAG, response, throwable);
                        Log.e(TAG, String.valueOf(statusCode), throwable);
                    }
                });
            }
        });
    }
}