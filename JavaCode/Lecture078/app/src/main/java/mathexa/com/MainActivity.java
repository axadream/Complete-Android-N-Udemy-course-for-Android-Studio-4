package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView downloadedImg;

    public void downloadImage(View view) {

        //https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png

        //we will create a variable of our class type ImageDownloader:
        ImageDownloader task = new ImageDownloader();
        //we define a bitmap variable used to get the information about our image'address:
        Bitmap myImage;

        //get() is the inbuilt method in Java and is used to return the element of the varargs, requires a try and catch block of code:
        try {
            myImage = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            /*
            The downloaded image is linked to our bitmap variable and additionally we add
             additionally we will add into the AndroidManifest.xml file this code before <Application>: <uses-permission android:name="android.permission.INTERNET"/>
             */
            downloadedImg.setImageBitmap(myImage);
        } catch (Exception e) {

            e.printStackTrace();
        }

        //Check if the button click is working:
        Log.i("Interaction", "Button Tapped");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadedImg = (ImageView) findViewById(R.id.imageView);
    }

    /*
    We define our class where we extends our code to the existing AsyncTask class where
    Bitmap is our returned image;
     */

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        /* doIngBackground is an existing method where urls is a varargs or
        variable-length arguments that is useful for methods that need to take a variable number of arguments
         */
        protected Bitmap doInBackground(String... urls) {
            //we define the url that mandatory will be surrounded by try and catch block of code for the cases the url is not the correct one:
            try {
                URL url = new URL(urls[0]);
                /*
                We will define an url connection where the method openConnection() needs
                mandatory a catch block of code with IOException:
                 */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                /*
                Because we need to download first the image and then we need to convert it to a bitmap format,
                we'll calling the connect() method:
                 */
                connection.connect();
                //we wil download the whole picture as an input stream of bytes:
                InputStream inputStream = connection.getInputStream();

                //conversion between input stream to bitmap:
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                //returning the image to original calling method:
                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}