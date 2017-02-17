package interius.game.states;

import interius.tiles.TileMapLoader;

public class GameStateTerrain extends GameState {
    private TileMapLoader map;

    public GameStateTerrain(String worldPath)
    {
        map = new TileMapLoader(worldPath);
    }
    
    public void create() {
        map.create();
    }

    public void render() {
        //map.render();
    }

    public void dispose() {
        map.dispose();
    }

    public void resize(int w, int h) {
        
    }
}
