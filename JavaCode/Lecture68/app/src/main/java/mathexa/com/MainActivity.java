package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //we declare the variables of type Seek bar and of type TextView to be accessed from any method included into the class:
    SeekBar timerSeekBar;
    TextView timerTextView;
    //the button object used to control when the text is changed from Go to Stop:
    Button controllerButton;
    //based on this boolean variable we control when to reset the CountDownTimer constructor
    Boolean counterIsActive = false;
    //we can control the start and cancelling of CountDownTimer constructor:
    CountDownTimer countDownTimer;

//as the name suggests we define a method to specify what is happening when the timer is reset:

    public void resetTimer() {
        timerTextView.setText("0:00");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("GO");
        timerSeekBar.setEnabled(true);
        counterIsActive = false;
    }


    //we define a method for displaying the minutes and seconds through the text view object:
    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }


        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);

    }

//controlTimer() is the name of the onCLick method and this is executed when the button object is tapped:
    public void controlTimer(View view) {
        /*
        by default we decide that we can press the Start button and we can move forward and backward our Seek Bar, but when
         the GO button is pressed, the Seek Bar becomes inactive and the text of the button is changed from GO to STOP:
         */

        if (counterIsActive == false) {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("STOP");
            //we are calling the predefined constructor CountDownTimer:
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    timerTextView.setText("0:00");
                    Log.i("Finished", "Timer done!");

                    //we are calling the resetTimer() method when the count down timer is finished:
                    resetTimer();
                     /*
                we will create a variable of media player type to play a specific sound when the
                countdown timer is finished where instead of this (referred to CountDownTimer() constructor) we will use getApplicationContext()
                 */
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();


                }
            }.start();

        } else {
            resetTimer();
        }

        //in case we want to check that the onCLick method "controlTimer" is working when the button is pressed:
        Log.i("Button pressed", "Pressed");
    }


    /*
    To the existing onCreate() method we add some settings like links to the ID that belong to the created objects;
    we add some default values for SeekBAr object and we are calling a listener for our SeekBAr object to update in real time the values of the
    SeekBar object when user moves it backward or forward:
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //we specify the link of our variable of Seek Bar type to the ID of our seek bar object:
        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);

        //we define variables of Text View and Button types linked to the ID object:
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controllerButton = (Button) findViewById(R.id.controllerButton);
        //we define a maximum and progress value for our Seek Bar object:
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        //we are calling a listener for our Seek Bar object:
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //we are calling the updateTimer() method to display the minutes and seconds through the text view object when the user moves the seek bar object:
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}