package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //The code below is for sign up:
        /*
        ParseUser user = new ParseUser();
        user.setUsername("alexpintea");
        user.setPassword("myPass");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Sign Up", "Successful");
                } else {
                    Log.i("Sign Up", "Failed");
                }
            }
        });

         */
        //This code is for log in:
        /*
        ParseUser.logInInBackground("alexpintea", "asdf", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user!=null) {
                    Log.i("Login","Successful");
                } else {
                    Log.i("Login","Failed: "+e.toString());
                }
            }
        });

         */
//Check if a user is logged out or logged in:
        //if we run the logOut() method the user will be logged out:
        ParseUser.logOut();

        if (ParseUser.getCurrentUser()!=null) {
            Log.i("currentUser","User logged in "+ParseUser.getCurrentUser().getUsername());
        } else {
            Log.i("currentUser","User not logged in");
        }



        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}