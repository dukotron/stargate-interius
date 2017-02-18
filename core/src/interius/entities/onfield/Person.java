package interius.entities.onfield;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Person {
    protected Sprite sprite;
    protected Vector2 pos;
    
    public Person() {}
    public abstract void create();
    public abstract void update();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}