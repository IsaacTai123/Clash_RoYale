package com.example.clashroyale.models;

import android.media.Image;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class CardProperties implements ICard {
    // Card Size, position, imgResource
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected int imageResId_ins;
    protected int imageResId_card;
    protected int imageId_card;
    protected ImageView imgUnit;

    // Card Properties
    protected String cardName;
    protected int hitPoints;
    protected int speed;
    protected int hitSpeed;
    protected String targets;
    protected String range;
    protected int damage;
    protected int range_damage;
    protected int area_damage;
    protected float projectile_range;
    protected float slowdown_duration;
    protected int elixir;
    protected String type;
    protected int Radius;
    protected int effective_duration;
    protected int crown_tower_damage;

     public void getDataFromJson_troop(String jsonData) {

        String[] value;
        String[] field = {"cardName", "HitPoints", "Speed", "HitSpeed", "Targets", "Range", "Damage", "Range Damage", "Area Damage", "Projectile Range", "slowdown Duration", "Elixir", "Type"};
        try {
            JSONObject obj = new JSONObject(jsonData);
            this.hitPoints = Integer.parseInt(obj.getJSONObject(cardName).getString(field[1]));
            this.speed = Integer.parseInt(obj.getJSONObject(cardName).getString(field[2]));
            this.hitSpeed = Integer.parseInt(obj.getJSONObject(cardName).getString(field[3]));
            this.targets = obj.getJSONObject(cardName).getString(field[4]);
            this.range = obj.getJSONObject(cardName).getString(field[5]);
            this.damage = Integer.parseInt(obj.getJSONObject(cardName).getString(field[6]));
            this.range_damage = Integer.parseInt(obj.getJSONObject(cardName).getString(field[7]));
            this.area_damage = Integer.parseInt(obj.getJSONObject(cardName).getString(field[8]));
            this.projectile_range = Integer.parseInt(obj.getJSONObject(cardName).getString(field[9]));
            this.slowdown_duration = Integer.parseInt(obj.getJSONObject(cardName).getString(field[10]));
            this.elixir = Integer.parseInt(obj.getJSONObject(cardName).getString(field[11]));
            this.type = obj.getJSONObject(cardName).getString(field[12]);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getDataFromJson_spell(String jsonData) {

        String[] value;
        String[] field = {"CardName", "Radius", "Effective Duration", "Area Damage", "Crown Tower Damage", "Elixir", "Type"};
        try {
            JSONObject obj = new JSONObject(jsonData);
            this.Radius = Integer.parseInt(obj.getJSONObject(cardName).getString(field[1]));
            this.effective_duration = Integer.parseInt(obj.getJSONObject(cardName).getString(field[2]));
            this.area_damage = Integer.parseInt(obj.getJSONObject(cardName).getString(field[3]));
            this.crown_tower_damage = Integer.parseInt(obj.getJSONObject(cardName).getString(field[4]));
            this.elixir = Integer.parseInt(obj.getJSONObject(cardName).getString(field[5]));
            this.type = String.valueOf(Integer.parseInt(obj.getJSONObject(cardName).getString(field[6])));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getImageResId_ins() {
        return imageResId_ins;
    }

    @Override
    public int getImageResId_card() {
        return imageResId_card;
    }

    @Override
    public int getImageId_card() {
        return imageId_card;
    }

    @Override
    public String getCardName() {
        return cardName;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getHitSpeed() {
        return hitSpeed;
    }

    @Override
    public String getTargets() {
        return targets;
    }

    @Override
    public String getRange() {
        return range;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getRange_damage() {
        return range_damage;
    }

    @Override
    public int getArea_Damage() {
        return area_damage;
    }

    @Override
    public float getProjectile_range() {
        return projectile_range;
    }

    @Override
    public float getSlowdown_duration() {
        return slowdown_duration;
    }

    @Override
    public int getElixir() {
        return elixir;
    }

    @Override
    public String getType() {
        return type;
    }


}
