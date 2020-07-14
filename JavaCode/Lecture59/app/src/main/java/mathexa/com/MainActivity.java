package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

//we defined the same method buttonTapped for all buttons:
    public void buttonTapped(View view) {
        /*if we want to get the ID name of each button,
        we need first to get their numeric id when we press them:
         */
        int id=view.getId();
        /*we can get the id name by calling a specific method getResourceEntryName based
        on the numeric id of each button:
         */
        String ourId=view.getResources().getResourceEntryName(id);

        /*
        there is an option using getIdentifier() method to identify each media file name located
        in our raw folder using the name of our view objects. The identification of the media file name is done
        based on the name of our id button,  that means the names for both view objects and media files must be
        the same. This identification between view object and media file is stored as a numeric ID value into a new int variable:
         */
        int resourceID=getResources().getIdentifier(ourId,"raw","mathexa.com");
        /*
        We define the variable mplayer that will store the objects information of type MediaPlayer:
         */
        MediaPlayer mplayer=MediaPlayer.create(this,resourceID);
        //start() method will play any media file that have a numeric ID specified in our variable resourceID:
        mplayer.start();
        //this is a log information to check if our tapped button is identified correctly based on his name
        Log.i("button tapped", ourId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}