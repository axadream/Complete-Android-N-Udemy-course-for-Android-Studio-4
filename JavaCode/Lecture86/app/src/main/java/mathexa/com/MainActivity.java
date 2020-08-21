package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

   /*
   Because the free API uses a connection with servers based on a cleartext network traffic, such as HTTP we need to add the following code in the androidmanifest.xml:
   <application
   ....
   android:usesCleartextTraffic="true"
    </application>
    */

    EditText cityName;
    TextView resultTextView;
    String message = "";
   // private final static int MAX_LINE = 2;

    public void myToast() {
        Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG).show();
    }

    /*
    This is a method to delete the data lines in excess displayed in the TextView object if we keep this statement:
     message += main + ": " + description+ "\n";
     */
    /*
    public void deleteExcessLines(String data) {
        resultTextView.append(data);
        int excessLineNumber = resultTextView.getLineCount() - MAX_LINE;
        Log.i("Numarul de linii: ", Integer.toString(excessLineNumber));
        if (excessLineNumber > 0) {
            int eolIndex = -1;
            CharSequence charSequence = resultTextView.getText();
            for (int i = 0; i < excessLineNumber; i++) {

                do {
                    eolIndex++;
                } while (eolIndex < charSequence.length() && charSequence.charAt(eolIndex) != '\n');

            }

            if (eolIndex < charSequence.length()) {
                resultTextView.getEditableText().delete(0, eolIndex + 1);
            }
            else {
               resultTextView.setText("");
            }
        }
    }
*/
    public void findWeather(View view) {


//check the functionality of the button:
        Log.i("cityName", cityName.getText().toString());
//we need to hide the keyboard once we tapped the city name:

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(cityName.getWindowToken(), 0);
//if there is blank space into the text field the application should encode this if our AIP will put this character % between the city names.E.g. San Francisco is encoded as San%Francisco by our AIP:
        try {
            String encodedCityName = URLEncoder.encode(cityName.getText().toString(), "UTF-8");
            DownloadTask task = new DownloadTask();
            task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=a75aa25237530fef5dac85427c644390");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

            resultTextView.getEditableText().clear();
            myToast();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = (EditText) findViewById(R.id.cityNAme);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
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

            try {

                JSONObject jsonObject = new JSONObject(result);
                //we want to extract only weather part from JSON object:
                String weatherInfo = jsonObject.getString("weather");
                Log.i("Weather content", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    String main = "";
                    String description = "";
                    main = jsonPart.getString("main");
                    description = jsonPart.getString("description");

                    if (main != "" && description != "") {
                        // \r\n means add a new line:

                        //if we use message+= then  this is equivalent to resultTextView.append() method: the text is added to the existing one:
                        message = main + ": " + description;   //+ "\n";
                    }

                }


                if (message != "") {

                   // Calling of the method deleteExcessLines(message); if we keep this statement:  message += main + ": " + description+ "\n";

                    resultTextView.setText(message);

                } else {


                    myToast();

            }

            } catch (JSONException e) {
                e.printStackTrace();

            } catch (Exception e) {

                resultTextView.setText("");
                myToast();
            }


        }
    }
}