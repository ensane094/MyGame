package gb.mygdx.game;

import com.badlogic.gdx.Game;

public class MyFirstGame extends Game {
	@Override
	public void create () {
		setScreen(new MenuScreen());
	}
	SpriteBatch batch;
	Texture img;
	Texture wallpapper;
	int x=0;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		wallpapper =new Texture("imperialFleet.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(wallpapper,0,0,712,486);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		wallpapper.dispose();
	}
}
