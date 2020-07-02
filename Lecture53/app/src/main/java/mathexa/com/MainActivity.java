package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //define a global variable of MediaPlayer type to be accessed from both methods playAudio(), pauseAudio()
    MediaPlayer mplayer;

    public void playAudio(View view) {

        //start() method will play the audio file:
        mplayer.start();
    }

    public void pauseAudio(View view) {
        mplayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // the calling of create() method that contain the path to the audio file
        //must be done from onCreate() method because we want that pause/play button to continue
        //the audio sequence not to replay it from the beginning:

        mplayer = MediaPlayer.create(this, R.raw.laugh);
    }
}