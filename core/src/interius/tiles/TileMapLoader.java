package interius.tiles;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TileMapLoader {
    private OrthographicCamera camera;
    
    private TiledMap map;
    private TiledMapRenderer tiledMapRenderer;
    
    public TileMapLoader(String path) {
        map = new TmxMapLoader().load(path);
    }
    
    public void create(OrthographicCamera cam) {
        this.camera = cam;
        
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        tiledMapRenderer.setView(camera);
    }
    
    // mozda u argumente gdje je kamera? pogledati kako radi libgdx kamera
    public void render() {
        tiledMapRenderer.render();
    }
    
    public void dispose() {
        map.dispose();
    }
}
