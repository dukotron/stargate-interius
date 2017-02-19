package interius.entities.onfield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import interius.resources.ShaderLoader;

public abstract class PersonCrew extends Person {
    protected TextureRegion[] animationFrames;
    protected Animation<TextureRegion> animation;
    protected float animationTime;
    protected float currRotation;
    private float currSpeed;
    
    protected Vector2 targetPos;
    protected boolean isSelected;
    
    protected int[][] path;
    
    public PersonCrew(float x, float y) {
        pos = new Vector2(x, y);
        targetPos = pos;
        this.create();
    }
    
    @Override
    public void create() {
        Texture texture = new Texture(Gdx.files.internal("sprites/humanbase.png"));
        sprite = new Sprite(texture, 35, 35);
        sprite.setOrigin(sprite.getWidth()/2f, sprite.getHeight()/2f);
        
        TextureRegion[][] tmpFrames = TextureRegion.split(texture, 35, 35);
        animationFrames = new TextureRegion[4];
        
        for (int i = 0; i < 4; i++)
            animationFrames[i] = tmpFrames[0][i];
        
        animation = new Animation<TextureRegion>(1/4f, animationFrames);
        
        currRotation = sprite.getRotation() - 90;
        currSpeed = 0f;
    }
    @Override
    public void update() {
        
        float minDistToMove = 0.5f;
        
        Vector2 dir = targetPos.cpy();
        dir.sub(pos);
        if(dir.len() > minDistToMove) dir.nor();
        
        Vector2 dirScaled = dir.cpy();
        dirScaled.scl(currSpeed);

        float deltaAng = dir.angle() - currRotation;
        while(deltaAng > 180) deltaAng -= 360;
        while(deltaAng < -180) deltaAng += 360;
        deltaAng /= 20f;
        
        if(Math.abs(deltaAng) < 1f)
            pos.add(dirScaled);
        else currSpeed -= acceleration;
        
        if(dir.len() > minDistToMove) {
            currRotation += deltaAng;
            
            if(Math.abs(deltaAng) < 1) currSpeed += acceleration;
            if(currSpeed > speed) currSpeed = speed;
            
            sprite.setRotation(currRotation + 90);
            animationTime += Gdx.graphics.getDeltaTime();
        }
        else currSpeed -= acceleration;
        
        if(currSpeed < 0) currSpeed = 0f;
    }
    
    @Override
    public void render(SpriteBatch batch) {
        batch.setShader(null);
        
        sprite.setRegion(animation.getKeyFrame(animationTime, true));
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
        
        if (isSelected) {
            batch.setShader(ShaderLoader.shaderOutline);

            sprite.setRegion(animation.getKeyFrame(animationTime, true));
            sprite.setPosition(pos.x, pos.y);
            sprite.draw(batch);
    
            batch.setShader(null);
        }
    }
    
    
    @Override
    public void dispose() {

    }
    
    public void walkTo(float x, float y) {
        targetPos = new Vector2(x, y);
        //umm iz nekog razloga gleda od end prema start al ono samo obrnes pos i xy i dobis normalno
        path = AStar.ApplyAlgorithm(100, 100, (int) pos.x / 16, (int) pos.y / 16, (int) x / 16, (int) y / 16, new int[][] {{1, 3}});
    }
    
    public void setSelected(boolean s){
        isSelected = s;
    }
    
    public boolean isSelected(){
        return isSelected;
    }
}
