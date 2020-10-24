package mathexa.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;

import javax.xml.soap.Text;

public class FlappyBird extends ApplicationAdapter {
    SpriteBatch batch;
    //texture is an image in the game:
    Texture background;
    Texture birdText;

    Texture[] birds;
    int flapState = 0;
    float birdY = 0;
    float velocity = 0;

    int gameState = 0;
    float gravity = 2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
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

        birdY = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;

    }

    @Override
    public void render() {

        if (gameState != 0) {

            if (Gdx.input.justTouched()) {
                velocity = -30;
            }

            if (birdY > 0 || velocity < 0) {

                //increase the speed based on gravity each time the loop render is called:
                velocity = velocity + gravity;
                //the vertical position of the bird decreases based on the value of the speed:
                birdY -= velocity;
            }
        } else {

            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        }

        //starting displaying sprites:
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);
        batch.end();
    }

}
