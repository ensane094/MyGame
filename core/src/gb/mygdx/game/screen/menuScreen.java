package gb.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.basicScreen;
import gb.mygdx.game.math.Rect;
import gb.mygdx.game.sprite.Background;
import gb.mygdx.game.sprite.ExitButton;
import gb.mygdx.game.sprite.PlayButton;
import gb.mygdx.game.sprite.Star;


public class menuScreen extends basicScreen {
    private static final int STAR_COUNT = 268;
    private ExitButton exitButton;
    private PlayButton playButton;
    private Texture wallpepper;
    Background background ;
    private TextureAtlas atlas1;
    private TextureAtlas atlas;
    private Star[] stars;
    private final Game game;
    private Music music;

    public menuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        wallpepper = new Texture("textures/imperialFleet.jpg");
        background = new Background(wallpepper);
        atlas1 = new TextureAtlas("textures/mainAtlas.tpack");
        atlas = new TextureAtlas("textures/menu_atlas.atlas");
        stars = new Star[STAR_COUNT];
        exitButton = new ExitButton(atlas);
        playButton = new PlayButton(atlas,game);
        for (int i = 0; i < stars.length; i++) {
            stars[i]= new Star(atlas);
        }
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/menuScreen.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars){
            star.resize(worldBounds);
        }
        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);
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
        wallpepper.dispose();
        atlas.dispose();
        music.dispose();
    }

    @Override
    public boolean touchDown(Vector2 vectorTouch, int pointer, int button) {
        playButton.touchDown(vectorTouch,pointer,button);
        exitButton.touchDown(vectorTouch,pointer,button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 vectorTouch, int pointer, int button) {
        playButton.touchUp(vectorTouch,pointer,button);
        exitButton.touchUp(vectorTouch,pointer,button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    private void update (float delta){
    for(Star star : stars){
        star.update(delta);
    }
    }
    private void draw(){
        batch.begin();
        background.draw(batch);
        for(Star star: stars){
            star.draw(batch);
        }
        exitButton.draw(batch);
        playButton.draw(batch);
        batch.end();
    }
}