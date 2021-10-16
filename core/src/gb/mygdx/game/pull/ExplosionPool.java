package gb.mygdx.game.pull;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import gb.mygdx.game.base.SpritesPool;
import gb.mygdx.game.sprite.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {
    private final TextureAtlas atlas;
    private final Sound explosionSound;

    @Override
    protected Explosion newSprite() {
        return new Explosion(atlas,explosionSound);
    }

    public ExplosionPool(TextureAtlas atlas, Sound explosionSound) {
        this.atlas = atlas;
        this.explosionSound = explosionSound;
    }
}
