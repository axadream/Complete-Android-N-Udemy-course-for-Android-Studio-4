package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//declare textView variable to be accessible in all methods from the class:
    TextView textView;

    //these are the names of onClick methods to set the textView visible or invisible when the user is tapping the buttons:
public void  show(View view) {
    textView.setVisibility(View.VISIBLE);
}

public void hide(View view) {
    textView.setVisibility(View.INVISIBLE);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
    }
}