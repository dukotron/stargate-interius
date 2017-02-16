package interius.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateSpace extends GameState{
    Texture img;

    public void create() {
        img = new Texture("badlogic.jpg");
    }

    public void render(SpriteBatch batch) {
        batch.draw(img, 0, 0);
    }

    public void dispose() {
        img.dispose();
    }
}
