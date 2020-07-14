package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    /*
    we will create variable of type ListView linked to the view object id:
     */
        ListView friendsListView=(ListView) findViewById(R.id.friendsListView);
   /*
   We will define a ArrayList variable of type String where will
   add some items to be displayed into our ListView. Here must be declared
   this sentence import static java.util.Arrays.asList;
    */
        final ArrayList myFriends=new ArrayList<String>(asList("Alex","Pintea","Mathexa","Sanobot"));

/*
we will define a variable derived from the ArrayAdapter class.The ArrayAdaptor class is used
for displaying our array of objects mentioned into the myFriends to a user interface like a specific list layout;
The keyword THIS is a reference variable in Java that refers to the current object of the current class ArrayAdapter and
simple_list_item_1 is a specific list layout and myFamily is the array of objects:
 */
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myFriends);
        /*
        From our ListView variable we are calling the setAdapter() method that will display
        the content of the myFriends array specified into our ArrayAdapter object:
         */

        friendsListView.setAdapter(arrayAdapter);
       /*From the same ListViewer variable we are calling a listener method to perform some specific actions when we click on
       any element from our ArrayList myFamily where are needed some parameters like an AdapterView<?> where ? means that it could represents
       any interactive user interface element like ListView, ArrayView...etc; the view variable is the user interface that is tapped (the tapped row);
       int position is the number of the row that was tapped (it starts from zero)
       long id is similar with position but in some cases can be used in a different way:
        */
        friendsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*
                in case we want to check if the tapped item return the name:
                 */
                Log.i("Person tapped: ", (String) myFriends.get(position));
                /*
                To popup a toast message for the tapped row from our list, we use the following code:
                 */
                Toast.makeText(getApplicationContext(),"Hello "+myFriends.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }
}