package interius.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import interius.resources.FontLoader;
import interius.tiles.TileMapLoader;

public class GameStateTerrain extends GameState implements InputProcessor{
    private SpriteBatch batch;
    private SpriteBatch batchGUI;
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    
    private TileMapLoader map;
    
    private float zoom = 1.5f;
    private float camSpeedX = 0f;
    private float camSpeedY = 0f;

    public GameStateTerrain(String worldPath)
    {
        map = new TileMapLoader(worldPath);
    }
    
    public void create() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        batchGUI = new SpriteBatch();

        camera = new OrthographicCamera(1280, 720);
        camera.setToOrtho(false);
        viewport = new ScreenViewport(camera);
        viewport.setUnitsPerPixel(1/zoom);

        map.create(camera);
    }
    
    public void update() {
        camera.translate(camSpeedX, camSpeedY);
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
        
        FontLoader.defaultFont.draw(batchGUI, new Integer(Gdx.graphics.getFramesPerSecond()).toString(), 
                2, 715);
        
        batchGUI.end();
    }

    public void dispose() {
        map.dispose();
    }

    public void resize(int w, int h) {
        viewport.update(w, h);
    }
    
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camSpeedX = -3f;
        if(keycode == Input.Keys.RIGHT)
            camSpeedX = 3f;
        if(keycode == Input.Keys.UP)
            camSpeedY = 3f;
        if(keycode == Input.Keys.DOWN)
            camSpeedY = -3f;
        return false;
    }
    
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camSpeedX = 0f;
        if(keycode == Input.Keys.RIGHT)
            camSpeedX = 0f;
        if(keycode == Input.Keys.UP)
            camSpeedY = 0f;
        if(keycode == Input.Keys.DOWN)
            camSpeedY = 0f;
        return false; 
    }
    
    public boolean keyTyped(char character) { return false; }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    public boolean mouseMoved(int screenX, int screenY) { return false; }
    public boolean scrolled(int amount) { return false; }

}
