package gb.mygdx.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.basicScreen;
import gb.mygdx.game.math.Rect;
import gb.mygdx.game.sprite.Background;
import gb.mygdx.game.sprite.Logo;

public class menuScreen extends basicScreen {
    private Texture ork;
    private Texture wallpepper;
    Background background ;
    Logo log;
    private Vector2 v;
    private Vector2 position;
    private Vector2 destination;
    private final float V_LEN = 0.025f;

    @Override
    public void show() {
        super.show();
        ork = new Texture("ORK.png");
        wallpepper = new Texture("imperialFleet.jpg");
        background = new Background(wallpepper);
        log = new Logo(ork);
        v = new Vector2();
        position = new Vector2();
        destination = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        log.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        log.draw(batch);
        batch.end();
        if (destination.dst(position) > V_LEN) {
            position.add(v);
        } else {
            position.set(destination);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        ork.dispose();
        wallpepper.dispose();
    }

    @Override
    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) {
        return super.touchDown(vectorTouch, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
}