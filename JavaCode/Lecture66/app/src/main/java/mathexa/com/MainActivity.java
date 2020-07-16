package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OPTION 1 to handle timers using the constructor CountDownTimer:
        //We are running the constructor CountDownTime() that is destroyed when is finished
        // A constructor is a special method that is used to initialize objects.
        // The constructor is called when an object of a class is created:
        new CountDownTimer(10000, 100) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Seconds left", String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Log.i("Done", "Countdown Times Finished ");
            }


        }.start();



 /* <--uncomment this to run the cod below
        //OPTION 2 to handle timers by using handlers and Runnable events:


        //Definition of a handler: Handler is part of the Android system's framework for managing threads.
        //A Handler object receives messages and runs code to handle the messages.
        //A handler allows us to run a chunk of code in a delay way like
        //every few seconds or every few minutes...etc
        //The handler controls the timing of some events or these chunks of code;
        //Because the Handler will run inside of a Runnable class, it is declared as final:

         final Handler handler = new Handler();

        //These events or chunks of code are named Runnable which are methods that can run
        //every second, or every minute

        Runnable run = new Runnable() {
            @Override
            public void run() {

                //Insert code to be run every second, where this refers to run() method and 1000 are milliseconds:

                Log.i("Runnable has run!","a second must have passed...");
                handler.postDelayed(this, 1000);

            }
        };

        //we can execute the Runnable run by calling post method:
        handler.post(run);

         */
    }
}