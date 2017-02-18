package interius.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import interius.game.states.GameState;
import interius.game.states.GameStateTerrain;
import interius.resources.FontLoader;
import interius.resources.ShaderLoader;

public class Interius extends ApplicationAdapter  {

    private GameState currentState;
    
    @Override
    public void create() {
        FontLoader.initialize();
        ShaderLoader.initialize();
        
        setState(new GameStateTerrain("maptest.tmx"));
    }
    
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentState.update();
        currentState.render();
    }

    @Override
    public void dispose() {
        currentState.dispose();
    }
    
    public void resize(int w, int h) {
        currentState.resize(w, h);
    }
    
    public void setState(GameState s) {
        if(currentState != null)
            currentState.dispose();
        currentState = s;
        s.create();
    }
}
