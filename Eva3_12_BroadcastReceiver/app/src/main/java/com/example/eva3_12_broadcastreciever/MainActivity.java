package com.example.eva3_12_broadcastreciever;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtShow;
    Intent inService;
    BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtShow = findViewById(R.id.txtShow);
        inService = new Intent(this, MyService.class);

        br = new MyBroadcast();
        IntentFilter filter = new IntentFilter("MI_SERVICIO");
        registerReceiver(br, filter);
    }

    public void startServ(View v) {
        startService(inService);
    }

    public void stopServ(View v) {
        stopService(inService);
    }

    class MyBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            txtShow.append(intent.getStringExtra("msg") + "\n");
        }
    }
}
