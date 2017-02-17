package interius.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import interius.resources.FontLoader;

public class GameStateSpace extends GameState{
    private Texture img;
    private Viewport viewport;
    private Camera camera;
    private SpriteBatch batch;

    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(800, 600);
        viewport = new ExtendViewport(1280, 720, camera);
        img = new Texture("badlogic.jpg");
        FontLoader.initialize();
    }

    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(img, 0, 0);
        System.out.print(Gdx.graphics.getWidth());
        FontLoader.defaultFont.draw(batch, new Integer(Gdx.graphics.getFramesPerSecond()).toString(),
                (-viewport.getScreenWidth() / 2) + 5, (viewport.getScreenHeight() / 2) - 5);
        batch.end();
    }

    public void dispose() {
        img.dispose();
        batch.dispose();
    }
    
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
