package gb.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.math.Rect;

public class Sprite extends Rect {
    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0] = region;
    }


    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }

    public void resize(Rect worldBounds) {

    }

    public void setHeightProportion (float height){
        setHeight(height);
        float aspect = regions[frame].getRegionWidth()/ (float) regions[frame].getRegionHeight();
        setWidth(height*aspect);
    }

    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) {

        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getAngle() {
        return angle;
    }

    public float getScale() {
        return scale;
    }

}
