package interius.entities.onfield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CrewScientist extends PersonCrew {

    public CrewScientist(float x, float y) {
        pos = new Vector2(x, y);
        targetPos = pos;
        this.create();
    }
    
    @Override
    public void create() {
        Texture texture = new Texture(Gdx.files.internal("blacksquare.png"));
        sprite = new Sprite(texture, 32, 32);
        boundingBox = new Rectangle(pos.x, pos.y, 32, 32);
    }

    @Override
    public void update() {
        Vector2 dir = targetPos.cpy();
        dir.sub(pos);
        if(dir.len() > speed) dir = dir.nor();
        pos = pos.add(dir);
        
        boundingBox.x = pos.x;
        boundingBox.y = pos.y;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void walkTo(float x, float y) {
        targetPos = new Vector2(x, y);
    }
}
