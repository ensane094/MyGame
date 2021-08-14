package gb.mygdx.game;

import com.badlogic.gdx.Game;
import gb.mygdx.game.screen.menuScreen;

public class MyFirstGame extends Game {
	@Override
	public void create () {
		setScreen(new menuScreen(this));
	}
}
