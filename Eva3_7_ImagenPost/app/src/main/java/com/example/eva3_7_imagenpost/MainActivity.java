package com.example.eva3_7_imagenpost;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imgBanner;

    Bitmap bitmap = null;

    Handler handler = new Handler();

    Thread thread = new Thread(){
        @Override
        public void run() {
            super.run();
            bitmap = loadImg("https://www.cam.ac.uk/sites/www.cam.ac.uk/files/styles/content-885x432/public/news/research/news/crop_97.jpg?itok=ctrIf2MG");
            handler.post(runModUI);
        }
    };

    //aqui si podemos modificar UI
    Runnable runModUI = new Runnable() {
        @Override
        public void run() {
            // aqui va el trabajo de modificar UI
            imgBanner.setImageBitmap(bitmap);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBanner = findViewById(R.id.imgBanner);

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
