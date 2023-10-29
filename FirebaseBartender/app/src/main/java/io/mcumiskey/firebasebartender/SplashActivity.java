package io.mcumiskey.firebasebartender;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private ImageView cloud1;
    private ImageView cloud2;
    private ImageView star;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        //5 sec timer
        Timer opening = new Timer();
        opening.schedule(task,5000);


        //animation!
        star = findViewById(R.id.star_imageView);
        cloud1 = findViewById(R.id.cloud_imageView);
        cloud2 = findViewById(R.id.cloud_imageView2);


        star.animate().rotation(180f).setDuration(5000).start();

        cloud1.animate().translationX(100f).setDuration(5000).start();
        cloud2.animate().translationX(100f).setDuration(5000).start();

    }
}