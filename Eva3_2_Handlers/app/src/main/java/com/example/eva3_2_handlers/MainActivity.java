package com.example.eva3_2_handlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtMsg;
    Thread hilo;

    //handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //aqui se puede interactuar con la UI
            //estamos en el hilo principal
            String mensaje = (String) msg.obj;
            txtMsg.append(mensaje + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = findViewById(R.id.txtMsg);

        hilo = new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (true){
                    try {
                        Thread.sleep(500);

                        String cadena = "i: " + i;

                        Message msg = handler.obtainMessage(1, cadena);  //mandarle mensaje al handler
                        handler.sendMessage(msg); //mostrar mensaje

                        Log.wtf("Hilo", cadena);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    i++;
                }
            }
        };

        hilo.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hilo.interrupt();
    }
}
