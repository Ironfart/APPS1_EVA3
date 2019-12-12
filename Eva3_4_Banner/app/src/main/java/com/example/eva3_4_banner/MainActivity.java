package com.example.eva3_4_banner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ImageView imgBanner;
    SeekBar bar;

    int cont = 0;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
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

    Thread hilo = new Thread(){
        @Override
        public void run() {
            super.run();
            while (true) {
                try {

                    Thread.sleep(1000);
                    Message msg = handler.obtainMessage();
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         imgBanner = findViewById(R.id.imgVwBanner);

        hilo.start();
    }
}
