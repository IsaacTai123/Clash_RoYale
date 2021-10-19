package com.example.clashroyale.models;

import android.widget.ImageButton;
import android.widget.ImageView;

 public interface ICard {

    void getDataFromJson_troop(String jsonData);
    void getDataFromJson_spell(String jsonData);
    void setActivate(boolean bool);
    boolean getActivate();
    float getX();
    void setX(float x);
    float getY();
    void setY(float y);
    int getWidth();
    void setWidth(int width);
    int getHeight();
    void setHeight(int height);
    int getImageResId_ins();
    int getImageResId_card();
    int getImageId_card();
    String getCardName();
    ImageButton getImgButton();
    void setImgButton(ImageButton img);
    int getHitPoints();
    String getSpeed();
    float getHitSpeed();
    String getTargets();
    String getRange();
    int getDamage();
    int getRange_damage();
    int getArea_Damage();
    float getProjectile_range();
    float getSlowdown_duration();
    int getElixir();
    float getRadius();
    float getEffective_duration();
    int getCrown_tower_damage();
    String getType();


}

