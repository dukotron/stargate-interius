package interius.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ShaderLoader {
    public static ShaderProgram shaderOutline;
    
    public static void initialize() {
        String vertexShader = Gdx.files.internal("shaders/vertex.glsl").readString();
        String fragmentShader = Gdx.files.internal("shaders/fragment.glsl").readString();
        shaderOutline = new ShaderProgram(vertexShader, fragmentShader);
        
        shaderOutline.begin();
        shaderOutline.setUniformf("u_viewportInverse", new Vector2(1f / 512f, 1f / 512f));
        shaderOutline.setUniformf("u_offset", 1f);
        shaderOutline.setUniformf("u_step", Math.min(1f, 512f / 70f));
        shaderOutline.setUniformf("u_color", new Vector3(0, 0, 1f));
        shaderOutline.end();
    }

}
