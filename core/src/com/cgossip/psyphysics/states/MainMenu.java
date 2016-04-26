package com.cgossip.psyphysics.states;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cgossip.psyphysics.actors.MyActor;
import com.cgossip.psyphysics.handlers.GameStateManager;
import com.cgossip.psyphysics.view.Button;

/**
 * Created by Dell on 24-03-2016.
 */
public class MainMenu extends GameState implements InputProcessor,ApplicationListener {
    /*
            imageProvider = game.getImageProvider();
            imageProvider.load();
            backgroundImage = imageProvider.getBackgroundSpring();
            TextureRegion buttonBg = imageProvider.getButton();
            buttons = new Button [3];
            buttons[0] = new Button(buttonBg, imageProvider.getStart());
            buttons[1] = new Button(buttonBg, imageProvider.getKids());
            buttons[2] = new Button(buttonBg, imageProvider.getScores());
            helpButton = new Button(imageProvider.getHelp());

            soundButtons = new Button[2];
            soundButtons[0] = new Button(imageProvider.getSoundImage(false));
            soundButtons[1] = new Button(imageProvider.getSoundImage(true));

            camera = new OrthographicCamera();
            camera.setToOrtho(false, imageProvider.getScreenWidth(), imageProvider.getScreenHeight());
            batch = new SpriteBatch();

            logo = imageProvider.getLogo();
            logoX = (imageProvider.getScreenWidth() - logo.getRegionWidth())/2;
            logoY = (imageProvider.getScreenHeight() - logo.getRegionHeight() - 10)-50;

            int buttonMargin = 15;
            int buttonsHeight = 3*buttonMargin;
            for(int i=0; i<buttons.length; i++) {
                buttonsHeight += buttons[i].getRegionHeight();
            }

            for(int i=buttons.length-1;i>=0;i--) {
                int x, y;
                x = (imageProvider.getScreenWidth() - buttons[i].getRegionWidth())/2;
                if (i == buttons.length - 1) {
                y = ((imageProvider.getScreenHeight() - buttonsHeight) / 2) - 10;
                }
                else {
                    y = ((int) buttons[i+1].getPosY()) +
                        buttons[i+1].getRegionHeight() + buttonMargin;
                }
                buttons[i].setPos(x, y);
            }

            float x = imageProvider.getScreenWidth() - helpButton.getRegionWidth() - 10;
            float y = 10;
            helpButton.setPos(x, y);

            soundButtons[0].setPos(10, 10);
            soundButtons[1].setPos(10, 10);

            Gdx.input.setInputProcessor(this);
     */
    private Stage stage; //** stage holds the Button **//
    private BitmapFont font;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin,textSkin,dialogskin; //** images are used as skins of the button **//
    private TextButton button;
    TextureRegion logo,play,credit,exit;
    Table root;
    float stateTime;
    TextureRegion                   currentFrame,currentFrame2;           // #7
    TextureRegion[]                 drawFrames;
    TextureRegion[]                 drawFramesmenu;
    Texture background,wood,dialogback;
    Animation drawAnimation,drawAnimationm;
    private TextureAtlas textatlas,dialogatlas;
    private Viewport viewport;
    private OrthographicCamera camera;
    public Button buttons[],dbuttons[],ibut;
    TextureRegion texte,yesoff,yeson,nooff,noon,exitp,fb,gp,info,infop,ipanel,close;

    public MainMenu(final GameStateManager gsm) {
        super(gsm);
        background =new Texture(Gdx.files.internal("newui/background1.png"));
        dialogback=new Texture(Gdx.files.internal("dataa/dialogback.png"));
        buttonsAtlas = new TextureAtlas("dataa/button.pack"); //**button atlas image **//
        textatlas = new TextureAtlas("dataa/text.atlas");
        wood=new Texture(Gdx.files.internal("dataa/wood.png"));
        fb = new TextureRegion(new Texture(Gdx.files.internal("newui/facebook.png")));
        gp = new TextureRegion(new Texture(Gdx.files.internal("newui/googleplus.png")));
        info = new TextureRegion(new Texture(Gdx.files.internal("newui/info.png")));
        infop = new TextureRegion(new Texture(Gdx.files.internal("newui/infopressed.png")));
        ipanel = new TextureRegion(new Texture(Gdx.files.internal("newui/infopanel.png")));
        close = new TextureRegion(new Texture(Gdx.files.internal("newui/cross.png")));

        dialogatlas = new TextureAtlas("dataa/dialoga.atlas");
        dialogskin = new Skin();
        dialogskin.addRegions(dialogatlas);
        yesoff = new TextureRegion(new Texture(Gdx.files.internal("newui/yes.png")));
        yeson = new TextureRegion(new Texture(Gdx.files.internal("newui/yespressed.png")));
        nooff = new TextureRegion(new Texture(Gdx.files.internal("newui/no.png")));
        noon = new TextureRegion(new Texture(Gdx.files.internal("newui/nopressed.png")));
        texte = new TextureRegion(new Texture(Gdx.files.internal("newui/exitdialog.png")));

        ibut = new Button(close);
        ibut.setPos(200,400);

        dbuttons = new Button[3];
        dbuttons[0] = new Button(yesoff);
        dbuttons[0].setPos(450,160);
        dbuttons[1] = new Button(nooff);
        dbuttons[1].setPos(310,160);

        dialogback = new Texture(Gdx.files.internal("dataa/dialogback.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, com.cgossip.psyphysics.main.Game.V_WIDTH , com.cgossip.psyphysics.main.Game.V_HEIGHT );

        root = new Table();
        root.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        dialogskin=new Skin();
        dialogskin.addRegions(dialogatlas);

        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//

        textSkin= new Skin();
        textSkin.addRegions(textatlas);
        font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"),false); //** font **//

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");

       // TextButton.TextButtonStyle styleexit = new TextButton.TextButtonStyle(); //** Button properties **//
      //  style.up = buttonSkin.getDrawable("buttonOff");
       // style.down = buttonSkin.getDrawable("buttonOn");

        style.font = font;

        button = new TextButton("START", style);
        //** Button text and style **//
        button.setHeight(Gdx.graphics.getHeight() / 3); //** Button Height **//
        button.setWidth(Gdx.graphics.getWidth() / 4); //** Button Width **//

        button.setPosition(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2, Gdx.graphics.getHeight());

        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//


                // TODO Auto-generated method stub


                return true;

            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Rggggggeleased");

                ///and level
                gsm.setState(gsm.PLAY);

                dispose();

            }
        });



        MoveToAction moveAction = new MoveToAction();//Add dynamic movement effects to button
        moveAction.setPosition(10, 10);
        moveAction.setDuration(.5f);
       // button.addAction(moveAction);
        //stage.addActor(root);
       // root.debug();



        //Image imaget = new Image(textSkin.getDrawable("backgroundtext"));
        logo=new TextureRegion(new Texture(Gdx.files.internal("newui/logo.png")));
        play=new TextureRegion(new Texture(Gdx.files.internal("newui/play.png")));
        credit=new TextureRegion(new Texture(Gdx.files.internal("newui/credit.png")));
        exit=new TextureRegion(new Texture(Gdx.files.internal("newui/exit.png")));
        exitp=new TextureRegion(new Texture(Gdx.files.internal("newui/exitpressed.png")));

        buttons = new Button [6];
        buttons[0] = new Button(play);
        buttons[0].setPos(350,220);
        buttons[1] = new Button(credit);
        buttons[1].setPos(7,22);
        drawFrames = new TextureRegion[32];
        drawFramesmenu = new TextureRegion[14];
        buttons[2] = new Button(exit);
        buttons[2].setPos(583,22);
        buttons[3] = new Button(fb);
        buttons[3].setPos(630,400);
        buttons[4] = new Button(gp);
        buttons[4].setPos(710,400);
        buttons[5] = new Button(info);
        buttons[5].setPos(10,400);


        for(int i=0;i<=31;i++){
            drawFrames[i]=new TextureRegion(new Texture(Gdx.files.internal("animm/Untitled-"+i+".png")));
        }

        drawAnimation = new Animation(0.20f, drawFrames);

        // TextureRegionDrawable trd=new TextureRegionDrawable(new TextureRegion(new Texture(textSkin.get("backgroundtext"))));

        // Stack stack = new Stack();

        // stack.add(imaget);
// creating the group
        /*
        WidgetGroup group = new WidgetGroup();


        group.addActor(image);
        group.addActor(imaget);
        root.setBounds(0, 0, Gdx.graphics.getWidth(), 50);
        root.debug().left().top().add(group);
        */
        MoveToAction moveActions = new MoveToAction();
        moveActions.setPosition(10, 350);
        moveActions.setDuration(.2f);

        //image.setScaling(Scaling.fill);
        //root.add(imaget).height(200).fill(400,100);
        MyActor my=new MyActor();

        my.addAction(moveActions);
       // stage.addActor(my);
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }



    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }
    int flags=1;int xxx=350, xx=400,xf=0;
    int si=0;
    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();           // #15
        currentFrame = drawAnimation.getKeyFrame(stateTime, true);

        stage.act();
        sb.begin();

        cam.update();
        sb.setProjectionMatrix(cam.combined);

        sb.draw(background, 0, 0);
       // sb.draw(wood, 400, 0);
        sb.draw(logo,135,335);



        sb.draw(currentFrame,0,0);

       // if(flags==1)
        //sb.draw(currentFrame2,0,0);
        //else {
            //sb.draw(drawFramesmenu[13], 0, 0);


        //}
        buttons[0].draw(sb);
        buttons[1].draw(sb);
        buttons[2].draw(sb);
        buttons[3].draw(sb);
        buttons[4].draw(sb);
        buttons[5].draw(sb);

        if (si == 1){

            sb.draw(ipanel,20,225,250,203);
            buttons[5].setPos(10, 400);
            buttons[5].draw(sb);
            ibut.draw(sb);
        }

        //Create dialog box
        if (dialog == 1 ){

            //sb.draw(dialogback,0,0);
            sb.draw(texte,230,180);
            dbuttons[0].draw(sb);
            dbuttons[1].draw(sb);
        }

        //stage.draw();
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
        // TODO Auto-generated method stub
        background.dispose();
        wood.dispose();

    }

    @Override
    public void create() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(com.cgossip.psyphysics.main.Game.V_WIDTH, com.cgossip.psyphysics.main.Game.V_HEIGHT,cam);
        viewport.apply();
    }

    @Override
    public void resize(int w, int h) {
        // viewport.update(w,h);
    }
    int backf=0;
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            backf=backf^1;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if(character=='c'){
            backf=backf^1;
            return true;
        }

        return false;
    }

    int y=0,n=0;
    int dialog = 0;
    int playbb=0;
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        /*
        for(int i=0;i<buttons.length;i++) {
            if (buttons[i].isPressed(touchPos)) {
                if (i == 0) {
                    gsm.setState(GameStateManager.PLAY);
                }

                else if (i == 1) {
                    game.startGameKidsMode();
                    game.gotoGameScreen(null);
                }
                else if (i == 2) {
                    game.showHighscores();
                }
                break;

            }
        }
*/
        y = 0;
        n = 0;
        if (n == 0){
            dbuttons[1] = new Button(nooff);
            dbuttons[1].setPos(310,160);
        }
        Vector3 touchPos = new Vector3();
        camera.unproject(touchPos.set(screenX, screenY, 0));
        /*
        touchPos.x = touchPos.x * com.cgossip.psyphysics.handlers.B2DVars.PPM;
        touchPos.y = touchPos.y * com.cgossip.psyphysics.handlers.B2DVars.PPM;
        */
        System.out.println("Touch down");
        //  System.out.println(buttons[0].isPressed(touchPos));
        System.out.println("Touch "+touchPos.x + " " + touchPos.y);
        if(dbuttons[0].isPressed(touchPos) && dialog==1){
            System.out.println("Yes pressed");
            dbuttons[0] = new Button(yeson);
            dbuttons[0].setPos(450, 160);
            y=1;
            //gsm.setState(GameStateManager.PLAY);
            return true;
        }
        if(dbuttons[1].isPressed(touchPos) && dialog==1){
            System.out.println("No pressed");
            dbuttons[1] = new Button(noon);
            dbuttons[1].setPos(310,160);
            n=1;
            //gsm.setState(GameStateManager.MENU);
            return true;
        }
        if(buttons[0].isPressed(touchPos)){
            buttons[0]=new Button(new TextureRegion(new Texture(Gdx.files.internal("newui/playpressed.png"))));
            buttons[0].setPos(350,220);
            System.out.println("Hwre");
            //gsm.setState(GameStateManager.SELECTLEVEL);
            playbb=1;
        }
        if(buttons[1].isPressed(touchPos)){
            buttons[1]=new Button(new TextureRegion(new Texture(Gdx.files.internal("newui/creditpressed.png"))));
            buttons[1].setPos(7,22);
            System.out.println("Hwre");

            playbb=2;
        }
        if (buttons[2].isPressed(touchPos)){
            buttons[2] = new Button(exitp);
            buttons[2].setPos(583,22);
            System.out.println("Pressed exit");
            playbb=3;
        }
        if (buttons[5].isPressed(touchPos)){
           si=1;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("Comming");
        if (y == 1) {
            System.out.println("Yes pressed");
            Gdx.app.exit();
            return true;
        }
        if (n == 1){
            System.out.println("No pressed");
            dialog = 0;
            return true;
        }
        if(dialog==1){
            return true;
        }
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY, 0);
        camera.unproject(touchPos);
        System.out.println(touchPos.x + " " + touchPos.y);
        //  System.out.println(buttons[0].isPressed(touchPos));
        System.out.println(touchPos.x + " " + touchPos.y);
        if(playbb==1){

            gsm.setState(GameStateManager.SELECTLEVEL);

        }
        if(playbb==2){
            System.out.println("Hwre");
            gsm.setState(GameStateManager.CREDITS);

        }
        if (playbb==3){
            buttons[2] = new Button(exit);
            buttons[2].setPos(583,22);
            System.out.println("Exit dialog");
            dialog = 1;
        }
        playbb=0;
        if (ibut.isPressed(touchPos) && si==1){
            si=0;

            buttons[5].setPos(10, 400);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (dialog == 1)
            return false;
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
}