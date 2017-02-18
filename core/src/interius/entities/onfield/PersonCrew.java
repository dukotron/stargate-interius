package interius.entities.onfield;

import com.badlogic.gdx.math.Vector2;

public abstract class PersonCrew extends Person {
    protected Vector2 targetPos;
    protected boolean isSelected;
    public abstract void walkTo(float x, float y);
    
    public void setSelected(boolean s){
        isSelected = s;
    }
    
    public boolean isSelected(){
        return isSelected;
    }
}
