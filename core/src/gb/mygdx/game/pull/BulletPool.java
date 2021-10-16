package gb.mygdx.game.pull;

import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.Sprite;
import gb.mygdx.game.base.SpritesPool;
import gb.mygdx.game.sprite.Bullet;

public class BulletPool extends SpritesPool {
    @Override
    protected Sprite newSprite() {
        return new Bullet();
    }
}
