package interius.tiles;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import interius.entities.onfield.*;

public class Planet {
    private TileMapLoader map;
    
    private ArrayList<PersonCrew> crew;
    private ArrayList<NPCDummy> npcs;
    
    private OrthographicCamera camera;
    private SpriteBatch personsBatch;
    
    public Planet(String path) {
        map = new TileMapLoader(path);
        personsBatch = new SpriteBatch();
        
        crew = new ArrayList<PersonCrew>();
        npcs = new ArrayList<NPCDummy>();
        
        
        // @TODO - load crew which is on this planet and set their positions
        // also load npcs supposed to be on this planet
        npcs = map.getNPCS();
        crew.add(new CrewScientist(0, 20*16));
        crew.add(new CrewScientist(34, 20*16));
        crew.add(new CrewScientist(68, 20*16));
    }

    public void create(OrthographicCamera camera) {
        this.camera = camera;
        map.create(camera);
    }
    
    public void update() {
        for(Person p : npcs) p.update();
        for(Person p : crew) p.update();
    }
    
    public void renderTerrain() {
        map.render();
    }
    
    public void renderPeople() {
        personsBatch.setProjectionMatrix(camera.combined);
        personsBatch.begin();
        for(Person p : npcs) p.render(personsBatch);
        for(Person p : crew) p.render(personsBatch);
        personsBatch.end();
    }
    
    public ArrayList<PersonCrew> getCrew() {
        return crew;
    }
    
    public ArrayList<NPCDummy> getNPCS() {
        return npcs;
    }

    public void dispose()
    {
        map.dispose();
        npcs.clear();
        crew.clear();
    }
}