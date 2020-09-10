package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Example 1:
       /* try {
            SQLiteDatabase myDatabase=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Alex',34)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Doru',35)");
            Cursor c=myDatabase.rawQuery("SELECT * FROM users",null);

            int nameIndex=c.getColumnIndex("name");
            int ageIndex=c.getColumnIndex("age");

            c.moveToFirst();
            while (c!=null) {
                Log.i("name",c.getString(nameIndex));
                Log.i("age",Integer.toString(c.getInt(ageIndex)));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/

        //Example 2:
        /*
        try {

            SQLiteDatabase eventsDB=this.openOrCreateDatabase("Events",MODE_PRIVATE,null);
            eventsDB.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR,year INT(4))");
            eventsDB.execSQL("INSERT INTO events (event,year) VALUES ('End Of WW2',1945)");
            eventsDB.execSQL("INSERT INTO events (event,year) VALUES ('Wham split up',1986)");

            Cursor c=eventsDB.rawQuery("SELECT * FROM events",null);
            int eventIndex=c.getColumnIndex("event");
            int yearIndex=c.getColumnIndex("year");
            c.moveToFirst();

            while (c!=null) {
                Log.i("Results - event",c.getString(eventIndex));
                Log.i("Results - year",Integer.toString(c.getInt(yearIndex)));
                c.moveToNext();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

         */
        //Advanced SQLite
        try {
            //SQL syntax examples:
            SQLiteDatabase eventsDB=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
            //eventsDB.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR,age INTEGER(3), id INTEGER PRIMARY KEY)");
           //eventsDB.execSQL("INSERT INTO newUsers (name,age) VALUES ('Kirsten',21)");
            //eventsDB.execSQL("INSERT INTO newUsers (name,age) VALUES ('Ralphie',1)");

            // Cursor c=eventsDB.rawQuery("SELECT * FROM users WHERE age<18",null);
            //Cursor c=eventsDB.rawQuery("SELECT * FROM users WHERE name='Kirsten'",null);
            //Cursor c=eventsDB.rawQuery("SELECT * FROM users WHERE name='Kirsten' AND age=21",null);
            //Cursor c=eventsDB.rawQuery("SELECT * FROM users WHERE name LIKE 'K%'",null);
            //Cursor c=eventsDB.rawQuery("SELECT * FROM users WHERE name LIKE '%i%' LIMIT 1",null);
            //eventsDB.execSQL("DELETE FROM users WHERE name='Kirsten' LIMIT 1");
            //eventsDB.execSQL("UPDATE users SET age=2 WHERE name='Ralphie'");
            eventsDB.execSQL("DELETE FROM newUsers WHERE id=1");
            Cursor c=eventsDB.rawQuery("SELECT * FROM newUsers",null);
            int nameIndex=c.getColumnIndex("name");
            int ageIndex=c.getColumnIndex("age");
            int idIndex=c.getColumnIndex("id");
            c.moveToFirst();

            while (c!=null) {
                Log.i("Results - id",Integer.toString(c.getInt(idIndex)));
                Log.i("Results - name",c.getString(nameIndex));
                Log.i("Results - age",Integer.toString(c.getInt(ageIndex)));
                c.moveToNext();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}