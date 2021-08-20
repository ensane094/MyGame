package gb.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.Sprite;
import gb.mygdx.game.math.Rect;

public class StarShip extends Sprite {

    private Vector2 v;
    private Rect worldBounds;
    public StarShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        v = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) {
        return super.touchDown(vectorTouch, pointer, button);
    }
}
