package com.mygdx.drop;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.drop.screens.GameScreen;

public class ZBGame extends Game {

    @Override
    public void create() {
        Gdx.app.log("ZBGame", "created");

        setScreen(new GameScreen());
    }
}
