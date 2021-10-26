package gb.mygdx.game.screen;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.basicScreen;
import gb.mygdx.game.math.Rect;
import gb.mygdx.game.pull.BulletPool;
import gb.mygdx.game.sprite.Background;
import gb.mygdx.game.sprite.MainShip;
import gb.mygdx.game.sprite.Star;

public class GameScreen extends basicScreen {
    private static final int STAR_COUNT = 84;
    private Texture wallpepper;
    private Texture mainAtlas;
    Background background ;
    private TextureAtlas atlas;
    private TextureAtlas atlas1;
    private Star[] stars;
    private MainShip mainShip;
    private BulletPool bulletPool;

    @Override
    public void show() {
        super.show();
        mainAtlas = new Texture("textures/mainAtlas.png");
        wallpepper = new Texture("textures/imperialFleet.jpg");
        background = new Background(wallpepper);
        atlas1 = new TextureAtlas("textures/mainAtlas.tpack");
        atlas = new TextureAtlas("textures/menu_atlas.atlas");
        stars = new Star[STAR_COUNT];
        bulletPool = new BulletPool();
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        mainShip = new MainShip(atlas1,bulletPool);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        mainShip.resize(worldBounds);
        for (Star star : stars){
            star.resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        wallpepper.dispose();
        bulletPool.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) {
        mainShip.touchDown(vectorTouch,pointer,button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 vectorTouch, int pointer, int button) {
        mainShip.touchUp(vectorTouch,pointer,button);
        return false;
    }
    public void update(float delta){
        for(Star star : stars){
            star.update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveSprites(delta);
    }
    public void draw (){
        batch.begin();
        background.draw(batch);
        for(Star star: stars){
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }

    public void freeAllDestroyed (){
        bulletPool.freeAllDestroyedActiveSprites();
    }
    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }
    @Override
    public boolean keyUp (int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }
}
