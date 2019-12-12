package com.example.eva3_5_handlerpost;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtMsg;

    Handler handler = new Handler();

    //aqui no podeomos modificar UI
    Thread thread = new Thread(){
        @Override
        public void run() {
            super.run();
            // trabajo en segundo plano
            while (true){
                try {
                    Thread.sleep(500);
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
            txtMsg.append("Hola Mundo! \t");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = findViewById(R.id.txtMsg);



        thread.start();
    }
}
