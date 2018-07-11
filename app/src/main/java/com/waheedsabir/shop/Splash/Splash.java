package com.waheedsabir.shop.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.waheedsabir.shop.R;
import com.waheedsabir.shop.map.Map;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getSupportActionBar().hide();


        Thread thread = new Thread(){

            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    startActivity(new Intent(Splash.this, Map.class));
                    finish();
                } catch (InterruptedException e) {


                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }
}
