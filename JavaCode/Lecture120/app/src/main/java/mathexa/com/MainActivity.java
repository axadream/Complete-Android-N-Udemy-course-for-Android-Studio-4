package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        the statement <uses-permission android:name="android.permission.INTERNET"></uses-permission> is
        included into the AndroidManifest.xml file
         */

        WebView webView=(WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
       //Example 1:
        //webView.loadUrl("https://www.mathexa.com");
        //Example 2:
        webView.loadData("<html><body><h1>Hi there!</h1><p>This is my website.</p></body></html>","text/html","UTF-8");
    }
}