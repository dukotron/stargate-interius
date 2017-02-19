package interius.game.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import interius.entities.onfield.*;
import interius.resources.FontLoader;
import interius.tiles.Planet;

public class GameStateTerrain extends GameState implements InputProcessor{
    private SpriteBatch batch;
    private SpriteBatch batchGUI;
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    
    private Planet planet;
    
    private float camSpeed = 4f;
    
    private ArrayList<PersonCrew> selectedUnits = new ArrayList<PersonCrew>();
    private ShapeRenderer dragRegionRenderer;
    private boolean mouseDown = false;
    private Rectangle mouseDragRegion = new Rectangle(0, 0, 0, 0);

    public GameStateTerrain(String worldName) {
        planet = new Planet(worldName);
    }
    
    public void create() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        batchGUI = new SpriteBatch();

        camera = new OrthographicCamera(1280, 720);
        camera.setToOrtho(false);
        viewport = new ScreenViewport(camera);
        
        dragRegionRenderer = new ShapeRenderer();

        planet.create(camera);
    }
    
    public void update() {
        float camSpeedX = 0f, camSpeedY = 0f;
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) camSpeedX =  camSpeed;
        if(Gdx.input.isKeyPressed(Keys.LEFT))  camSpeedX = -camSpeed;
        if(Gdx.input.isKeyPressed(Keys.UP))    camSpeedY =  camSpeed;
        if(Gdx.input.isKeyPressed(Keys.DOWN))  camSpeedY = -camSpeed;
        if(Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.LEFT)) camSpeedX = 0f;
        if(Gdx.input.isKeyPressed(Keys.UP)    && Gdx.input.isKeyPressed(Keys.DOWN)) camSpeedX = 0f;
        
        camera.translate(camSpeedX, camSpeedY);
        camera.update();
        
        mouseDown = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        if(mouseDown) {
            Vector3 size = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            size.x -= mouseDragRegion.x;
            size.y -= mouseDragRegion.y;
            mouseDragRegion.width = size.x;
            mouseDragRegion.height = size.y;
            
            selectedUnits = planet.getSelectedUnits(mouseDragRegion);
        }
        
        planet.update();
    }

    public void render() {
        batch.setProjectionMatrix(camera.combined);

        planet.renderTerrain();
        planet.renderPeople();
        
        batch.begin();

        if(mouseDown) {
            Gdx.gl.glEnable(GL30.GL_BLEND);
            Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
            dragRegionRenderer.setProjectionMatrix(camera.combined);
            dragRegionRenderer.begin(ShapeType.Filled);
            dragRegionRenderer.setColor(new Color(.1f, .1f, 1, 0.3f));
            dragRegionRenderer.rect(mouseDragRegion.x, mouseDragRegion.y, 0, 0, mouseDragRegion.width, mouseDragRegion.height, 1, 1, 0);
            dragRegionRenderer.end();
            Gdx.gl.glDisable(GL30.GL_BLEND);
        }
        
        batch.end();
        
        renderGUI();
    }
    
    private void renderGUI() {
        batchGUI.begin();
        
        FontLoader.defaultFont.draw(batchGUI, new Integer(Gdx.graphics.getFramesPerSecond()).toString(), 
                2, 715);
        
        batchGUI.end();
    }

    public void dispose() {
        planet.dispose();
    }

    public void resize(int w, int h) {
        viewport.update(w, h);
    }
    

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 pos = camera.unproject(new Vector3(screenX, screenY, 0));
        Vector2 worldPos = new Vector2(pos.x, pos.y);
        
        if(button == Input.Buttons.LEFT) {
            mouseDragRegion.x = worldPos.x;
            mouseDragRegion.y = worldPos.y;
            planet.selectNPC(worldPos.x, worldPos.y);
        }
        else if(button == Input.Buttons.RIGHT) {
            // maybe tell all units to go to a specific place?
            // tell them what action to do
            // if more than one, give some formation
            for(PersonCrew p : selectedUnits) {
                p.walkTo(worldPos.x - 16, worldPos.y - 16);
            }
        }
        return false;
    }
    
    public boolean scrolled(int amount) { 
        if(amount == 1)
            camera.zoom += .2f;
        else if(amount == -1)
             camera.zoom -= .2f;
        
        return false; 
    }
    
    public boolean keyDown(int keycode) { return false; }
    public boolean keyUp(int keycode) { return false; }
    public boolean keyTyped(char character) { return false; }
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    public boolean mouseMoved(int screenX, int screenY) { return false; }
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

}
