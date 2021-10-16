package gb.mygdx.game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.Ship;
import gb.mygdx.game.math.Rect;
import gb.mygdx.game.pull.BulletPool;


public class MainShip extends Ship {
    private static final float RELOAD_INTERVAL = 0.35f;
    private static final float HEIGHT = 0.15f;
    private static final int INVALID_POINTER = -1;
    private final float BOTTOM_MARGIN = 0.05f;

    private boolean pressedLeft;
    private boolean pressedRight;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, Sound bulletSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV.set(0, 0.5f);
        bulletHeight = 0.01f;
        bulletDmg = 1;
        reloadInterval = RELOAD_INTERVAL;
        this.bulletSound = bulletSound;
        v0.set(0.35f, 0);
        hp = 100;
    }

    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if (getRight() > worldBounds.getRight()) {
            stopMoving(v);
        } else if (getLeft() < worldBounds.getLeft()) {
            stopMoving(v);
        }
        bulletPos.set(pos.x, pos.y + getHalfHeight());
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

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft(v);
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight(v);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight(v);
            } else {
                stopMoving(v);
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft(v);
            } else {
                stopMoving(v);
            }
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft(v);
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight(v);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight(v);
                } else {
                    stopMoving(v);
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft(v);
                } else {
                    stopMoving(v);
                }
                break;
        }
        return false;
    }

    @Override
    public boolean isBulletCollision(Bullet bullet) {
        return !(
                bullet.getRight() < getLeft()
                        ||
                        bullet.getLeft() > getRight()
                        ||
                        bullet.getBottom() > pos.y
                        ||
                        bullet.getTop() < getBottom()
        );
    }
}
