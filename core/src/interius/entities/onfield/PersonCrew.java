package interius.entities.onfield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class PersonCrew extends Person {
    protected TextureRegion[] animationFrames;
    protected Animation<TextureRegion> animation;
    protected float animationTime;
    
    protected Vector2 targetPos;
    protected boolean isSelected;
    
    public PersonCrew(float x, float y) {
        pos = new Vector2(x, y);
        targetPos = pos;
        this.create();
    }
    
    @Override
    public void create() {
        Texture texture = new Texture(Gdx.files.internal("human_base.png"));
        sprite = new Sprite(texture, 35, 35);
        sprite.setOrigin(sprite.getWidth()/2f, sprite.getHeight()/2f);
        
        TextureRegion[][] tmpFrames = TextureRegion.split(texture, 35, 35);
        animationFrames = new TextureRegion[4];
        
        for (int i = 0; i < 4; i++)
            animationFrames[i] = tmpFrames[0][i];
        
        animation = new Animation<TextureRegion>(1/4f, animationFrames);
    }
    
    @Override
    public void update() {
        Vector2 dir = targetPos.cpy();
        dir.sub(pos);
        if(dir.len() > speed) dir.nor();
        dir.scl(speed);
        
        pos.add(dir);
        
        if(dir.len() > 0.3) {
            sprite.setRotation(dir.angle() + 90);
            animationTime += Gdx.graphics.getDeltaTime();
        }
    }
    
    @Override
    public void render(SpriteBatch batch) {
        sprite.setRegion(animation.getKeyFrame(animationTime, true));
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
    }
    
    @Override
    public void dispose() {

    }
    
    public void walkTo(float x, float y) {
        targetPos = new Vector2(x, y);
    }
    
    public void setSelected(boolean s){
        isSelected = s;
    }
    
    public boolean isSelected(){
        return isSelected;
    }
}
