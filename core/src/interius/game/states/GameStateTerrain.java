package interius.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import interius.resources.FontLoader;
import interius.tiles.TileMapLoader;

public class GameStateTerrain extends GameState {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    
    private TileMapLoader map;

    public GameStateTerrain(String worldPath)
    {
        map = new TileMapLoader(worldPath);
    }
    
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(1280, 720);
        camera.setToOrtho(false, 1280, 720);
        viewport = new ScreenViewport(camera);
        
        map.create(camera);
    }

    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.setTransformMatrix(camera.view);
        
        batch.begin();
        map.render();
        
        FontLoader.defaultFont.draw(batch, new Integer(Gdx.graphics.getFramesPerSecond()).toString(), 
                -1280/2 + 20, 720/2 - 20);
        
        batch.end();
    }

    public void dispose() {
        map.dispose();
    }

    public void resize(int w, int h) {
        viewport.update(w, h);
    }
}
