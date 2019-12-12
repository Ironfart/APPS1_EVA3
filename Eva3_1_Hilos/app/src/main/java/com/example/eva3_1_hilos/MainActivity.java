package com.example.eva3_1_hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Thread hilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txtHilo);

        hilo = new Thread(){
            @Override
            public void run() {
                super.run();
                //jale en segundo plano
                while (true){
                    try {
                        Thread.sleep(1000);
                        Log.wtf("hilo","hilo");
                        txt.setText("Hilo");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.wtf("asd", e);
                        break;
                    }
                }
            }
        };
        hilo.start();
        
        Runnable run = new Runnable() {
            @Override
            public void run() {

                //jale en segundo plano
                while (true){
                    try {
                        Thread.sleep(1000);
                        Log.wtf("runnable", "runnable");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        run.run();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hilo.interrupt();
    }
}
