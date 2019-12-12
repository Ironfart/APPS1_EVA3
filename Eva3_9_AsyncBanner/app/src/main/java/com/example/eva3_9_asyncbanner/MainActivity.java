package com.example.eva3_9_asyncbanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imgBanner);

        AsyncClass clase = new AsyncClass();
        clase.execute(20, 500);
    }

                                    //entrada   progreso   fin
    class AsyncClass extends AsyncTask<Integer, Integer, String> {

        // este es el orden que usa AsyncTask
        // todos pueden interactuar con UI menos doInBG
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            //aquí se hace el jale en segundo plano
            int times = integers[0];
            int sleep = integers[1];
            for (int i = 0; i < times; i++){
                try {
                    Thread.sleep(sleep);
                    publishProgress(cont++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Fin de la AsyncTask";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (cont){
                case 0:
                    img.setImageResource(R.drawable.rainy);
                    cont++;
                    break;
                case 1:
                    img.setImageResource(R.drawable.thunderstorm);
                    cont++;
                    break;
                default:
                    img.setImageResource(R.drawable.snow);
                    cont = 0;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }

    }
}
