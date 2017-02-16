package interius.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public void render(SpriteBatch batch) {
        map.render(batch);
    }

    public void dispose() {
        map.dispose();
    }
}
