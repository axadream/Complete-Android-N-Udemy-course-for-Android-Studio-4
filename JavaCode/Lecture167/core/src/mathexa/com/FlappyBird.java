package mathexa.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

import javax.xml.soap.Text;

//THIS GAME IS OPTIMIZED FOR A SCREEN SIZE OF 1080 x 2340 PIXELS

public class FlappyBird extends ApplicationAdapter {
    SpriteBatch batch;
    //texture is an image in the game:
    Texture background;
    Texture birdText;
    //ShapeRenderer shapeRenderer;

    Texture gameover;

    Texture[] birds;
    int flapState = 0;
    float birdY = 0;
    float velocity = 0;
    //for the collision we will define a circle:
    Circle birdCircle;
    int score = 0;
    int scoringTube = 0;
    BitmapFont font;

    int gameState = 0;
    float gravity = 2;

    Texture topTube;
    Texture bottomTube;
    float gap = 1000;
    float maxTubeOffset;
    Random randomGenerator;
    float tubeVelocity = 4;
    int numberOfTubes = 4;
    float[] tubeX = new float[numberOfTubes];
    float[] tubeOffset = new float[numberOfTubes];
    float distanceBetweenTubes;
    Rectangle[] topTubeRectangles;
    Rectangle[] bottomTubeRectangles;


    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
        // shapeRenderer = new ShapeRenderer();
        birdCircle = new Circle();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);

        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");
        birdText = birds[0];

        //setting of the values for flapState with some delays:
        new com.badlogic.gdx.utils.Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                birdText = birds[flapState];
                flapState = flapState == 1 ? 0 : 1;
            }
        }, 0f, 0.2f);



        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        maxTubeOffset = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;
        randomGenerator = new Random();
        distanceBetweenTubes = Gdx.graphics.getWidth() * 3 / 4;
        topTubeRectangles = new Rectangle[numberOfTubes];
        bottomTubeRectangles = new Rectangle[numberOfTubes];

        startGame();
    }

    public void startGame(){
        birdY = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;

        //setup the tubes:
        for (int i = 0; i < numberOfTubes; i++) {
            tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
            tubeX[i] = Gdx.graphics.getWidth() / 2 - topTube.getWidth() / 2 + Gdx.graphics.getWidth() + i * distanceBetweenTubes;

            topTubeRectangles[i] = new Rectangle();
            bottomTubeRectangles[i] = new Rectangle();
        }
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (gameState == 1) {

            if (tubeX[scoringTube] < Gdx.graphics.getWidth() / 2) {
                score++;
                Gdx.app.log("Score", String.valueOf(score));
                if (scoringTube < numberOfTubes - 1) {
                    scoringTube++;
                } else {
                    scoringTube = 0;
                }
            }

            if (Gdx.input.justTouched()) {
                velocity = -30;
                //generates a random number between 0 and 1:

            }
            for (int i = 0; i < numberOfTubes; i++) {

                if (tubeX[i] < -topTube.getWidth()) {
                    tubeX[i] += numberOfTubes * distanceBetweenTubes;
                    tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);

                } else {
                    tubeX[i] -= tubeVelocity;

                }

                batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i], topTube.getWidth() + 10, topTube.getHeight() + 500);
                batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i], topTube.getWidth() + 10, topTube.getHeight() + 500);

                topTubeRectangles[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i], topTube.getWidth() + 10, topTube.getHeight() + 500);
                bottomTubeRectangles[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i], topTube.getWidth() + 10, topTube.getHeight() + 500);
            }

            if (birdY > 0) {

                //increase the speed based on gravity each time the loop render is called:
                velocity = velocity + gravity;
                //the vertical position of the bird decreases based on the value of the speed:
                birdY -= velocity;
            } else {
                gameState = 2;
            }
        } else if (gameState == 0) {

            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else if (gameState == 2) {
            batch.draw(gameover, Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2);

            if (Gdx.input.justTouched()) {
                gameState = 1;
                startGame();
                score=0;
                scoringTube=0;
                velocity=0;
            }

        }

        //starting displaying sprites:

        batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);

        font.draw(batch, String.valueOf(score), 100, 200);
        birdCircle.set(Gdx.graphics.getWidth() / 2, birdY + birds[flapState].getHeight() / 2, birds[flapState].getWidth() / 2);

        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(Color.RED);
        //shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);

        for (int i = 0; i < numberOfTubes; i++) {
            //shapeRenderer.rect(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i], topTube.getWidth() + 10, topTube.getHeight() + 500);
            //shapeRenderer.rect(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i], topTube.getWidth() + 10, topTube.getHeight() + 500);
            if (Intersector.overlaps(birdCircle, topTubeRectangles[i]) || Intersector.overlaps(birdCircle, bottomTubeRectangles[i])) {

                //Gdx.app.log("Collision", "Yes!");
                gameState = 2;
            }

        }

        batch.end();
        //shapeRenderer.end();
    }

}
