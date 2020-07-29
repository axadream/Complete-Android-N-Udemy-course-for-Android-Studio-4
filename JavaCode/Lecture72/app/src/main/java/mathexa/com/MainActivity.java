package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //we declare the global variables of Button type and of ArrayList :
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    //variable used to store the correct result based on a random value:
    int locationOfCorrectAnswer;
    /*
    We inform the user about the score based on what button is tapped and for this
    we'll declare a score, numberOfQuestions and a pointsTextView variable:
     */
    int score = 0;
    int numberOfQuestions = 0;
    TextView pointsTextView;
    //we want to control when to be displayed the information to the user about the final result:
    TextView resultTextView;
    //this variable is used to display the random operation a+b:
    TextView sumTextView;
    //the timerTextView variable is used to display the 30s timer that is counted down
    TextView timerTextView;
    //we declare the play again button to control when this becomes invisible after it is tapped:
    Button playAgainButton;
    //we grouped most of the game view objects into a single relative layout and we want to control when these element to be visible:
    ConstraintLayout gameRelativeLayout;
    //using a boolean variable we can control then the buttons can be tapped or not:
    boolean activeButtons=true;

    public void playAgain(View view) {
        //when playAgain() method is loaded we need to reset few variables to their initial values:
        activeButtons=true;
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        //we are calling the generateQuestion() when the play again button is tapped:
        generateQuestion();

        /*We are creating the constructor CountDownTimer that will generate the countdown time
        displayed through the Text View timer: where 30100 represents the total of 30 seconds to be counted down and 1000 means 1 second the tick interval:
        For running the counter we need to add the start() method:
         */
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                //we want to display the value of milliseconds converted into seconds
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {


                //the play again button should be set to visible when the counter is finished:
                playAgainButton.setVisibility(View.VISIBLE);

                //we update the value of timerTextView to 0, otherwise the last value will remain 1:
                timerTextView.setText("0s");
                //we update the resultTextView variable with the text Done when the timer is finished:
                resultTextView.setText("Your score: "+Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
               // buttonGridLayout.setClickable(false);
                //once the count down is finished, we should set the activeButtons to false to not be possible to tape the button:
                activeButtons=false;

            }

        }.start();
    }

    //method to generate a new question each time is selected an answer:
    public void generateQuestion() {
        //create a Random object to get a random value:
        Random rand = new Random();
        //variables a and b will get values between 0 and 20 because 21 is exclusive:
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        //it is chosen randomly one button that will contain the correct text answer from the existing 4 buttons:
        locationOfCorrectAnswer = rand.nextInt(4);
       /*
       When the generateQuestion method is called again, we need to clear the previous generated values for the Array List answers, otherwise
       the previous generated values remain displayed:
        */
        answers.clear();

        //we define the logic how to be displayed the text answers for each of the 4 buttons:
        //we need also a specific variable that will store the wrong answers:
        int incorrectAnswer;
        //the for loop will run 4  times using the values from 0 to 3 because we have 4 buttons which will display a text answer:
        for (int i = 0; i < 4; i++) {
            /*
            as a convention we decide when to display the correct text answer into one of the buttons:
             when the value of i is equal with generated value of the variable locationOfCorrectAnswer:
            */

            if (i == locationOfCorrectAnswer) {
                /*
                Once the variable locationOfCorrectAnswer already got a random value, the code below is executed
                when there is a match between the value of i and the generated randomly value:
                 */

                answers.add(a + b);
            } else {
                //we initialize the variable used to display the wrong answers for the rest of the 3 buttons:
                incorrectAnswer = rand.nextInt(41);
                /*
                we want to exclude the possibility to display the correct answer to more than one button and for this
                the while loop is executed as long the condition is true:
                 */

                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
//using the conditions defined into the if loop, each button will get one result:
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    /*
    The onClick method is linked to the buttons and it is executed when the user is taping any of these.
    When a specific button is tapped a message is displayed through the resultTextView variable:
     */
    public void chooseAnswer(View view) {
        if (activeButtons) {
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
                score++;
                resultTextView.setText("Correct!");
                //in case we want to check what button returns the correct answer:
                Log.i("Correct", "correct");
            } else {
                resultTextView.setText("Wrong!");
            }
            numberOfQuestions++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            //we are calling generateQuestion() to generate a new question when an answer is selected:
            generateQuestion();

            //in case we want to check if the tapped button returns the correct configured tag:
            Log.i("Tag", (String) view.getTag());
        }
    }

    //we define the onCLick method named start() that belongs to the Button object:
    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    //in the predefined method onCreate we defined the link between our defined variables and the id of the objects:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton= (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout=(ConstraintLayout) findViewById(R.id.gameRelativeLayout);



    }
}