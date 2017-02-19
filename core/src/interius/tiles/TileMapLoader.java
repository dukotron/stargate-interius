package interius.tiles;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import interius.entities.onfield.NPCDummy;
import interius.entities.onfield.PersonNPC;

public class TileMapLoader {
    private OrthographicCamera camera;
    
    private TiledMap map;
    private TiledMapTileLayer collisionLayer;
    private TiledMapRenderer tiledMapRenderer;
    private boolean[][] isCollidable;
    
    public TileMapLoader(String name) {
        map = new TmxMapLoader().load("maps/" + name);
    }
    
    public void create(OrthographicCamera cam) {
        this.camera = cam;
        
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        tiledMapRenderer.setView(camera);
        
        collisionLayer = (TiledMapTileLayer) map.getLayers().get("collision");
        isCollidable = new boolean[getHeight()][getWidth()];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (collisionLayer.getCell(x, y) == null)
                    isCollidable[x][y] = false;
                else
                    isCollidable[x][y] = true;
            }
        }
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
    
    public boolean isCollidable(int x, int y) {
        return isCollidable[x][y];
    }
    
    public void dispose() {
        map.dispose();
    }
}
