package com.mio.app.mioapp.views;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mio.app.mioapp.R;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by PI2 on 29/11/17.
 */

public class TwitterActivity extends ListActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String CONSUMER_KEY = "iWtpJ2GWSgII8dKH4MPqcDdiw";
    private static final String CONSUMER_SECRET = "fvmPoBIfGNfsT1x40hjZDNZheEZf7xDuIkz9j9FhvIbb5qh0fM";
    private Context mContext;
    private BoomMenuButton bmb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_layout);
        mContext = this;

        bmb = (BoomMenuButton) findViewById(R.id.bmb);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);


        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("metrocali")
                .includeReplies(false)
                .includeRetweets(false)
                .maxItemsPerRequest(20)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        boolean a=  adapter.isEmpty();
        setListAdapter(adapter);
    }


    private void setBuilders(){
        HamButton.Builder builder1 = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_traza_ruta)
                .normalText("Planear ruta")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(TwitterActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                    }
                });
        bmb.addBuilder(builder1);

        HamButton.Builder builder2 = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_real_time)
                .normalText("Ubicar Rutas")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(TwitterActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                    }
                });
        bmb.addBuilder(builder2);

        HamButton.Builder builder3 = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_noticias)
                .normalText("Noticias")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(TwitterActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TwitterActivity.this, TwitterActivity.class);
                        startActivity(intent);
                    }
                });
        bmb.addBuilder(builder3);

        HamButton.Builder builder4 = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_puntos_recarga)
                .normalText("Puntos de recarga")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(TwitterActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                    }
                });
        bmb.addBuilder(builder4);
    }
}
