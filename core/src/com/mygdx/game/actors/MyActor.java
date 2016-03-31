package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyActor extends Actor {
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    TextureRegion region;
    TextureRegion logo;
    Sprite sp;
    public MyActor () {
        TextureAtlas textatlas = new TextureAtlas("dataa/text.atlas");
         logo= new TextureRegion(textatlas.findRegion("backgroundtext"));
        sp=new Sprite(logo);
        //setPosition(-10,350);
        setBounds(900,350,logo.getRegionWidth(),logo.getRegionHeight());
       //setBounds(10,350,logo.getRegionWidth(),logo.getRegionHeight());
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
       // Color color = getColor();
      //  batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        //super.draw(batch,parentAlpha);
        batch.draw(sp,this.getX(),getY());
    }


}