package gb.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.basicScreen;

public class menuScreen extends basicScreen {
    private Texture img;
    private Texture wallpepper;
    private Vector2 v;
    private Vector2 position;
    private Vector2 destination;
    private final float V_LEN = 0.025f;

    @Override
    public void show() {
        super.show();
        img = new Texture("ufthag.png");
        wallpepper = new Texture("imperialFleet.jpg");
        v = new Vector2();
        position = new Vector2();
        destination = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(wallpepper, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(img, position.x, position.y);
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
        img.dispose();
        wallpepper.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        destination.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.add(destination.cpy().sub(position)).scl(V_LEN);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
}
