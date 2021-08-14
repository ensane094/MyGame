package gb.mygdx.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import gb.mygdx.game.base.BaseButton;
import gb.mygdx.game.math.Rect;

public class ExitButton extends BaseButton {
    TextureAtlas atlas;
    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("exitbutton1"));
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.32f);
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
