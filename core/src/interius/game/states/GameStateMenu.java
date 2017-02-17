package interius.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import interius.resources.FontLoader;

public class GameStateMenu extends GameState {
    private SpriteBatch batch;
    private Camera camera;
    private Viewport viewport;
    
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(1280, 720);
        viewport = new ScreenViewport(camera);
    }
    
    public void update() {
        
    }

    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.setTransformMatrix(camera.view);
        
        batch.begin();
        
        FontLoader.defaultFont.draw(batch, new Integer(Gdx.graphics.getFramesPerSecond()).toString(), 
                -1280/2 + 20, 720/2 - 20);
        
        batch.end();
    }

    public void dispose() {
        
    }
    
    public void resize(int w, int h) {
        viewport.update(w, h);
    }

}
