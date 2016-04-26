package com.cgossip.psyphysics.levels;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.cgossip.psyphysics.handlers.GameStateManager;
import com.cgossip.psyphysics.states.GameState;
import com.cgossip.psyphysics.view.Button;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by HP on 30-03-2016.
 */
public class selectLevel extends GameState implements InputProcessor,ApplicationListener, EventListener {

    public Button buttons[];
    private TextureAtlas textatlas;
    private TextButton button[];
    private Stage stage;
    private OrthographicCamera camera;
    private Texture background,buton,butoff,ontop;
    Texture close;
    Skin skin;
    BitmapFont font,gfont;
    public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
    public selectLevel(final GameStateManager gsm) {
        super(gsm);
        close=new Texture(Gdx.files.internal("dataa/undo.png"));
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        background =new Texture(Gdx.files.internal("newui/background1.png"));
        ontop=new Texture(Gdx.files.internal("newui/lvlbackground.png"));
        buton =new Texture(Gdx.files.internal("dataa/buttonon.png"));
        butoff =new Texture(Gdx.files.internal("dataa/buttonoff.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, com.cgossip.psyphysics.main.Game.V_WIDTH, com.cgossip.psyphysics.main.Game.V_HEIGHT);

        textatlas = new TextureAtlas("dataa/text.atlas");
       // textSkin= new Skin();
       // textSkin.addRegions(textatlas);
        font = new BitmapFont(Gdx.files.internal("newui/skikaria.fnt"),false);
        gfont = new BitmapFont(Gdx.files.internal("newui/grey.fnt"),false);//** font **//
       // font = TrueTypeFontFactory.createBitmapFont(Gdx.files.internal("dataa/crayon.ttf"), FONT_CHARACTERS, 12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.setColor(1f, 1f, 1f, 1f);
        font.getData().setScale((float)0.75,(float)0.75);
        gfont.getData().setScale((float)0.75,(float)0.75);

        /*
        FileWriter file = null;
        try {
            file = new FileWriter(String.valueOf(Gdx.files.internal("json/lvlstatus.json")));
            JSONObject objs = new JSONObject();
            try {
                objs.put("Current",1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            file.write(objs.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        FileHandle fileHandle = Gdx.files.internal("json/lvlstatus.json");
        String s = new String(fileHandle.readString());
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        int lvl=((Long)jsonObject.get("Current")).intValue();
        gsm.setTOTAlLEVEL(lvl);
      //  gsm.setCURLEVEL(lvl);


        stage = new Stage(new StretchViewport(800, 480));        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//
        Gdx.input.setCatchBackKey(true);

        TextButton.TextButtonStyle cstyle = new TextButton.TextButtonStyle();

        cstyle.font = font;

        TextButton buttonc = new TextButton("  ",cstyle);
        buttonc.setHeight(50);
        buttonc.setWidth(65);
        buttonc.setPosition(207, 32);
        buttonc.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.MENU);
            }
        });
        stage.addActor(buttonc);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("newui/lvlbuttonback.png"))));
        style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("newui/buttonpressed.png"))));
        style.font = font;

        TextButton.TextButtonStyle lstyle = new TextButton.TextButtonStyle(); //** Button properties **//
        lstyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("newui/lock.png"))));
        //lstyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("newui/buttonpressed.png"))));
        lstyle.font = gfont;

        button = new TextButton[15+1];
        buttons = new Button [15+1];
        int pre=140;
        int next=270;
        for(int i=0;i<15;i++){
            final int g=i;
            if (i>=lvl)
                button[i]= new TextButton(""+(i+1)+"",lstyle);
            else
                button[i]= new TextButton(""+(i+1)+"",style);
            button[i].setHeight(80); //** Button Height **//
            button[i].setWidth(85); //** Button Width **//
            button[i].setPosition(pre,next);
            if (i<lvl) {
                button[i].addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }

                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        GameStateManager.setCURLEVEL(g + 1);
                        gsm.setState(GameStateManager.PLAY);
                    }
                });
            }
            pre+=102;
            if((i+1)%5==0){
                next-=85;
                pre=140;
            }
            stage.addActor(button[i]);
        }
       /*
        buttons[1] = new Button(two);
        buttons[1].setPos(200, 300);
        buttons[2] = new Button(three);
        buttons[2].setPos(450, 300);
        buttons[3] = new Button(four);
        buttons[3].setPos(600 , 300);
        */

        //Gdx.input.setInputProcessor(this);

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, 800, 480);
        sb.draw(ontop,70,10);

        sb.end();
        sb.begin();

        stage.draw();

        sb.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void rendersb(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void create() {
        cam = new OrthographicCamera();
       // viewport = new FitViewport(Game.V_WIDTH,Game.V_HEIGHT,cam);
      //  viewport.apply();
    }

    @Override
    public void resize(int w, int h) {
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.SPACE){
            // Do your optional back button handling (show pause menu?)
            gsm.setState(GameStateManager.MENU);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean handle(Event event) {
      //  event.equals()
        return false;
    }
}
