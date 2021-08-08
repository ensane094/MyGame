package gb.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.Sprite;
import gb.mygdx.game.math.Rect;

public class Logo extends Sprite {
    private final float V_LEN=0.64f;
    Vector2 v= new Vector2();
    Vector2 vectorTouch=new Vector2();
    public Logo(Texture texture) {
        super(new TextureRegion(texture));
    }
    public void resize(Rect worldBounds) {                  //устанавливаем размер картинки
        worldBounds.setHeight(0.20f);
        setHeightProportion(worldBounds.getHalfHeight());
        pos.set(worldBounds.pos);
    }
    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );if (vectorTouch.dst(pos) > V_LEN) {
                      pos.add(v);
                     } else {
                      pos.set(vectorTouch);
                      }
    }
    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) { //как и в прошлый раз, находим вектор скорости
        this.vectorTouch=vectorTouch;
        v.set(vectorTouch.cpy().sub(pos).scl(V_LEN));
        return false;
    }
}// как я понимаю, логику мы должны зашить в метод draw Но, если честно, я не сильно понимаю как...
//  Позиция это pos и к нему надо добавлять во время draw эту логику но он чёт не летает..

