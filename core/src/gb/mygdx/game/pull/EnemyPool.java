package gb.mygdx.game.pull;

import gb.mygdx.game.base.SpritesPool;
import gb.mygdx.game.math.Rect;
import gb.mygdx.game.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final Rect worldBounds;
    private final BulletPool bulletPool;
    private final ExplosionPool explosionPool;

    public EnemyPool(Rect worldBounds, BulletPool bulletPool, ExplosionPool explosionPool) {
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
    }

    @Override
    protected EnemyShip newSprite (){
        return new EnemyShip(worldBounds,bulletPool,explosionPool);
    }
}
