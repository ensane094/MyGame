package gb.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import gb.mygdx.game.base.Sprite;
import gb.mygdx.game.math.*;

public class Star extends Sprite {
    private Vector2 v;
    private Rect worldBounds;
    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("asteroid"));
        v = new Vector2();
    }

    @Override
    public void update(float delta) {
    pos.mulAdd(v,delta);
    checkAndHandleBounds();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float height = Rnd.nextFloat(0.003f , 0.020f );
        setHeightProportion(height);
        float x= Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y= Rnd.nextFloat(worldBounds.getBottom(),worldBounds.getTop());
        pos.set(x,y);
        v.set(Rnd.nextFloat(-0.005f,0.005f),getHeight()* (-6.5f));
    }
    private void checkAndHandleBounds (){
        if(getRight()<worldBounds.getLeft()){
            setLeft(worldBounds.getRight());
        }
        if(getLeft()>worldBounds.getRight()){
            setRight(worldBounds.getLeft());
        }
        if (getTop()<worldBounds.getBottom()){
            setBottom(worldBounds.getTop());
        }
        if (getBottom()>worldBounds.getTop()){
            setTop(worldBounds.getBottom());
        }
    }

}
