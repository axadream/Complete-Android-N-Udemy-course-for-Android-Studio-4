package mathexa.com;
//*** We create an application that will download some stuff from the internet ***

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
/*
We are declaring a class that extends an existing class AsyncTask which takes 3 variables:
-the first variable is of type String and this will use to specify the url address;
-this is a void method used to show the progress of the download
- the last String variable is what our class is returning from the url address
 */

    public class DownloadTask extends AsyncTask<String, Void, String> {


        @Override
        /*
        Protected means that the doInBackground variable can be accessed everywhere at the level of our package application;
        String...urls is called the Variable Arguments or varargs. This means that zero or more String objects
         (or an array of them) may be passed as the argument(s) for that method. The reason we use this varargs over an array is because
         we can specify how many arguments we want to the method we want to call
         */
        protected String doInBackground(String... urls) {
            //Define a variable where to store the URL content:
            String result = "";

            //define an url variable:
            URL url;
            //HttpURLConnection class is considered as a browser:
            HttpURLConnection urlConnection = null;

            //we need to use try and catch block of code for the case we will have an error:
            /*
        try and catch is a standard block of code used for the cases our application could throw an exception (throw an error):
        The try statement allows you to define a block of code to be tested for errors while it is being executed.
        The catch statement allows you to define a block of code to be executed, if an error occurs in the try block.
         */
            try {
                /*
                When we are calling the variable of class DownloadTask in the onCreate method, we want to take in consideration
                only the first url in case there are more:
                 */
                url = new URL(urls[0]);
                //the URL is fetched into the browser:
                urlConnection = (HttpURLConnection) url.openConnection();

                //we need to put the data into a variable of type InputStream:
                InputStream in = urlConnection.getInputStream();

                //we need to read the data from the InputStream variable:
                InputStreamReader reader = new InputStreamReader(in);

                //the int variable will store the location of each character from the InputStreamReader variable:
                int data = reader.read();

                //the while loop will go through all characters using the location of each character: (-1 means there's no character left)
                while (data != -1) {
                    //we will cast the numeric location into a character:
                    char current = (char) data;
                    //each character is added to the string variable:
                    result += current;
                    //we need to read the location of the next character:
                    data = reader.read();
                }
                //all the stored characters are returned to the variable of type DownloadTask

                return result;
            } catch (Exception e) {

                e.printStackTrace();
                //this is the string text we want to be returned together with the information returned by printStackTrace() method
                return "Failed";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //we create a variable of our class type DownloadTask:
        DownloadTask task = new DownloadTask();
        String result = null;

/*We need to inform the user through a pop-up message about the right to access the internet: inside of the file AndroidManifest.xml we added the line:
<uses-permission android:name="android.permission.INTERNET"/>
 */
    /*
    The code from try statement is always executed and it is also tested against possible errors; if there is an error in the try
     block of code, then the code from catch is also executed and it will display additional information that could caused the errors:
     */

        try {
            /*
            In the block of code from try defined in the class DownloadTask we specified for url variable to get only the first element of the varargs,
            so the second url is ignored;
            The execute() is a standard java method that schedules the task represented by the instance to be executed on an existing worker thread.
            The get() method is used to get the element present in a list at a given specific index.
             */
            result = task.execute("https://www.ecowebhosting.co.uk/", "http://www.stackoverflow.com").get();
        } catch (ExecutionException e) {
            //printStackException() is a method that shows some useful information when something goes wrong:
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Content of URL", result);


    }
}