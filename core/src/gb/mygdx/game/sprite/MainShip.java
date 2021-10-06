package gb.mygdx.game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.Sprite;
import gb.mygdx.game.math.Rect;

public class MainShip extends Sprite {
    private static final int INVALID_POINTER = -1;
    private final float V_LEN=0.024f;
    Vector2 v;
    Vector2 vectorTouch;
    private boolean pressedLeft;
    private boolean pressedRight;
    private Rect worldBounds;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;



    public MainShip(TextureRegion []regions) {
    super(regions[0]);
    }
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        v= new Vector2();
        vectorTouch=new Vector2();
        setHeightProportion(0.0993f);
        pos.set(0f,-0.40f);
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
    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < 0) {
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
    }return false;
}}
