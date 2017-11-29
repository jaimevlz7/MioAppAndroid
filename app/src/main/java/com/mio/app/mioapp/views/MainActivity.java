package com.mio.app.mioapp.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.mio.app.mioapp.R;

public class MainActivity extends AppCompatActivity {

    long startTime;
    ImageView logo_splash;
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        startTime = System.currentTimeMillis();

        logo_splash =(ImageView) findViewById(R.id.splash_logo_img);

        /*
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.splash_object_intro);
        set.setTarget(logo_splash);
        set.start();*/

        new CountDownTimer(3000, 500) {


            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("TICK", "onTick: ");
            }

            public void onFinish() {
                goToLiveView();
            }
        }.start();

        Animation splash_intro = AnimationUtils.loadAnimation(this, R.anim.splash_object_intro);
        logo_splash.startAnimation(splash_intro);

       // goToLiveView();
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }




    public void goToLiveView(){
        int timer = 100;
        //while (System.currentTimeMillis()-startTime<timer) {

           // if (System.currentTimeMillis() - startTime >= timer) {
        if(isServicesOK()){
            Intent intent = new Intent(this, live_view.class);
            startActivity(intent);
        }
          //  }
       // }
    }
}
