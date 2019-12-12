package com.example.eva3_6_bannerpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgBanner;

    int cont = 0;

    Handler handler = new Handler();

    Thread hilo = new Thread(){
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(1000);
                    handler.post(runModUI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    //aqui si podemos modificar UI
    Runnable runModUI = new Runnable() {
        @Override
        public void run() {
            // aqui va el trabajo de modificar UI
            switch (cont){
                case 0:
                    imgBanner.setImageResource(R.drawable.rainy);
                    cont++;
                    break;
                case 1:
                    imgBanner.setImageResource(R.drawable.thunderstorm);
                    cont++;
                    break;
                default:
                    imgBanner.setImageResource(R.drawable.snow);
                    cont = 0;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBanner = findViewById(R.id.imgShow);

        hilo.start();
    }
}
