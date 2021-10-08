package gb.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import gb.mygdx.game.math.*;

public class basicScreen implements Screen, InputProcessor {
    protected SpriteBatch batch;
    private Rect screenBounds;
    private Rect worldBounds;
    private Rect glBounds;

    private Matrix4 worldToGl;
    private Matrix3 screenToWorld;
    private Vector2 vectorTouch;

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        screenBounds = new Rect();
        worldBounds = new Rect();
        glBounds = new Rect(0, 0, 1f, 1f);
        worldToGl = new Matrix4();
        screenToWorld= new Matrix3();
        vectorTouch = new Vector2();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 65f, 0.30f, 1);
    }

    @Override
    public void resize(int width, int height) {
        screenBounds.setSize(width, height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);

        float aspect = width / (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f*aspect);

        MatrixUtils.calcTransitionMatrix(worldToGl,worldBounds,glBounds);
        batch.setProjectionMatrix(worldToGl);
        resize(worldBounds);
        MatrixUtils.calcTransitionMatrix(screenToWorld,screenBounds,worldBounds);
    }
    public void resize(Rect worldBounds){
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        vectorTouch.set(screenX,Gdx.graphics.getHeight()-screenY).mul(screenToWorld);
        touchDown(vectorTouch,pointer,button);
        return false;
    }
    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) {
      return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        vectorTouch.set(screenX,Gdx.graphics.getHeight()-screenY).mul(screenToWorld);
        touchUp(vectorTouch,pointer,button);
        return false;
    }
    public boolean touchUp(Vector2 vectorTouch, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        vectorTouch.set(screenX,Gdx.graphics.getHeight()-screenY).mul(screenToWorld);
        touchDragged(vectorTouch,pointer);
        return false;
    }
    public boolean touchDragged(Vector2 vectorTouch, int pointer) {
       return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
