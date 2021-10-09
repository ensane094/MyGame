package gb.mygdx.game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.Sprite;
import gb.mygdx.game.math.Rect;
import gb.mygdx.game.pull.BulletPool;


public class MainShip extends Sprite {
    private static final int INVALID_POINTER = -1;
    private final float V_LEN = 0.024f;
    private final float BOTTOM_MARGIN = 0.05f;
    Vector2 v;
    Vector2 vectorTouch;
    private boolean pressedLeft;
    private boolean pressedRight;
    private Rect worldBounds;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;
    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletPos;
    private Vector2 bulletV;
    private float bulletHeight;
    private int bulletDmg;
    private int delay=0;
    private Sound bulletSound;

    public MainShip(TextureAtlas atlas,BulletPool bulletPool,Sound bulletSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool=bulletPool;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV = new Vector2(0,0.5f);
        bulletHeight = 0.01f;
        bulletDmg = 10000;
        bulletPos = new Vector2();
        this.bulletSound = bulletSound;
    }

    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        v = new Vector2();
        vectorTouch = new Vector2();
        setHeightProportion(0.15f);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
        this.worldBounds = worldBounds;
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
        autoShoot();
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
            case Input.Keys.UP:
            case Input.Keys.W:
                shoot();
                break;
        }
        return false;
    }

    private void shoot (){
        Bullet bullet = (Bullet) bulletPool.obtain();
        bulletPos.set(pos.x,pos.y+getHalfHeight());
        bullet.set(this,bulletRegion,bulletPos,bulletV,bulletHeight,worldBounds,bulletDmg);
        bulletSound.play(0.019f);
    }

    private void autoShoot(){
      if(delay==0){
          shoot();
          delay++;
      }else if(delay==23) {
          delay=0;
      }else {
          delay++;
      }
    }
}
