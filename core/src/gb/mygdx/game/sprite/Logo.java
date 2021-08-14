package gb.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.Sprite;
import gb.mygdx.game.math.Rect;

public class Logo extends Sprite {
    private final float V_LEN=0.024f;
    Vector2 v;
    Vector2 vectorTouch;
    public Logo(Texture texture) {
        super(new TextureRegion(texture));
    }
    public void resize(Rect worldBounds) {
        v= new Vector2();
        vectorTouch=new Vector2();
        setHeightProportion(0.0993f);
    }

    @Override
    public void update(float delta) {
        if (vectorTouch.dst(pos) > V_LEN) {
            pos.add(v);
        } else {
            pos.set(vectorTouch);
        }
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
    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) { //как и в прошлый раз, находим вектор скорости
        this.vectorTouch.set(vectorTouch);
        v.set(vectorTouch.cpy().sub(pos).scl(V_LEN));
        return false;
    }
}
