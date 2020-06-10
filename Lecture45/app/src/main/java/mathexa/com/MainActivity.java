package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    public void fade(View view) {
        ImageView bart=(ImageView)findViewById(R.id.bart);
        ImageView homer=(ImageView) findViewById(R.id.homer);
        //alpha() is for visibility of the image where 0 means it is invisible;
        //setDuration takes values in milliseconds:
        bart.animate().alpha(0f).setDuration(2000);
        homer.animate().alpha(1f).setDuration(2000);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}