package interius.game.states;

public abstract class GameState {
    public abstract void create();
    public abstract void update();
    public abstract void render();
    public abstract void dispose();
    public abstract void resize(int w, int h);
}
