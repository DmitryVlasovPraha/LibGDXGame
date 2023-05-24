package com.mygdx.drop.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.drop.zbhelpers.AssetLoader;

public class Bird {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation; // Для обработки поворота птицы
    private int width;
    private int height;

    private Circle boundingCircle;

    private boolean isAlive;

    /* Создание самой птицы
    * */
    public Bird(float x, float y, int width, int height) {

        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
        isAlive = true;

    }

    public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
    }

    /* Метод update, который будет запускать во время обновления GameWorld
    * */
    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }

        // проверяем потолок
        if (position.y < -13) {
            position.y = -13;
            velocity.y = 0;
        }

        position.add(velocity.cpy().scl(delta));

        // Устанавливаем центр круга (9, 6) по отношению к птице.
        // Устанавливаем радиус круга равным 6.5f;
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        // Повернем против часовой стрелки
        if (velocity.y < 0) {
            rotation -= 600 * delta;

            if (rotation < -20) {
                rotation = -20;
            }
        }

        // Повернем по часовой стрелке
        if (isFalling() || !isAlive) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }
        }
    }


    public boolean isAlive() {
        return isAlive;
    }

    /* onClick метод, который будет отрабатывать клики/касания по экрану
    *
    * */
    public void onClick() {
        if (isAlive) {
            AssetLoader.flap.play();
            velocity.y = -140;
        }
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return velocity.y > 70 || !isAlive;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public void die() {
        isAlive = false;
        velocity.y = 0;
    }


    public void decelerate() {
        // Нам надо чтобы птичка перестала падать вниз когда умерла
        acceleration.y = 0;
    }

}


