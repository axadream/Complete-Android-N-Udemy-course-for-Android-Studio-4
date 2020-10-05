package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Below code is just to retrieve some data from the Parse Server:
        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.whereEqualTo("username","Tommy");
        query.setLimit(1);


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("findInBackground", "Retrieved " + objects.size() + " objects");
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            Log.i("findInBackgroundResult", Integer.toString(object.getInt("score")));
                        }
                    }
                }
            }
        });
*/


        //find anyone with a specific score and then add the value 50:
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.whereGreaterThan("score", 200);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null) {
                    for (ParseObject object : objects) {
                        object.put("score", object.getInt("score") + 50);
                        object.saveInBackground();
                    }
                }
            }
        });


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}