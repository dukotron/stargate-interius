package interius.entities.onfield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
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
        Texture texture = new Texture(Gdx.files.internal("human_base.png"));
        sprite = new Sprite(texture, 32, 32);
        boundingBox = new Rectangle(pos.x, pos.y, 32, 32);
        
        TextureRegion[][] tmpFrames = TextureRegion.split(texture, 35, 35);
        animationFrames = new TextureRegion[4];
        
        int index = 0;
        for (int i = 0; i < 4; i++){
            animationFrames[index++] = tmpFrames[0][i];
        }
        
        animation = new Animation<TextureRegion>(1f / 4f, animationFrames);
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
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(elapsedTime,true),pos.x, pos.y);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void walkTo(float x, float y) {
        targetPos = new Vector2(x, y);
    }
}
