package interius.game;

import com.badlogic.gdx.ApplicationAdapter;

import interius.game.states.GameState;
import interius.game.states.GameStateSpace;

public class Interius extends ApplicationAdapter {

    GameState currentState;
    
    @Override
    public void create() {
        setState(new GameStateSpace());
    }

    @Override
    public void render() {
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
