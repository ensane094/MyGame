package gb.mygdx.game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.math.Rect;
import gb.mygdx.game.pull.BulletPool;
import gb.mygdx.game.sprite.Bullet;

public abstract class Ship extends Sprite {
    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected final Vector2 v0;
    protected final Vector2 v;

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletPos;
    protected Vector2 bulletV;
    protected float bulletHeight;
    protected int bulletDmg;
    protected Sound bulletSound;

    protected float reloadTimer;
    protected float reloadInterval;
    protected int hp;

    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    public Ship() {
        v0 = new Vector2();
        v = new Vector2();
        bulletPos = new Vector2();
        bulletV = new Vector2();
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        v0 = new Vector2();
        v = new Vector2();
        bulletPos = new Vector2();
        bulletV = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL){
            frame = 0;
        }
    }

    private void shoot() {
        Bullet bullet = (Bullet) bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, bulletDmg);
        bulletSound.play(0.019f);
    }

    public void damage(int bulletDmg) {
        hp -= bulletDmg;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
        frame = 1;
        damageAnimateTimer = 0f;
    }

    public int getBulletDmg() {
        return bulletDmg;
    }

    public abstract boolean isBulletCollision(Bullet bullet);
}
