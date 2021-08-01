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

    @Override
    public void show() {
        super.show();

        img = new Texture("ufthag.png");
        wallpepper = new Texture("imperialFleet.jpg");
        v = new Vector2(1, 1);
        position = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.draw(wallpepper, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(img,position.x,position.y);
        batch.end();
        position.add(v);
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        wallpepper.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        position.set(screenX, Gdx.graphics.getHeight() - screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        position.set(screenX, Gdx.graphics.getHeight() - screenY);
        return super.touchDragged(screenX, screenY, pointer);
    }
}
