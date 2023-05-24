package com.mygdx.drop.gameworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {

    private Rectangle rect = new Rectangle(0, 0, 17, 12);

    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        rect.x++;

        if (rect.x > 137) {
            rect.x = 0;
            //rect.y = 50;
        }
    }

    /* Метод для доступа к react
    * */
    public Rectangle getRect() {
        return rect;
    }
}