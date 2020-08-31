package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//***ATTENTION: Expect to wait for about 5 minutes till the application will load all the data from the internet
public class MainActivity extends AppCompatActivity {

    //Below are declared all the global variables that need to be accessed inside of the methods:

    //celebURLs is the variable used to store all the url links:
    ArrayList<String> celebURLs = new ArrayList<>();
    //celebNames is the variable used to store all the names associated to the url links:
    ArrayList<String> celebNames = new ArrayList<>();

    //this is the variable to store random values:
    int chosenCeleb = 0;
    //the variable to store the button location of the correct answer:
    int locationOfCorrectAnswer = 0;
    //variable of type array string to store the names to any of the 4 buttons:
    String[] answers = new String[4];
    //this is the view object where will be displayed the image from the url:
    ImageView imageView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void celebChosen(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong! IT was " + celebNames.get(chosenCeleb), Toast.LENGTH_SHORT).show();
        }
        createNewQuestion();
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //in case something goes wrong we will return null:
            return null;
        }
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            /*
            Add the statement below into the file AndroidManifest.xml:
            <uses-permission android:name="android.permission.INTERNET" />
             */
            result = task.execute("https://www.imdb.com/list/ls052283250").get();
            //we are splitting the web page using a specific html tag <div id="sidebar"> :
            //with the split() method all the content included into the div tag <div id="sidebar"></> will not be considered:
            String[] splitResult = result.split("<div id=\"sidebar\">");
            String imageURL = "https://m.media-amazon.com";

            Pattern p = Pattern.compile("<img alt=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);

            while (m.find()) {
                //storing all the names into the array list:
                celebNames.add(m.group(1));
            }
            p = Pattern.compile("src=\"https://m.media-amazon.com(.*?)\"");
            m = p.matcher(splitResult[0]);

            while (m.find()) {
                //storing all the pictures url into the array list:
                celebURLs.add(imageURL + m.group(1));

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createNewQuestion();

    }

    public void createNewQuestion() {
        //we wil generate random values from 0 to the highest value of items from celebURLs:
        Random random = new Random();
        chosenCeleb = random.nextInt(celebURLs.size());

        ImageDownloader imageTask = new ImageDownloader();
        Bitmap celebImage;
        try {
            celebImage = imageTask.execute(celebURLs.get(chosenCeleb)).get();
            imageView.setImageBitmap(celebImage);
            /*
            The correct answer could be displayed in any of the 4 buttons, this is why
            we could have a maximum of 4 values (from 0 to 3):
             */
            locationOfCorrectAnswer = random.nextInt(4);
            int incorrectAnswerLocation;
            for (int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {
                    answers[i] = celebNames.get(chosenCeleb);
                } else {
                    incorrectAnswerLocation = random.nextInt(celebURLs.size());

                    while (incorrectAnswerLocation == chosenCeleb) {
                        incorrectAnswerLocation = random.nextInt(celebURLs.size());
                    }

                    answers[i] = celebNames.get(incorrectAnswerLocation);

                }
            }
            button0.setText(answers[0]);
            button1.setText(answers[1]);
            button2.setText(answers[2]);
            button3.setText(answers[3]);

            //Log.i("Contents of URL",result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}