package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

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
import com.parse.SignUpCallback;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    public void redirectActivity() {
        if(ParseUser.getCurrentUser().getString("riderOrDriver").equals("rider")) {
            Intent intent=new Intent(getApplicationContext(),RiderActivity.class);
            startActivity(intent);
        } else {
            Intent intent=new Intent(getApplicationContext(),ViewRequestsActivity.class);
            startActivity(intent);
        }
    }

    public void getStarted(View view) {
        Switch userTypeSwitch = (Switch) findViewById(R.id.userTypeSwitch);
        Log.i("Switch value", String.valueOf(userTypeSwitch.isChecked()));
        String userType = "rider";
        if (userTypeSwitch.isChecked()) {
            userType = "driver";
        }
        ParseUser.getCurrentUser().put("riderOrDriver", userType);
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                redirectActivity();
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is to hide the text with the name of the project:
        getSupportActionBar().hide();
        if (ParseUser.getCurrentUser() == null) {
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Log.i("Info", "Anonymous login successful");
                    } else {
                        Log.i("Info", "Anonymous login failed");
                    }
                }
            });
        } else {
            if (ParseUser.getCurrentUser().get("riderOrDriver") != null) {
                Log.i("Info", "Redirecting as " + ParseUser.getCurrentUser().get("riderOrDriver"));
                redirectActivity();
            }
        }


        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        //Uncomment the code below if you cannot login to the Parse server due to this error: "invalid session token error":
        //ParseUser.getCurrentUser().logOut();
    }
}