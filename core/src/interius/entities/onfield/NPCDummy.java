package interius.entities.onfield;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NPCDummy extends PersonNPC {
    
    public NPCDummy(float x, float y) {
        super(x, y);
    }
    
    @Override
    public void create() {
        super.create();
    }

    @Override
    public void update() {
        super.update();
        //pos.x += 1f;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
