package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--------Below code is to save data to the Parse Server:
        /*
        ParseObject score=new ParseObject("Score");
        score.put("username","alex");
        score.put("score",86);
        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null) {
                    Log.i("SaveInBackground","Successful");
                } else {
                    Log.i("SaveInBackground","Failed. Error: "+e.toString());
                }
            }
        });
        */

//----------below code is to retrieve data from the Parse Server:
        /*
        ParseQuery<ParseObject> query=ParseQuery.getQuery("Score");
        query.getInBackground("Xl4h3KzHGv", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e==null && object!=null) {
                    object.put("score",200);
                    object.saveInBackground();
                    Log.i("ObjectValue",object.getString("username"));
                    Log.i("ObjectValue",Integer.toString(object.getInt("score")));
                }
            }
        });
*/
        //Create Tweet class, username tweet, save on Parse, then query it, and update the tweet content:
        /*
        ParseObject tweet=new ParseObject(("Tweet"));
        tweet.put("username","tommy");
        tweet.put("tweet","Hey there!");

        tweet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
           if (e==null) {
               Log.i("Tweet","Successful");
           }     else {
               Log.i("Tweet","Failed");
           }
            }
        });

         */

ParseQuery<ParseObject> query=ParseQuery.getQuery("Tweet");
query.getInBackground("Eelwp458pN", new GetCallback<ParseObject>() {
    @Override
    public void done(ParseObject object, ParseException e) {
        if (e==null && object!=null) {
            Log.i("Tweet","Successful");
            object.put("tweet","Bye");
            object.saveInBackground();
        }     else {
            Log.i("Tweet","Failed");
        }
    }
});
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}