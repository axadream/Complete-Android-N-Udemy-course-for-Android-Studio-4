package mathexa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    /*
     as a convention we define 0=yellow.png and 1=red.png;
     By default the first displayed picture will be the yellow one:
     */
    int activePlayer = 0;
    boolean gameIsActive = true;

    /*
    this is the first logic of the game: when the game is first started there is displayed an image as a grid with 9 positions: when
    one specific position is tapped, the game will display a specific picture;
    we consider another convention for the initial state of each position within the displayed grid: value 2 means that a specific position was not yet tapped;
    We will use an array type that's a data type that represents a collection of elements (values or variables);
    In our case we define an array of type int where each item has the default value 2; each item of the array represents a position within the grid that was not yet tapped:
    in total we will include 9 items because we are dealing with 9 positions, each tapped position will return a specific picture:
    */
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    /*
        This is the second logic of the game: we define 3 winning positions based on the criteria all 3 positions should display one of the images: red or yellow.
        These 3 winning positions could be located on the same row, on the same column or on the opposite sides of the grid.
        Totally we will have 8 definitions, each definition contains 3 winning positions
        We will have the variable of int array data type called "winningPositions".This is a multidimensional array: winningPositions[8][3] ->8 rows and 3 columns;
        A multidimensional array is arranged as an array of arrays i.e. each element of a multi-dimensional array is another array. The representation of the elements is in rows and columns.
        E.g. the following code:
        for (int[] checking:winningPositions) {
                    System.out.println(checking[0]);
                }
                where checking[0] will return the values: 0,3,6,0,1,2,0,2 that represent the index(position) 0 of each element from the two dimensional array.We have 8 elements, each element is also an array and
                the elements of that array are of int data type;
    */
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    /*declare the method name dropIn() ->this method is specified in the attribute onClick of the ImageView object
    //specify as parameter the object view of the class View; View is the base class for widgets;
    View is the Super Class of ImageView. you can draw all android control event like as (Button, EditText,TextView, CheckBox) in view
    */
    public void dropIn(View view) {

        //declare a variable of type ImageView controlled through the object view:
        ImageView counter = (ImageView) view;


    /*
    we want to store the ID value of each pressed ImageView object.
    We gave each image a numeric value stored into the ID tag;
    the getTag() method returns the ID as an object type that can be retrieved as a string through .toString() method;
    a string can be converted into an int data type by using Integer.parseInt() method:
    */
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        System.out.println(tappedCounter);

        /*
        we are checking if a specific position within the grid was tapped or not;
        use the if condition to check if a specific position of the predefined array was not tapped before; the index of the array is actually the value returned by the variable tappedCounter
         */
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            /*
            we define the relation between the tapped position and what picture to be displayed;
            the array's index will get the default value of the variable activePlayer
            which is the value 0, that means it will be displayed the picture yellow.png:
            */
            gameState[tappedCounter] = activePlayer;


            //Sets the vertical location of this view relative to its top position (minus means that the location is from bottom to the top)
            counter.setTranslationY(-1000);


            //loading of the images based on the actual value of the activePlayer:
            if (activePlayer == 0) {
                //load the image yellow.png from the path res/drawable
                counter.setImageResource(R.drawable.yellow);
                //next tapped position will display the image based on else statement:
                activePlayer = 1;
            } else {
                //load the image red.png from the path res/drawable
                counter.setImageResource(R.drawable.red);
                //next tapped position will display the image based on if statement:
                activePlayer = 0;
            }

            //the object is moved on vertical and rotated in about 300 milliseconds
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            //we are checking for all array elements from the multiple array winningPositions using for loop where we define the array int data type winningPosition;
            //Each array element contains a winning combination of 3 positions in the grid;

            /*
            E.g. {0,1,2} is one array element included into the multiple array winningPositions, in total we defined 8 array elements, this is the
            reason we use for loop to check each array element. We are doing that by defining an array int variable winningPosition that will go through all 8 array elements
            */

            for (int[] winningPosition : winningPositions) {
                /*
                Because the for loop will iterate all 8 array elements, let's try to understand the statements below:
                E.g. when first array element {0,1,2} is iterated, the winningPosition[0] returns the value from the first index, in this case it is the value 0;
                then winningPosition[1] returns the value from the second index, in this case it is the value 1;
                then winningPosition[2] returns the value from the third index, in this case it is the value 2.
                The next E.g. statement gameState[winningPosition[0]] could be equal with value 0 (yellow) or 1 (red) because gameState[tappedCounter] = activePlayer where tappedCounter
                in this case is the winningPosition[]. In conclusion value 0(yellow) or 1(red) will be linked to a specific position returned by winningPosition[].
                When all 3 values returned by gameState[winningPosition[]] are equal to 0 or 1, we have a winner.
                The gameState[winningPosition[0]] != 2 iterated into the for loop means that every position in the grid was tapped, so it's not in the initial state of 2 defined in the gameState[];
                */
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    /*
                    When above if condition is fulfilled, either red or yellow won and
                    gameIsActive should be setup as false because we will check its initial status in the else if condition to display the message for draw:
                    */

                    gameIsActive = false;

                    //by default we decide to display the winner message for red to exclude the additional using of if statement:
                    String winner = "Red";

                    //because the above if condition is based on gameState[winningPosition[0]]==gameState[winningPosition[1]]==gameState[winningPosition[2]], results
                    //that's enough to include an additional if statement  with gameState[winningPosition[0]]==0 to display the winner message for yellow:

                    if (gameState[winningPosition[0]] == 0) {

                        winner = "Yellow";

                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    // we could use also: winnerMessage.setText(String.format("%s has won!", winner));
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                    //this is just to check what values are returned in the Logcat from Android Studio when a specific color wins:
                    System.out.println("The value of gameState[winningPosition[0]] : winningPosition[0] is: " + gameState[winningPosition[0]] + " : " + winningPosition[0]);

                    /*
                    If you want to use break statement below, then in else statement replace if(gameIsOver && gameIsActive) just with if(gameIsOver) statement
                    The break statement means that when if condition is true:
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2)
                    then the break condition will jump out from the for loop , the else condition is no more executed:
                    */
                    //break;
                }

               /*
                Without a break statement at the final of the if condition, the else statement is executed,
                 even if the previous if statement is true (there is a winner)
                */
                else {
                    /*
                     We introduce a new boolean variable used to define an if statement when the message for draw should be displayed:
                    */

                    boolean gameIsOver = true;

                   /*
                    The draw message must be displayed only if all positions in the grid are tapped and there is no winner;
                    We use a for loop to check the status of each position of the grid:
                     */

                    for (int counterState : gameState) {
                        //if there is one or more positions untapped (value 2), then we change our boolean variable to false;
                        if (counterState == 2) {
                            gameIsOver = false;

                        }

                    }

                    /*
                    if our boolean variable is true (all the positions in the grid are tapped) and there is no winner (variable gameIsActive is true),
                    then the message for draw must be displayed:
                     */
                    if (gameIsOver && gameIsActive) {


                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }


                }
            }
        }
    }

    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        // Arrays.fill(gameState, 2);

        //the classic way is to use for loop:

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}