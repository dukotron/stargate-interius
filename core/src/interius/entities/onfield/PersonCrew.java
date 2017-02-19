package interius.entities.onfield;

import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import interius.resources.ShaderLoader;
import interius.tiles.Planet;

public abstract class PersonCrew extends Person {
    protected TextureRegion[] animationFrames;
    protected Animation<TextureRegion> animation;
    protected float animationTime;
    protected float currRotation;
    private float currSpeed;
    
    protected Vector2 targetPos;
    protected boolean isSelected;
    
    protected Queue<int[]> path;
    protected Planet planet;
    
    public PersonCrew(float x, float y, Planet p) {
        this.planet = p;
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
        
        if (pos.dst(targetPos) <= 0.5f && pos != targetPos) {
            if (!path.isEmpty()) {
                int[] temp = path.poll();
                //System.out.println(temp[0] + "   " + temp[1]);
                targetPos = new Vector2(temp[0] * 16, temp[1] * 16);
            }
        }
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
        //umm iz nekog razloga gleda od end prema start al ono samo sam obrnuo pos i dest i dobis normalno
        path = AStar.ApplyAlgorithm(100, 100, (int) x / 16, (int) y / 16, (int) pos.x / 16, (int) pos.y / 16, planet.getCollision());
        int[] temp = path.poll();
        targetPos = new Vector2(temp[0] * 16, temp[1] * 16); //ovako bi trebalo getat values za pos, temp je jer bi dvaput removealo
        //targetPos = new Vector2(x, y);
    }
    
    public void setSelected(boolean s){
        isSelected = s;
    }
    
    public boolean isSelected(){
        return isSelected;
    }
}
