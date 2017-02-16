package interius.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {
    public abstract void create();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
