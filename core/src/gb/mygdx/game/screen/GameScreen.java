package gb.mygdx.game.screen;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.basicScreen;
import gb.mygdx.game.math.Rect;
import gb.mygdx.game.sprite.Background;
import gb.mygdx.game.sprite.Logo;
import gb.mygdx.game.sprite.Star;

public class GameScreen extends basicScreen {
    private static final int STAR_COUNT = 84;
    private Texture wallpepper;
    Background background ;
    private TextureAtlas atlas;
    private Star[] stars;
    private Logo logo;
    private Texture ork;

    @Override
    public void show() {
        super.show();
        wallpepper = new Texture("textures/imperialFleet.jpg");
        background = new Background(wallpepper);
        atlas = new TextureAtlas("textures/menu_atlas.atlas");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        ork = new Texture("textures/ORK.png");
        logo = new Logo(ork);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars){
            star.resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        wallpepper.dispose();
        atlas.dispose();
        ork.dispose();
    }

    @Override
    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(Vector2 vectorTouch, int pointer, int button) {
        return false;
    }
    public void update(float delta){
        for(Star star : stars){
            star.update(delta);
        }
    }
    public void draw (){
        batch.begin();
        background.draw(batch);
        for(Star star: stars){
            star.draw(batch);
        }
        logo.draw(batch);
        batch.end();
    }
}
