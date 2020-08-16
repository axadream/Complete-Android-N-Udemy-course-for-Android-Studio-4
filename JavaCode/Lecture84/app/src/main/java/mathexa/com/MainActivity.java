package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        DownloadTask task=new DownloadTask();
        task.execute("https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=439d4b804bc8187953eb36d2a8c26a02");
    }

    public class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            String result="";
            URL url;
            HttpURLConnection urlConnection=null;
            try {
                url=new  URL(urls[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while (data!=-1) {
                    char current=(char)data;
                    result+=current;
                    data=reader.read();
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            /*
            JSON stands for JavaScript Object Notation;
            JSON is a lightweight format for storing and transporting data
            JSON is often used when data is sent from a server to a web page
            JSON is "self-describing" and easy to understand.

            JSON objects are surrounded by curly braces {}.
            JSON objects are written in key/value pairs.
            Keys must be strings, and values must be a valid JSON data type (string, number, object, array, boolean or null).
            Keys and values are separated by a colon.
            Each key/value pair is separated by a comma.
             */

            try {
                JSONObject jsonObject=new JSONObject(result);
                //we want to extract only weather part from JSON object:
                String weatherInfo=jsonObject.getString("weather");
                Log.i("Weather content",weatherInfo);

                JSONArray arr=new JSONArray(weatherInfo);
                for (int i=0;i<arr.length();i++) {
                    JSONObject jsonPart=arr.getJSONObject(i);
                    Log.i("main",jsonPart.getString("main"));
                    Log.i("description",jsonPart.getString("description"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}