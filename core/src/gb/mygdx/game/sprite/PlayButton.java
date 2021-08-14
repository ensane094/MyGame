package gb.mygdx.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import gb.mygdx.game.base.BaseButton;
import gb.mygdx.game.math.Rect;
import gb.mygdx.game.screen.GameScreen;

public class PlayButton extends BaseButton {
    private final Game game;
    private static final float PADDING = 0.001f;

    public PlayButton(TextureAtlas atlas,Game game) {
        super(atlas.findRegion("playButton"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.32f);
        setBottom(worldBounds.getBottom()+PADDING);
        setLeft(worldBounds.getLeft()+PADDING);
    }

    @Override
    public void action() {
    game.setScreen(new GameScreen());
    }
}
