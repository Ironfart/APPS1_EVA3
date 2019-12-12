package com.example.eva3_10_climaasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ClimaAsync().execute();
    }

    class ClimaAsync extends AsyncTask<Void, Void, String>{

        final String ruta = "https://samples.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=10&appid=b6907d289e10d714a6e88b30761fae22";

        @Override
        protected String doInBackground(Void... voids) {
            String res = null;
            // Aqui va la conexion

            try {
                URL url = new URL(ruta);

                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.connect();

                if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String line;
                    StringBuffer lines = new StringBuffer();

                    InputStream input = http.getInputStream();
                    InputStreamReader isReader = new InputStreamReader(input);
                    BufferedReader bufReader = new BufferedReader(isReader);

                    while ((line = bufReader.readLine()) != null) {
                        lines.append(line);
                    }
                    res = lines.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null){
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                //Aquí procesamos los JSON
                /*try {
                    JSONObject jsonWeather = new JSONObject(s);
                    JSONArray jsonCities = jsonWeather.getJSONArray("list");

                    for (int i = 0; i < jsonWeather.length();i++){
                        //leer cada ciudad y poner los datos en una lista
                        JSONObject jsonCity = jsonCities.getJSONObject(i);
                        //nombre de ciudad
                        //jsonCity.getString("name");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                //conectar el adaptador al
            }
        }
    }
}
