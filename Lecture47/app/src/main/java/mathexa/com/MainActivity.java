package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    public void fade(View view) {
        ImageView bart=(ImageView)findViewById(R.id.bart);

        //translationYBy moves the picture down
        //translationXBy(-1000f) moves the picture to the left
        //rotation(180f) rotates the picture 1800 degrees
        //scaleX(0.5f).scaleY(0.5f) shrinking of the picture on X and Y coordinates
        //setDuration takes values in milliseconds, 2000==2 seconds:
        bart.animate()
                .translationXBy(1000f)
                .translationYBy(1000f)
                .rotationBy(3600)
                .setDuration(2000);

        //ImageView homer=(ImageView) findViewById(R.id.homer);
        //homer.animate().alpha(1f).setDuration(2000);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bart=(ImageView)findViewById(R.id.bart);
        //when the app is lunched, setTranslationX(-1000f) and
        // setTranslationY(-1000f) moves picture up to the left
        bart.setTranslationX(-1000f);
        bart.setTranslationY(-1000f);

    }
}