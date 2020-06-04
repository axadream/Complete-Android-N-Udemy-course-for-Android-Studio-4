package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view) {
        EditText dollarAmount=(EditText) findViewById(R.id.dollarAmountEditText);
        Double dollarAmountDouble=Double.parseDouble(dollarAmount.getText().toString());
        Double poundAmount=dollarAmountDouble*0.75;
        Toast.makeText(MainActivity.this, "£ "+String.format("%.2f",poundAmount),Toast.LENGTH_SHORT).show();
        Log.i("amount",dollarAmount.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}