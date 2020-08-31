package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //define a global variable of MediaPlayer type to be accessed from both methods playAudio(), pauseAudio(). This is used to play any media files in Android:
    MediaPlayer mplayer;

    /*
    Definition of the audio manager variable used to interact with the  audio system of Android;
    We need this variable because we want to make the link between the audio system of Android with our SeekBar object:
     */
    AudioManager audioManager;

    //this is the onclick method event for the Play button:
    public void playAudio(View view) {

        //start() method will play the audio file:
        mplayer.start();
    }

    //this is the onclick method event for the Pause button:
    public void pauseAudio(View view) {

        mplayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        The calling of create() method that contain the path to the audio file
        must be done from onCreate() method because we want that streaming of the audio to play in sequence when we use pause/play button, but
        not to replay it from the beginning:
*/
        mplayer = MediaPlayer.create(this, R.raw.laugh);

        //we are calling the audio system method through our variable of type audio manager:
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //we want to get the maximum volume of the Android system stored into a variable of type integer:
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //we want to get the current volume of the Android system stored into a variable of type integer:
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //we setup the path to our seekBar view object by defining a variable of SeekBar type:
        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);

        /*Now it is very easy to make the link between the maximum and current volume of Android system and
        the maximum and current values of our SeekBar object:
         */
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currentVolume);

        /*
        When we are working in a graphical user interface environment like Android, we are calling specific listeners.
        Generally speaking the definition of a listener is this one:
         A user interface listener is a method which is called when the user does something (eg, click a button)
         that the programmer has indicated they want to be notified of. The listener method is passed an event parameter
         that may tell something about what happened. Although events and listeners are used extensively in the user interface,
         they can have other sources (eg, a Timer) or in communicating between other parts of a program.
         Below we are calling a specific listener through our SeekBar object:
         */
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            /*
            The definition of an event:
            An event it's an object that is created when something changes within a graphical user interface.
            If a user clicks on a button, clicks on a combo box, or types characters into a text field, etc.,
            then an event triggers, creating the relevant event object.
            Our listener contains a bunch of event methods flagged with @Override, this means that
            we only need to add our code to the existing methods to do specific actions.
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                /* when we want to change the volume of the Android system through our SeekBar object, then we
                call the setStreamVolume() method:
                 */
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
/*
We define the second SeekBar object used to scrub forward and backward the media file define as mplayer.
Our object has the ID scrubber and must be declared as a final data type variable because is accessed from the internal method Timer()
 */
        final SeekBar scrubber = (SeekBar) findViewById(R.id.scrubber);
        /*the maximum value of our SeekBar object is linked to the maximum
        duration of the mplayer file:
         */
        scrubber.setMax(mplayer.getDuration());
        /*
        the Timer() method is used to control how fast is updated the SeekBar object position in relation with the media player position:
         */
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //the position of our scrubber object is linked to the current position of our media file:
                scrubber.setProgress(mplayer.getCurrentPosition());
            }
             //the parameter 0 means that we have no delays before our object position starts and the value 1000 represents the time in milliseconds between each movement of the SeekBAr object
        }, 0, 1000);

        /*
        We are calling a specific listener through our SeekBar object
        to update the audio position of our media file when we manipulate the SeekBar object position forward and backward:
         */
        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.i("Scrubber value ", String.valueOf(progress));
                mplayer.seekTo(progress);
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