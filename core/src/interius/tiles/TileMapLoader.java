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
    
    public void render() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }
    
    public int getWidth()
    {
        return (Integer) map.getProperties().get("width");   
    }
    
    public int getHeight()
    {
        return (Integer) map.getProperties().get("height");
    }
    
    public void dispose() {
        map.dispose();
    }
}
