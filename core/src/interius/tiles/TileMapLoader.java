package interius.tiles;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import interius.entities.onfield.NPCDummy;
import interius.entities.onfield.PersonNPC;

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
    
    public int getWidth() {
        return (Integer) map.getProperties().get("width");   
    }
    
    public int getHeight() {
        return (Integer) map.getProperties().get("height");
    }
    
    public ArrayList<PersonNPC> getNPCs() {
        ArrayList<PersonNPC> npcs = new ArrayList<PersonNPC>();
        
        for (MapObject mo : map.getLayers().get("objects").getObjects()) {
            if (mo instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) mo).getRectangle();
                npcs.add(new NPCDummy(rect.x, rect.y));
            }
        }

        return npcs;
    }
    
    public void dispose() {
        map.dispose();
    }
}
