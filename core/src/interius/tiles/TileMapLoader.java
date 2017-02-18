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
    private boolean[][] collision;
    
    public TileMapLoader(String path) {
        map = new TmxMapLoader().load(path);
    }
    
    public void create(OrthographicCamera cam) {
        this.camera = cam;
        
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        tiledMapRenderer.setView(camera);
        collisionLayer = (TiledMapTileLayer) map.getLayers().get("collision");
        collision = new boolean[getHeight()][getWidth()];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                System.out.println(i + " " + j + " " + collisionLayer.getCell(j, i));
                if (collisionLayer.getCell(i, j) == null)
                    collision[i][j] = false;
                else
                    collision[i][j] = true;
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
    
    public boolean isCollideable(int height, int width) {
        return collision[height][width];
    }
    
    public void dispose() {
        map.dispose();
    }
}
