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
    private float distance;
    private float speed;

    @Override
    public void show() {
        super.show();
        img = new Texture("ufthag.png");
        wallpepper = new Texture("imperialFleet.jpg");
        v = new Vector2(1, 1);
        position = new Vector2();
        destination = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        if (distance==0){
            v.x=0;
            v.y=0;
        }else   {
            speed=distance/=6000;
            v.x+=speed;
            v.y+=speed;
            position.add(v.nor());
        }
        batch.draw(wallpepper, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(img,position.x,position.y);
        distance= destination.len()-position.len();
        batch.end();

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
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        position.set(screenX, Gdx.graphics.getHeight() - screenY);
        return super.touchDragged(screenX, screenY, pointer);
    }
}
