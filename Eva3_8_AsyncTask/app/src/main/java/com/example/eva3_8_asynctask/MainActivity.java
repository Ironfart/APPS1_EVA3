package com.example.eva3_8_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtShow = findViewById(R.id.txtShow);

        AsyncClass clase = new AsyncClass();
        clase.execute(20, 500);
    }
                                    //entrada   progreso   fin
    class AsyncClass extends AsyncTask<Integer, String, String> {

        // este es el orden que usa AsyncTask
        // todos pueden interactuar con UI menos doInBG
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtShow.append("Se inicia la clase asíncrona\n");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            //aquí se hace el jale en segundo plano
            int times = integers[0];
            int sleep = integers[1];
            for (int i = 0; i < times; i++){
                try {
                    Thread.sleep(sleep);
                    publishProgress(i + " - \n" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Fin de la AsyncTask";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtShow.append(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtShow.append(s);
        }

    }
}
