package com.example.clashroyale.models;

import android.media.Image;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.clashroyale.GlobalConfig;

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
    protected ImageButton imgButton;
    protected boolean activite;  //用來判斷該卡片是否達到聖水的條件

    // Card Properties
    protected String cardName;
    protected int hitPoints;
    protected String speed;
    protected float hitSpeed;
    protected String targets;
    protected String range;
    protected int damage;
    protected int range_damage;
    protected int area_damage;
    protected float projectile_range;
    protected float slowdown_duration;
    protected int elixir;
    protected String type;
    protected float radius;
    protected float effective_duration;
    protected int crown_tower_damage;

     public void getDataFromJson_troop(String jsonData) {

        String[] value;
        String[] field = {"cardName", "HitPoints", "Speed", "HitSpeed", "Targets", "Range", "Damage", "Range Damage", "Area Damage", "Projectile Range", "slowdown Duration", "Elixir", "Type"};
        try {
            JSONObject obj = new JSONObject(jsonData);
            this.hitPoints = Integer.parseInt(obj.getJSONObject(cardName).getString(field[1]));
            this.speed = obj.getJSONObject(cardName).getString(field[2]);
            this.hitSpeed = Float.parseFloat(obj.getJSONObject(cardName).getString(field[3]));
            this.targets = obj.getJSONObject(cardName).getString(field[4]);
            this.range = obj.getJSONObject(cardName).getString(field[5]);

            String damage = obj.getJSONObject(cardName).getString(field[6]);
            this.damage = (damage.equals("null")) ? 0 : Integer.parseInt(damage);

            String range_damage = obj.getJSONObject(cardName).getString(field[7]);
            this.range_damage = (range_damage.equals("null")) ? 0 : Integer.parseInt(range_damage);

            String area_damage = obj.getJSONObject(cardName).getString(field[8]);
            this.area_damage = (area_damage.equals("null")) ? 0 : Integer.parseInt(area_damage);

            String projectile_range = obj.getJSONObject(cardName).getString(field[8]);
            this.projectile_range = (projectile_range.equals("null")) ? 0 : Float.parseFloat(projectile_range);

            String slowdown_duration = obj.getJSONObject(cardName).getString(field[9]);
            this.slowdown_duration = (slowdown_duration.equals("null")) ? 0 : Float.parseFloat(slowdown_duration);
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

            String radius = obj.getJSONObject(cardName).getString(field[1]);
            this.radius = (radius.equals("null")) ? 0 : Float.parseFloat(radius);

            String effective_duration = obj.getJSONObject(cardName).getString(field[2]);
            this.effective_duration = (effective_duration.equals("null")) ? 0 : Float.parseFloat(effective_duration);

            this.area_damage = Integer.parseInt(obj.getJSONObject(cardName).getString(field[3]));
            this.crown_tower_damage = Integer.parseInt(obj.getJSONObject(cardName).getString(field[4]));
            this.elixir = Integer.parseInt(obj.getJSONObject(cardName).getString(field[5]));
            this.type =obj.getJSONObject(cardName).getString(field[6]);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setActivate(boolean bool) {
        this.activite = bool;
    }

    @Override
    public boolean getActivate() {
        return activite;
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
    public ImageButton getImgButton() {
        return imgButton;
    }

    @Override
    public void setImgButton(ImageButton img) {
        this.imgButton = img;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public String getSpeed() {
        return speed;
    }

    @Override
    public float getHitSpeed() {
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

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public float getEffective_duration() {
        return effective_duration;
    }

    @Override
    public int getCrown_tower_damage() {
        return crown_tower_damage;
    }


}
