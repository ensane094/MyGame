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

    @Override
    public void show() {
        super.show();
        ork = new Texture("ORK.png");
        wallpepper = new Texture("imperialFleet.jpg");
        background = new Background(wallpepper);
        log = new Logo(ork);
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        log.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
     update(delta);
     draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        ork.dispose();
        wallpepper.dispose();
    }

    @Override
    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) {
        log.touchDown(vectorTouch,pointer,button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    private void update (float delta){
    log.update(delta);
    }
    private void draw(){
        batch.begin();
        background.draw(batch);
        log.draw(batch);
        batch.end();
    }
}