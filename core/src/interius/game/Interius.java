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
    
    @Override
    public void create() {
        setState(new GameStateSpace());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        currentState.render();
    }

    @Override
    public void dispose() {
        currentState.dispose();
    }
    
    public void setState(GameState s) {
        if(currentState != null)
            currentState.dispose();
        currentState = s;
        s.create();
    }
}
