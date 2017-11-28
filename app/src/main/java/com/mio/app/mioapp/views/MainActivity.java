package com.mio.app.mioapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mio.app.mioapp.R;

public class MainActivity extends AppCompatActivity {

    long startTime;
    ImageView logo_splash;


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




    public void goToLiveView(){
        int timer = 100;
        //while (System.currentTimeMillis()-startTime<timer) {

           // if (System.currentTimeMillis() - startTime >= timer) {
                Intent intent = new Intent(this, live_view.class);
                startActivity(intent);

          //  }
       // }
    }
}
