package com.example.eva3_3_hiloejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imgShow;
    Bitmap bitmap = null;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            imgShow.setImageBitmap(bitmap);
        }
    };

    Thread thread = new Thread(){
        @Override
        public void run() {
            super.run();
            bitmap = loadImg("https://www.cam.ac.uk/sites/www.cam.ac.uk/files/styles/content-885x432/public/news/research/news/crop_97.jpg?itok=ctrIf2MG");
            Message msg = handler.obtainMessage();
            handler.sendMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgShow = findViewById(R.id.imgVwShow);

        //Bitmap bitmap = loadImg("url a la imagen");
        //imgShow.setImageBitmap(bitmap);

        thread.start();
    }

    private Bitmap loadImg(String url){
        Bitmap image = null;

        try {
            InputStream input = (InputStream) new URL(url).getContent();
            image = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }
}
