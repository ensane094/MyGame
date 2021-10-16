package gb.mygdx.game.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.math.Rect;
import gb.mygdx.game.math.Rnd;
import gb.mygdx.game.pull.EnemyPool;
import gb.mygdx.game.sprite.EnemyShip;

public class EnemyEmitter {
    private static final float GENERATE_INTERVAL = 4f;
    private static final float SMALL_ENEMY_HEIGHT = 0.1f;
    private static final float SMALL_ENEMY_BULLET_HEIGHT = 0.01f;
    private static final int SMALL_ENEMY_BULLET_DMG = 1;
    private static final float SMALL_ENEMY_RELOAD_INTERVAL = 3f;
    private static final int SMALL_ENEMY_HP = 1;

    private static final float MEDIUM_ENEMY_HEIGHT = 0.17f;
    private static final float MEDIUM_ENEMY_BULLET_HEIGHT = 0.03f;
    private static final int MEDIUM_ENEMY_BULLET_DMG = 5;
    private static final float MEDIUM_ENEMY_RELOAD_INTERVAL = 4f;
    private static final int MEDIUM_ENEMY_HP = 5;

    private static final float BIG_ENEMY_HEIGHT = 0.22f;
    private static final float BIG_ENEMY_BULLET_HEIGHT = 0.04f;
    private static final int BIG_ENEMY_BULLET_DMG = 10;
    private static final float BIG_ENEMY_RELOAD_INTERVAL = 1f;
    private static final int BIG_ENEMY_HP = 10;

    private float generateTimer;

    private final TextureRegion bulletRegion;
    private final Rect worldBounds;
    private final Sound bulletSound;
    private final EnemyPool enemyPool;

    private final TextureRegion[] smallEnemyRegions;
    private final TextureRegion[] mediumEnemyRegions;
    private final TextureRegion[] bigEnemyRegions;

    private final Vector2 smallEnemyV = new Vector2(0, -0.2f);
    private final Vector2 smallEnemyBulletV = new Vector2(0, -0.3f);

    private final Vector2 mediumEnemyV = new Vector2(0, -0.03f);
    private final Vector2 mediumEnemyBulletV = new Vector2(0, -0.25f);

    private final Vector2 bigEnemyV = new Vector2(0, -0.005f);
    private final Vector2 bigEnemyBulletV = new Vector2(0, -0.3f);

    public EnemyEmitter(Rect worldBounds, Sound bulletSound, EnemyPool enemyPool, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
        this.enemyPool = enemyPool;
        bulletRegion = atlas.findRegion("bulletEnemy");
        smallEnemyRegions = Regions.split(atlas.findRegion("enemy0"), 1, 2, 2);
        mediumEnemyRegions = Regions.split(atlas.findRegion("enemy1"), 1, 2, 2);
        bigEnemyRegions = Regions.split(atlas.findRegion("enemy2"), 1, 2, 2);
    }

    public void generateEnemy(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0;
            EnemyShip enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                enemy.set(
                        smallEnemyRegions,
                        smallEnemyV,
                        bulletRegion,
                        smallEnemyBulletV,
                        SMALL_ENEMY_BULLET_HEIGHT,
                        SMALL_ENEMY_BULLET_DMG,
                        bulletSound,
                        SMALL_ENEMY_RELOAD_INTERVAL,
                        SMALL_ENEMY_HEIGHT,
                        SMALL_ENEMY_HP
                );
            } else if (type < 0.8f) {
                enemy.set(
                        mediumEnemyRegions,
                        mediumEnemyV,
                        bulletRegion,
                        mediumEnemyBulletV,
                        MEDIUM_ENEMY_BULLET_HEIGHT,
                        MEDIUM_ENEMY_BULLET_DMG,
                        bulletSound,
                        MEDIUM_ENEMY_RELOAD_INTERVAL,
                        MEDIUM_ENEMY_HEIGHT,
                        MEDIUM_ENEMY_HP
                );
            } else {
                enemy.set(
                        bigEnemyRegions,
                        bigEnemyV,
                        bulletRegion,
                        bigEnemyBulletV,
                        BIG_ENEMY_BULLET_HEIGHT,
                        BIG_ENEMY_BULLET_DMG,
                        bulletSound,
                        BIG_ENEMY_RELOAD_INTERVAL,
                        BIG_ENEMY_HEIGHT,
                        BIG_ENEMY_HP
                );
            }
            float posX = Rnd.nextFloat(
                    worldBounds.getLeft() + enemy.getHalfWidth(),
                    worldBounds.getRight() - enemy.getHalfWidth()
            );
            enemy.setPos(posX,worldBounds.getTop());
        }
    }
}
