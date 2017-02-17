package interius.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import interius.resources.FontLoader;
import interius.tiles.TileMapLoader;

public class GameStateTerrain extends GameState {
    private SpriteBatch batch;
    private SpriteBatch batchGUI;
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    
    private TileMapLoader map;
    
    private float zoom = 1.5f;

    public GameStateTerrain(String worldPath)
    {
        map = new TileMapLoader(worldPath);
    }
    
    public void create() {
        batch = new SpriteBatch();
        batchGUI = new SpriteBatch();
        
        camera = new OrthographicCamera(1280, 720);
        camera.setToOrtho(false);
        viewport = new ScreenViewport(camera);
        viewport.setUnitsPerPixel(1/zoom);

        map.create(camera);
    }
    
    public void update() {
        camera.translate(1, 1);
        camera.update();
    }

    public void render() {
        
        batch.setProjectionMatrix(camera.combined);
        batch.setTransformMatrix(camera.view);

        map.render();
        renderElements();
        renderGUI();
    }
    
    private void renderElements()
    {
        batch.begin();
        
        batch.end();
    }
    
    private void renderGUI()
    {
        batchGUI.begin();
        
        FontLoader.defaultFont.draw(batchGUI, new Integer(Gdx.graphics.getFramesPerSecond()).toString(), 500, 500);
        
        batchGUI.end();
    }

    public void dispose() {
        map.dispose();
    }

    public void resize(int w, int h) {
        viewport.update(w, h);
    }
}
