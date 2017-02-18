package interius.entities.onfield;

import com.badlogic.gdx.math.Vector2;

public abstract class PersonCrew extends Person {
    protected Vector2 targetPos;
    public abstract void walkTo(float x, float y);
}
