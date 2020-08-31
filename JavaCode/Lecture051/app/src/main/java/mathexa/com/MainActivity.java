package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //our VideoView object has the id videoView and we are defined a variable
        //with exactly the same name videoView:
        VideoView videoView =(VideoView) findViewById(R.id.videoView);

        //we define the path of our video file:
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.demovideo);
        //we wil create a media controller variable and we will anchor it to our videoView object
        //the MediaController object contains the start/stop and back-forward buttons control
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();
    }

}