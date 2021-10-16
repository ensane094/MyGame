package gb.mygdx.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import gb.mygdx.game.base.Sprite;
import gb.mygdx.game.base.basicScreen;
import gb.mygdx.game.math.Rect;
import gb.mygdx.game.pull.BulletPool;
import gb.mygdx.game.pull.EnemyPool;
import gb.mygdx.game.sprite.Background;
import gb.mygdx.game.sprite.Bullet;
import gb.mygdx.game.sprite.EnemyShip;
import gb.mygdx.game.sprite.MainShip;
import gb.mygdx.game.sprite.Star;
import gb.mygdx.game.utils.EnemyEmitter;

public class GameScreen extends basicScreen {
    private boolean connectionCheck = false;
    private static final int STAR_COUNT = 84;
    private Texture wallpepper;
    Background background ;
    private TextureAtlas atlas;
    private TextureAtlas atlas1;
    private Star[] stars;
    private MainShip mainShip;
    private BulletPool bulletPool;
    private Sound laserSound;
    private Sound bulletSound;
    private Music music;
    private EnemyPool enemyPool;
    private EnemyEmitter enemyEmitter;

    @Override
    public void show() {
        super.show();
        wallpepper = new Texture("textures/imperialFleet.jpg");
        background = new Background(wallpepper);
        atlas1 = new TextureAtlas("textures/mainAtlas.tpack");
        atlas = new TextureAtlas("textures/menu_atlas.atlas");
        stars = new Star[STAR_COUNT];
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(worldBounds,bulletPool);
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        mainShip = new MainShip(atlas1,bulletPool,laserSound);
        enemyEmitter = new EnemyEmitter(worldBounds,bulletSound,enemyPool,atlas1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        checkCollisions();
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
        enemyPool.dispose();
        bulletSound.dispose();
        music.dispose();
        laserSound.dispose();
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
        enemyPool.updateActiveSprites(delta);
        enemyEmitter.generateEnemy(delta);
    }
    public void draw (){
        batch.begin();
        background.draw(batch);
        for(Star star: stars){
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);
        batch.end();
    }

    public void freeAllDestroyed (){
        enemyPool.freeAllDestroyedActiveSprites();
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

    private void checkCollisions(){
        List<EnemyShip> enemyShipList = enemyPool.getActiveSprites();
        for(EnemyShip enemyShip : enemyShipList){
            if(enemyShip.isDestroyed()){
                continue;
            }
            float minDist = enemyShip.getHalfWidth()+ mainShip.getHalfWidth();
            if(mainShip.pos.dst(enemyShip.pos)<minDist){
                mainShip.damage(enemyShip.getBulletDmg()*2);
                enemyShip.destroy();
            }
        }

        List<Bullet> bulletList = bulletPool.getActiveSprites();
        for(Bullet bullet : bulletList){
            if(bullet.isDestroyed()){
                continue;
            }
            for(EnemyShip enemyShip : enemyShipList){
                if(enemyShip.isDestroyed()|| bullet.getOwner()!=mainShip){
                    continue;
                }
                if (enemyShip.isBulletCollision(bullet)){
                    enemyShip.damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
            if(bullet.getOwner()!=mainShip && mainShip.isBulletCollision(bullet)){
                mainShip.damage(bullet.getDamage());
                bullet.destroy();
            }
        }
    }

}
