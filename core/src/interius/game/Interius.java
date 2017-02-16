package interius.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import interius.game.states.GameState;
import interius.game.states.GameStateSpace;
import interius.resources.FontLoader;

public class Interius extends ApplicationAdapter {

    private GameState currentState;
    private SpriteBatch batch;
    
    @Override
    public void create() {
        FontLoader.initialize();
        setState(new GameStateSpace());
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        
        currentState.render(batch);
        
        int y = Gdx.graphics.getHeight() - 10;
        FontLoader.defaultFont.draw(batch, new Integer(Gdx.graphics.getFramesPerSecond()).toString(), 5, y);
        
        batch.end();
    }

    @Override
    public void dispose() {
        currentState.dispose();
        batch.dispose();
    }
    
    public void setState(GameState s) {
        if(currentState != null)
            currentState.dispose();
        currentState = s;
        s.create();
    }
}
