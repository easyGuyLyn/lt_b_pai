package com.dawoo.lotterybox.view.view.sortcar;

import android.graphics.Bitmap;

public class SortCar {
    private Bitmap bitmap;
    private int index;
    private int num;
    private float speed;
    private float targeX;
    private float oldX;

    public boolean cheakMove() {
        if (this.targeX != this.oldX) {
            return true;
        }
        this.speed = 0.0f;
        return false;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public int getIndex() {
        return this.index;
    }

    public int getNum() {
        return this.num;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getTargeX() {
        return this.targeX;
    }

    public float getX() {
        return this.oldX;
    }

    public float move() {
        if ((Math.abs(this.targeX - this.oldX)) < Math.abs(speed)) {
            this.oldX = this.targeX;
            return this.targeX;
        } else {
            this.oldX += this.speed;
            return this.oldX;
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public void setTargeX(float f) {
        this.targeX = f;
        this.speed = (f - this.oldX) / 30.0f;
        cheakMove();
    }

    public void setX(float f) {
        this.oldX = f;
    }
}
