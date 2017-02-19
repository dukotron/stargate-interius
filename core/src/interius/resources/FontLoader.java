package interius.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontLoader {
    
    public static BitmapFont defaultFont;

    public static void initialize()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 14;
        defaultFont = generator.generateFont(parameter);
        
        generator.dispose();
    }
}
