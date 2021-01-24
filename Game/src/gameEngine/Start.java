package gameEngine;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_C;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F12;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F2;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F3;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F4;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_H;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_T;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Y;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;
import java.util.ArrayList;
import static input.GetInput.*;
import  org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import Collisions.AABB;
import Collisions.CircleColision;
import Collisions.ColisionHandeler;
import Collisions.Collisions;
import Data.Constants;
import Data.Enemies;
import Data.Moves;
import Data.Pcs;
import Items.Inventory;
import Items.Items;
import  Scripter.Proccesor;
import ScripterCommands.DrawModel;
import ScripterCommands.DrawString;
import ScripterCommands.walkTo;
import animation.Animate;
import animation.AnimationHandler;
import animation.SpriteSheetLoader;
import audio.AudioInit;
import audio.Listener;
import audio.Sound;
import audio.Source;
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattleFormulas;
import battleClasses.BattlePlayerField;
import battleClasses.BattleSlot;
import battleClasses.Enemy;
import battleClasses.HpBar;
import events.Condition;
import events.EventActionDebugPrint;
import events.Events;
import events.Flag;
import events.FlagHandler;
import guis.BarElement;
import guis.CloseWindow;
import guis.DisplayPCInfo;
import guis.FullHeal;
import guis.GUIManeger;
import guis.GUINode;
import guis.GUIUseCurrentItemOnPC;
import guis.PickMove;
import guis.UIElement;
import guis.UIStringElement;
import guis.CallItemToBeUsed;
import input.BIndingNameParser;
import input.CharCallback;
import input.GetInput;
import input.InputHandler;
import rendering.MainBatchRender;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.ModelFramwork;
import rendering.OneTextureBatchedModel;
import rendering.Render;
import textrendering.Fontloader;
import textrendering.TextBuilder;
public class Start {
    
	
	public static Window w;
	public static final int width=640,height=480;
    public static int lowFPS=60000,HighFPs=0;
	
	
	public static int  location2,Projection2,UIProjection,RTS2,location,Projection,Color,RTS,frames=0,j=0,i=0,fps,gridx,gridy,Aframes,drawcalls=0,drawcallsFrame=0;
    public static byte dKeys,testKey;
    
   
    public static BIndingNameParser buttonNamses;
    public static boolean JustStarted=true;//,MoveInprogress=false,MoveCalled=false,TurnFinished=false,BattleEnded=false;
  //  public static TimedButton Button;
    public static ShaderProgram s,Batcheds;
    public static float scaleOfMapTiles=128,Rendercamx,Rendercamy;
    public static int amountWidth=Math.round((width/scaleOfMapTiles)),amountHeight=Math.round((height/scaleOfMapTiles));
    public static Camera cam,DebugCam;
    public static float screencoordx=0,screencoordy=0;
    public static InputHandler I;
    public static Fontloader font;
    public static int step=0;
    public static boolean canRender,overworld=true,test=false,testcol,circCol,GLDEBUG=false,LOG=true,DEBUGCOLISIONS=true,HideSprite=false,DebugPrint=true,Debugdraw=false,showFps=true,StateOfStartBOx=false,showDrawLines=true;
    public static double time,frameTime=0,lastFrame=0,DeltaTime;
    public static Texture tex,MAP,bg,playerTex,COLTEX,piont,piont2,col2,circleCol1,circleCol2,textbox,testSprite,HealthBarBackground,VectorTex;
    public static float x2,y2,camx,camy,x,y,Playerscale=64;
    public static Model background,player,textboxM,Arrow;
    public static OneTextureBatchedModel testM;
    public static TextBuilder textB,textA,textC,textD,text1,textDrawCalls,textR;
    public static Vector2f currentmovement,quarterStepVelocity=new Vector2f(),oldpos,direction,BattleBoxPosition,velocity;
   // public static BattleEntity p;
 
    public static CircleColision circle1, circle2;
    public static float PHP;
    public static float[] uvtextbox,uvArrow,vert; 
 
    public static int amountOfMoves,amountOfSPMoves,function;
    public static HpBar playersSPBAr;
    public static double startTime;
    public static Inventory playersInventory, enemyTestInventory;
    public static Moves currentlyUsedMove;
    public static AABB playerCol,Col,COl2;
    public static Animate a1;
    public static SpriteSheetLoader sloader;
    public static BattleEnemyField enemyField;
    public static BattlePlayerField playerField;
  //  public static BattleEntity currentEntity;
    public static Sound lazer,Heal,Select,Move,Back,NO,TimedBad;
    public static Source source1;
	public static  Source source;
    public static Fontloader aakar;
   // public static ArrayList<BattleEntity> turnOrder= new ArrayList<BattleEntity>();
    public static boolean facingLeft,running,ESCAPEBOXUP;
	private static WorldLoader map1;
	private static MapLoader currentMap;
   // public static Entity teste;
	public static boolean soundPlay=true;
    public static BattleEntity p;
    private static double BackSpaceHoldStart=0;
    private static GUIManeger maneger,PARTY_SELECT;
    private static GUINode rootForBattleScreen,root;
	public static GUINode specials;
	public static GUINode moves;
	public static AABB col4;
	public static Vector2f playerPostion=new Vector2f();
	private static boolean USEITEM_INITIALIZED=false;
	public static boolean STOP_PLAYER_MOVEMENT=false;
	public static Flag flag=new Flag(false);
	private static Events event;
    
	public static void main(String[] args) {
	
	AudioInit.InitAudio();
	Listener.InitListener();	
		
		
		DebugPrint("Starting.....");
	 startTime=Timer.getTIme();
	
	
	
	
	
	
	
	DebugPrint("intialzing vars....");
	vert=new float[]{
				-0.5f,+0.5f,
				0.5f,0.5f,
				0.5f,-0.5f,
				-0.5f,-0.5f
			};
		
	
		
	
		
		
		
		

		
		
		float[] uvBg={
				0,0,
				1,0,
				1,1,
				0,1
				
				};
		
	
	    float  Texwidth=192;
		float Texheight=192;		
		float  wi=71;
		float  h=28;
		float  Texx=0;
		float Texy=0;		
		
		 uvtextbox=new float[]{
				Texx/Texwidth,Texy/Texheight,
				(Texx+wi)/Texwidth,Texy/Texheight,
				(Texx+wi)/Texwidth,(Texy+h)/Texheight,
				Texx/Texwidth,(Texy+h)/Texheight
				};
		
		 float[] vertText={
				   -wi,+h,
					wi,h,
					wi,-h,
					-wi,-h};

	
		
		
		
		
			
	
	   Texwidth=138;
	   Texheight=138;		
	   wi=32;
	   h=46;
	   Texx=0;
	   Texy=0;	
	   float height2=h/Playerscale;
	   float width2=wi/Playerscale;     
	 
	   float[] vertPlayer={
			   -width2,+height2,
				width2,height2,
				width2,-height2,
				-width2,-height2


	   };
	   
	   

       float[] uvPlayer={
		Texx/Texwidth,Texy/Texheight,
		(Texx+wi)/Texwidth,Texy/Texheight,
		(Texx+wi)/Texwidth,(Texy+h)/Texheight,
		Texx/Texwidth,(Texy+h)/Texheight  };

       Texwidth=128;
	   Texheight=128;		
	   wi=62;
	   h=64;
	   Texx=66;
	   Texy=0;	
	    height2=h;
	    width2=wi;     
	 
	     uvArrow=new float[]{
	    		Texx/Texwidth,Texy/Texheight,
	    		(Texx+wi)/Texwidth,Texy/Texheight,
	    		(Texx+wi)/Texwidth,(Texy+h)/Texheight,
	    		Texx/Texwidth,(Texy+h)/Texheight  };



		//for(int i=0;i<8;i++) {
		///System.out.println(uv[i]);
		//} 
        DebugPrint("Creating window....");
		//make our window and attach shaders
		createWindow("shader");
		Batcheds=new ShaderProgram("BatchShader");
		
			Batcheds.makeAttribLocation("colors");
			Batcheds.makeAttribLocation("translation");
		DebugPrint("loading Sounds");
		lazer=new Sound("Lazer");
		Heal=new Sound("healing sound");
		Select=new Sound("select_GUI");
		Move=new Sound("move_GUI");
		Back=new Sound("Back_GUI");
		NO=new Sound("NO_GUI");
		TimedBad=new Sound("Timed_Button_BAD");
		

        source1=new Source(new Vector2f(0),1,1, 1,200, 0);
        source=new Source(new Vector2f(0), 1, 1, 0, 0,0);
		source.setSourceRelitive(true);	
         
		//source1.play(Select);
		
		DebugPrint("Making Textures....");
		//Define texturesbr.close();
		//font=new Fontloader("aakar",512);    
	
		tex=new Texture("newsprite");
		textbox=new Texture("textbox");
		bg= new Texture("testBackground");
		HealthBarBackground=new Texture("HealthBarBackground");
	    VectorTex=new Texture("Vector");
		
		sloader= new SpriteSheetLoader("playerSpriteSheet",138);//loads the sprite sheet info	
		
		
		aakar=new Fontloader("aakar",512);    
	    textB= new TextBuilder(aakar); 
		textA= new TextBuilder(aakar);
		textC= new TextBuilder(aakar);
		textD= new TextBuilder(aakar);
		text1= new TextBuilder(aakar);
		textR=new TextBuilder(aakar);
		textDrawCalls= new TextBuilder("aakar",512);
	//	TextBuilder textCircle= new TextBuilder("aakar",512);
		testM= new OneTextureBatchedModel();
		piont= new Texture("Point");

		piont2= new Texture("Point2");
		circleCol1=new Texture("Circle");
	//	MAP=new Texture("map3");
	    playerTex= new Texture("Spritesheets/playerSpriteSheet");
	    col2= new Texture("ColTex2");
	    COLTEX=new Texture("whitebox");
	    testSprite=new Texture("testSprites");
		//map=new Texture("map1"); 
		//Define models
	    DebugPrint("Making Models....");
		
		player= new Model(vertPlayer,uvPlayer);
	    background=new Model(vert,uvBg);
	    textboxM=new Model(vertText,uvtextbox);
	    Arrow=new Model(vert,uvArrow);
		
	
		DebugPrint("Making Shader Uniforms....");
		//make shader locations so we can use uniforms
		try {
			location=s.makeLocation("sampler");
			Projection=s.makeLocation("projection");
			RTS=s.makeLocation("rts");
			Color=s.makeLocation("color");
			
			
			location2=Start.Batcheds.makeLocation("sampler");
			Projection2=Batcheds.makeLocation("projection");
			RTS2=Batcheds.makeLocation("rts");
			UIProjection=Batcheds.makeLocation("UIProjection");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		DebugPrint("Loading world....");
		//map1=new WorldLoader("map3",true);
		
	    //currentMap=new MapLoader(map1.Getmap(),scaleOfMapTiles);
		MapFIle map=new MapFIle("Map1TEST");
		map.readMap();
	    currentMap=new MapLoader(tex,map,scaleOfMapTiles);
      
	
		DebugPrint("Settign Colisions....");
		playerCol=new AABB(new Vector2f(0,0),16,42,0);
		//playerCol=new AABB(new Vector2f(0,0),15,44,0,false);
		Col=new AABB(new Vector2f(0,0),32,32,0);
		AABB	Col3=new AABB(new Vector2f(100,0),16,32,0);
		col4=new AABB(new Vector2f(200,0),64,64,1);
        COl2=new AABB(new Vector2f(-64,1026-64),2048,64,0);
	    buttonNamses = new BIndingNameParser("GLFW");
		ColisionHandeler.addCollisions(new Collisions[] {playerCol,Col,COl2,Col3,col4});
	
		
		initializeFPS();
		x=100;
		y=100;	
		double ENDTime=Timer.getTIme();
		double timeTaken=ENDTime-startTime;
		DebugPrint("Tiem to load--- "+timeTaken+" seconds");
		
		DebugPrint("Starting Game loop.....");
		
		
		s.loadVec4(Color, new Vector4f(1,1,1,1));
     
		a1=new Animate(7,player,sloader,0,7);
		 
	
		  
	    GUINode bag=new GUINode(new GUINode[] {},"bag",2,2);
		   

			
			 playersInventory= new Inventory(new Items[] {Items.hpPotion,Items.SuperHpPotion,Items.spRestore},new int[] {1,3,2},bag);
			 enemyTestInventory = new Inventory(new Items[] {Items.hpPotion,Items.SuperHpPotion,Items.spRestore,Items.spSuperRestore},new int[] {3,1,4,2});
			 p=new BattleEntity("PC_1",player, playerTex,64*1.5f, new Vector2f(100,10),Pcs.C1.getAtk(),Pcs.C1.getDef(),Pcs.C1.getHp(),Pcs.C1.getSp(),Pcs.C1.getSpeed(),Pcs.C1.getMoves(),playersInventory);

			GUINode healn=new GUINode("heal",new FullHeal(p));		
		    GUINode stats=new GUINode("stats",new guis.DebugPrint("stats"));
			GUINode quit=new GUINode("Quit",new CloseWindow(w));
			 root=new GUINode(new GUINode[]{stats,bag,healn,quit},"root",1,4);
			
		
		 	
	    BattleEntity  p2=new BattleEntity("PC_2",player, playerTex,64*1.5f, new Vector2f(100,10),Pcs.C2.getAtk(),Pcs.C2.getDef(),Pcs.C2.getHp(),Pcs.C2.getSp(),Pcs.C2.getSpeed(),Pcs.C2.getMoves(),playersInventory);
        
	    Enemy enemy=new Enemy(new Vector2f(50,10),player, playerTex, 96,Enemies.E1.getName(),Enemies.E1.getAtk(),Enemies.E1.getDef(),Enemies.E1.getHp(),Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy2=new Enemy(new Vector2f(50,10),player, playerTex,96,"E2",Enemies.E1.getAtk(),Enemies.E1.getDef(),Enemies.E1.getHp(),Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy3=new Enemy(new Vector2f(50,10),player, playerTex,96,"E3",Enemies.E1.getAtk(),Enemies.E1.getDef(),Enemies.E1.getHp(),Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy4=new Enemy(new Vector2f(50,10),player, playerTex,96,"E4",Enemies.E1.getAtk(),Enemies.E1.getDef(),Enemies.E1.getHp(),Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy5=new Enemy(new Vector2f(50,10),player, playerTex, 96,Enemies.E1.getName(),Enemies.E1.getAtk(),Enemies.E1.getDef(),15,Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy6=new Enemy(new Vector2f(50,10),player, playerTex, 96,Enemies.E1.getName(),Enemies.E1.getAtk(),Enemies.E1.getDef(),15,Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		
		
		
	
		
		enemyField=new BattleEnemyField(new BattleSlot[] {new BattleSlot(new Vector2f(140,-20),enemy),new BattleSlot(new Vector2f(202,-88),enemy2),new BattleSlot(new Vector2f(202,10),enemy3),new BattleSlot(new Vector2f(260,-20),enemy4)});
	    playerField=new BattlePlayerField(new BattleSlot[] {new BattleSlot(new Vector2f(-90,40),p),new BattleSlot(new Vector2f(-100,-40),p2)});
		
		
		playersSPBAr=new HpBar(p.getMaxsp(),p.getSp(),new Vector2f(80,10),HealthBarBackground, COLTEX,Constants.BAR_COLOR_YELLOW,Constants.BAR_COLOR_YELLOW); 
		
	
		
		
		
		 event=new Events(new Condition[] {new Condition(col4.colide_flag,true),new Condition(flag,true)}, new EventActionDebugPrint("test_EVENT"));
		
		
		
		
		
				
	    rootForBattleScreen=new GUINode(new GUINode[] {quit},"root",1,1);
		
		
		 specials=new GUINode(new GUINode[]{},"specials",2,2);
         moves=new GUINode(new GUINode[]{},"moves",2,2);
         GUINode buy=new GUINode(new GUINode[] {},"Buy items",5,5);
         GUINode sell=new GUINode(new GUINode[] {},"Sell items",5,5);
         GUINode rootForTrigger=new GUINode(new GUINode[] {buy,sell},"root",1,3);
         
		 GUINode battleRoot=new GUINode(new GUINode[]{bag,moves,specials},"root",1,3);	
		
	
	    GUIManeger battleManeger=new GUIManeger(battleRoot,Constants.COL_COLOR_BLUE.add(0,0,0,50,new Vector4f()));
		maneger=new GUIManeger(root,Constants.COL_COLOR_BLUE);
		GUIManeger m=new GUIManeger(rootForTrigger,Constants.BLACK);
		PARTY_SELECT=new GUIManeger(new GUINode(new GUINode[] {},"root",2,2),Constants.COL_COLOR_BLUE);
		
		
		BattleSystem.INIT(battleManeger);	
		
		
		
//	AABB	Col5=new AABB(new Vector2f(400,0),32,32,1,new OpenGUI(new Vector2f(0,0), m,new Vector2f(100,80),0.2f,GLFW_KEY_P));
		
//	ColisionHandeler.addCollision(Col5);		
		//teste=new Entity(player,new Vector3f(0,0,200),0,64, playerTex);
		
		
		
		
		
		p.setHp(1);
		p.setSp(1);
		enemy.setHp(10);
		enemy2.setHp(10);
		enemy3.setHp(10);
		enemy4.setHp(10);
		enemy.setSp(1);
		enemy2.setSp(1);
		enemy3.setSp(1);
		enemy4.setSp(1);
		
		
		
		
		
	
	
		
//		new UIStringElement("Stats",new Vector2f(-17,35),.2f,Constants.BLACK,new guis.DebugPrint("stats")),
//		new UIStringElement("Bag",new Vector2f(-17,15),.2f,Constants.BLACK,1),
//		new UIStringElement("Heal",new Vector2f(-17,-5),.2f,Constants.BLACK,new FullHeal(p)),
//		new UIStringElement("Quit",new Vector2f(-17,-25),.2f,Constants.BLACK,new CloseWindow(w))
	
	    int operation=1;
	while(!w.isExited()) {
	
		fps();    
		
		BattleSystem.soundPlay=true;
		
		//----------------------GAME--LOOP------------------------------
			
		  InputHandler.EnableButtons(new int[] {GLFW_KEY_F1,GLFW.GLFW_KEY_X,GLFW.GLFW_KEY_Y,GLFW.GLFW_KEY_O});
  
		  FlagHandler.updateFlags();
		
	
	if(overworld==true) {	
		
	  if(!ESCAPEBOXUP) {    
		   Vector2f step=new Vector2f();
	
		  //step 1
		   ColisionHandeler.setColided(false);
           new Vector2f(x,y).add(quarterStepVelocity,step);
		    playerCol.setCenterPosition(step);//take step
		 
		   playerPostion=updateColisions(playerCol,step,oldpos, quarterStepVelocity, direction);//check colisions
		 
		    Start.text1.setString("bc: "+Math.round(playerCol.getPosBeforeCol().x)+", "+Math.round(playerCol.getPosBeforeCol().y));
			Start.text1.UIdrawString((640/2)+Start.screencoordx-100,(480/2)+Start.screencoordy-80,.2f);			  
		    
		    //step 2
			oldpos.set(playerPostion);
		    playerPostion.add(quarterStepVelocity,step);
		    playerCol.setCenterPosition(step);
			playerPostion=updateColisions(playerCol,step,oldpos, quarterStepVelocity, direction);
	
		   //step3
			oldpos.set(playerPostion);
		    playerPostion.add(quarterStepVelocity,step);
		    playerCol.setCenterPosition(step);
			playerPostion=updateColisions(playerCol,step,oldpos, quarterStepVelocity, direction);
	
		    //step4
			oldpos.set(playerPostion);
		    playerPostion.add(quarterStepVelocity,step);
		    playerCol.setCenterPosition(step);
			playerPostion=updateColisions(playerCol,step,oldpos, quarterStepVelocity, direction);
			
			
	
			  
			if(!ColisionHandeler.getColided()) {
				
				playerCol.setPosBeforeCol(playerPostion);
				
				
			
				
			}
			 
		
		    
			x=playerPostion.x; y=playerPostion.y;	
		    playerCol.setCenterPosition(playerPostion);	
			
		
			
		
			     
				    camx=-x;
					camy=-y;
							
		screencoordx=-camx;
	    screencoordy=-camy;
		
		  }	
	     cam.setPosition((new Vector2f(camx,camy)));
	  
	     Listener.ChangePosition(oldpos);

	      textB.setString("x="+x+" y="+y);
	      Vector2f camPos=cam.getPosition();
	     Rendercamx=-(camPos.x);
	     Rendercamy=-(camPos.y);
	      Vector2f newvec=currentMap.findPositionOnMap(Rendercamx,Rendercamy);
	      gridx= Math.round(newvec.x);gridy=Math.round(newvec.y);
	    drawmap(currentMap,gridx,gridy);		     
	  
	      textD.setString("circCol:"+circCol);
	      textC.setString("xmap="+gridx+" ymap="+gridy);
	      if(playersInventory.isUseItemCalled()) {
	    	  maneger.hide();
	    	  ;
	    	  if(!USEITEM_INITIALIZED) {

	    		  USEITEM_INIT(playerField,PARTY_SELECT);
	    	  }else {
	    		  drawPartyScreen(PARTY_SELECT, new Vector2f(screencoordx+(640/2)+50-maneger.getWidth(0.2f,new Vector2f(100,80)),screencoordy),new Vector2f(100,80),0.2f);
	    		  if(InputHandler.getStateofButton(GLFW_KEY_BACKSPACE)>0) {
	    			  playersInventory.setUseItemCalled(false);
	    			  maneger.open();
	    			  maneger.UpdateChanges();
	    			  PARTY_SELECT.close();
	    			  USEITEM_INITIALIZED=false;
	    		  }
	    	  }
		}else if(PARTY_SELECT.isOpen()){
			maneger.open();
			//maneger.UpdateChanges();
			USEITEM_INITIALIZED=false;
			PARTY_SELECT.close();
		
		}
	  
	      
	      if(HideSprite==false) {
	    	  RenderEntity playerE=a1.getE();	
	    	  playerE.setPosition(new Vector3f(x,y,100));
	    	  playerE.setSize(Playerscale);
	    	  playerE.setMirror(!facingLeft);
	    	  a1.draw();
	      }

		textB.UIDebugdrawString(screencoordx-300,screencoordy-220,.2f);
		
		textC.UIDebugdrawString(screencoordx+100,screencoordy-220,.2f);
      
		 ColisionHandeler.Debug();

}else {
	//---------------------battle loop---------------------
	

	BattleSystem.battleUpdate();
	
    screencoordx=0;
	screencoordy=0;
	
	
   






}

	 	  maneger.draw( new Vector2f(screencoordx+(640/2)+50-maneger.getWidth(0.2f,new Vector2f(100,80)),screencoordy),new Vector2f(100,80),0.2f);
	  textA.setString("FPS="+(int)fps+"\nH:"+HighFPs+" L:"+lowFPS);
		
	  if(showFps)
		  textA.UIdrawString((640/2)+screencoordx-100,(480/2)+screencoordy-20,.2f);
	


if(CharCallback.takeInput) {
	
   MainRenderHandler.addEntity(new RenderEntity(textboxM,new Vector3f(screencoordx,screencoordy,text1.getZ()-1),0,3, COLTEX,Constants.BLACK));
    text1.setString("TAKING INPUT");
	text1.drawString(screencoordx-75, screencoordy+70, .2f,Constants.RED);
    text1.setString(CharCallback.string);
    text1.drawString(screencoordx-100, screencoordy, .15f);
}






Proccesor.proccesCommands(time);
MainRenderHandler.SortEntities(); 
textDrawCalls.setString("\n"+"Entities "+MainRenderHandler.getAmountOfEntities());
textDrawCalls.UIDebugdrawString((640/2)+screencoordx-625,(480/2)+screencoordy-90,.25f);
MainRenderHandler.addToBatchedRender();

textDrawCalls.setString("Drawcalls(S:"+drawcalls+ "\nF:"+drawcallsFrame+")\nAnimations: "+AnimationHandler.amountInList()+"\nQuads"+MainBatchRender.getQuads());

textDrawCalls.UIDebugdrawString((640/2)+screencoordx-625,(480/2)+screencoordy-20,.25f);


MainRenderHandler.addToBatchedRender();
MainBatchRender.draw();
MainBatchRender.flushModel();
//MainRenderHandler.clear();
		    w.render();
		    w.clear();
		  
		 
			
		}
	//	}
//--------------------------------------------------------------	
		DebugPrint("Closing.....");
		DebugPrint("Highest fps="+HighFPs+" Lowest fps="+lowFPS);
		w.destroy();
		AudioInit.destroy();
		MainBatchRender.deleteResources();
		System.exit(0);
		
		
	}

	
		
	

	private static void createWindow(String Shader) {
		

		
		DebugPrint("Making Camera....");
			//set camera
			cam= new Camera(width,height);
		w=new Window(width,height,cam,"Game");
	
		
	
       //load our shaders 
	 DebugPrint("Making Shader Program....");
		s= new ShaderProgram(Shader);
		
		
		//bind shader
		s.bind();	
		
		
	}
	
	
	
	
	private static void USEITEM_INIT(BattlePlayerField field,GUIManeger maneger) {
	    maneger.reset();
		maneger.open();
		USEITEM_INITIALIZED=true;
	    boolean allDead=field.updateField();
	    if(!allDead) {
	    	BattleEntity entities[]=field.getAlivePCs();
	    	GUINode root=new GUINode(new GUINode[] {},"root",2,2);
	    	GUINode partyMembers[]=new GUINode[entities.length];
	    	for(int i=0;i<entities.length;i++) {
                BattleEntity e=entities[(entities.length-1)-i];
                String name=e.getName();
                partyMembers[i]=new GUINode(name, new GUIUseCurrentItemOnPC(e,Start.playersInventory),new DisplayPCInfo(e));

	    	}
	    	root.addChildren(partyMembers);
	    	maneger.setParrentNode(root);
	    	maneger.UpdateChanges();
	    	
	    }else {
	    	playersInventory.setUseItemCalled(false);
			Start.maneger.open();
			USEITEM_INITIALIZED=false;
			maneger.close();
	    }
	       	
	       	
	       	
	}
	private static void drawPartyScreen(GUIManeger maneger,Vector2f position,Vector2f padding,float sizeOfStrings) {
	       
		    
		      maneger.draw(position, padding, sizeOfStrings);	
		      
	}
	
	
	

	private static void Inputupdate() {
	
		
		w.update();//this is needed to actually poll events from keyboard 
		
//		boolean isPresent=GLFW.glfwJoystickPresent(GLFW.GLFW_JOYSTICK_1);
//		
//			DebugPrint(""+isPresent);
//		
//		    	
		
		    	InputHandler.EnableButtons(new int[] {GLFW.GLFW_KEY_I,GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_ENTER,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
		    if(CharCallback.takeInput) {
		    	InputHandler.EnableButton(GLFW_KEY_BACKSPACE);
		    }    
		maneger.InputUpdate();
		PARTY_SELECT.InputUpdate();
	    BattleSystem.updateInput();

	   
	    int UP=InputHandler.getStateofButton(GLFW_KEY_UP),DOWN=InputHandler.getStateofButton(GLFW_KEY_DOWN),
	    LEFT=InputHandler.getStateofButton(GLFW_KEY_LEFT),RIGHT=InputHandler.getStateofButton(GLFW_KEY_RIGHT),
		I=InputHandler.getStateofButton(GLFW.GLFW_KEY_I),
		F1=InputHandler.getStateofButton(GLFW_KEY_F1),F2=InputHandler.getStateofButton(GLFW_KEY_F2),
		F3=InputHandler.getStateofButton(GLFW_KEY_F3),F4=InputHandler.getStateofButton(GLFW_KEY_F4),
	    F12=InputHandler.getStateofButton(GLFW_KEY_F12),C=InputHandler.getStateofButton(GLFW_KEY_C),Y=InputHandler.getStateofButton(GLFW_KEY_Y),W=InputHandler.getStateofButton(GLFW_KEY_W)
		,ESCAPE=InputHandler.getStateofButton(GLFW_KEY_ESCAPE),ENTER=InputHandler.getStateofButton(GLFW_KEY_ENTER),
		BACKSPACE=InputHandler.getStateofButton(GLFW_KEY_BACKSPACE),S=InputHandler.getStateofButton(GLFW_KEY_S),CONTROLRIGHT=InputHandler.getStateofButton(GLFW_KEY_RIGHT_CONTROL),
		CONTROLLEFT=InputHandler.getStateofButton(GLFW_KEY_LEFT_CONTROL),F=InputHandler.getStateofButton(GLFW_KEY_F),H=InputHandler.getStateofButton(GLFW_KEY_H);
		
	    
	    if(H==1) {
	    	
	    	if(!event.isActivated()) {
	    		event.ActivateFlags();
	    	}else {
	    		event.deactivateFlags();
	    	}
	    	
	    	
	    }
	    
	    
	    
	    
	    if(I==JUST_PUSHED) {
	    	flag.setState(true);
	   	   }else {
	    	//Start.DebugPrint("false");
	    	flag.setState(false);
	    }
	    
	
	  double time=Timer.getTIme();
	  double timeHeld=time-BackSpaceHoldStart;
	 
	   if(CharCallback.takeInput && BACKSPACE==JUST_PUSHED) {
		   CharCallback.backspace();
		   Start.BackSpaceHoldStart=Timer.getTIme();	   
	   }else if(CharCallback.takeInput && BACKSPACE>JUST_PUSHED) {
		   if(timeHeld>=0.5) {
			   Start.BackSpaceHoldStart=Timer.getTIme();
			   CharCallback.removeWord();
		   }
	   }
	    
	    	
		
		
	    if(H==JUST_PUSHED && CONTROLLEFT==HELD) {
	    boolean i=CharCallback.takeInput;
       if(!i) {
	    	
	    	CharCallback.takeInput=true;
	        
	    
       }else {
    	   try {
    		
    	    
    	   }catch(NullPointerException e) {
    		   
    	   }
    	   
    	   
    	    CharCallback.clearString();
	    	CharCallback.takeInput=false;
	    	
	     
	    }
	    
	    
	    }
	 
	       if(ESCAPE==JUST_PUSHED) {
			if(maneger.isOpen() || PARTY_SELECT.isOpen()) {
				maneger.close();
				PARTY_SELECT.close();
				USEITEM_INITIALIZED=false;
				Start.playersInventory.setUseItemCalled(false);
				InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
	
			}else {
				PARTY_SELECT.reset();
			    maneger.reset();
				maneger.open();
				a1.Pause();
			    InputHandler.DisableAllBut(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_BACKSPACE,GLFW_KEY_ENTER});
			
			 
			}
			
		}
	    
	    
		
		
		
		if(testKey==JUST_REALESED) {
			//	x=0;y=0;
				if (overworld==false) {
					overworld=true;
					a1.addAnimation();
			        BattleSystem.closeBattleGUI();
					maneger.setParrentNode(Start.root);
				}else {
					maneger.close();
					BattleSystem.StartBattle(enemyField,playerField, bg, background);
					maneger.setParrentNode(Start.rootForBattleScreen);
				
				}
				
				
				
			}
			
		
		
		
		
		
		
		
		
		testKey=InputHandler.getStateofButton(GLFW_KEY_T);
	   
	   float speed=1;
	    		
		
	      if(((CONTROLRIGHT==JUST_PUSHED || CONTROLRIGHT==HELD)||(CONTROLLEFT==JUST_PUSHED||CONTROLLEFT==HELD)) && (F==JUST_PUSHED)) {//if the fuscreenCode is just pressed toggle full screen
			 w.toggleFullscreen();}
	  
		
		
	

	
		
		
		
		
		
		
		
		
		
		
	
	
		
		
		if(F2==JUST_PUSHED) {
			if(Start.DebugPrint==false) {
				
			Start.DebugPrint=true;
			}

			else{
				
			Start.DebugPrint=false;
			}

			}
		
		if( F3==JUST_PUSHED) {
			if(Start.DEBUGCOLISIONS==false) {
				
			Start.DEBUGCOLISIONS=true;
			}

			else{
				
			Start.DEBUGCOLISIONS=false;
			}

			}
		
		if(F4==JUST_PUSHED) {
			if(Start.Debugdraw==false) {
				
			Start.Debugdraw=true;
			}

			else{
					
			Start.Debugdraw=false;
			}

			}
		if( F12==JUST_PUSHED) {
			if(showFps==false) {
				showFps=true;
			
			}

			else{
				showFps=false;	
			
			}

			}
					
			


	
	
	
	if(overworld) {
	
		if((maneger.isOpen() || PARTY_SELECT.isOpen())){
			Start.STOP_PLAYER_MOVEMENT=true;
		}
	
		
		if(!Start.STOP_PLAYER_MOVEMENT) {
		
		
		
		        if(W>=JUST_PUSHED) {
		        	speed=5;
		        	running=true;
		        	
		        }else {
		        	speed=1f;
		        	a1.changefps(7);
		        	running=false;
		        }
		     float speedx=0,speedy=0;
		
		    // speed=5*speed;
				{ 
						
						
						
						if(UP>=JUST_PUSHED) {//up
						
							speedy=1;
							 a1.Play();
							
						}
						if(DOWN>=JUST_PUSHED) {//down
							speedy=-1;
							
							 a1.Play();
									
						}if(LEFT>=JUST_PUSHED) {//left
					        facingLeft=true;
					        a1.Play();
							speedx=-1;
							
						}if(RIGHT>=JUST_PUSHED) {//right
						
						   speedx=1;
						   a1.Play();
						   facingLeft=false;
						   
						}	
						if(speedx==0&&speedy==0) {
							a1.Stop();
						}else if(running==true) {
							a1.changefps(12);
						}
					
						//speedx*=(float)DeltaTime;
						//speedy*=(float)DeltaTime;
					
					
						direction=(VectorMath.normalize(new Vector2f(speedx,speedy)));
						
						velocity=new Vector2f();
					    direction.mul(speed,velocity);
									
						 
						velocity.mul(50*5);
						
						velocity.mul((float) DeltaTime);						
						
						
						
						
						velocity.mul(1/4f,quarterStepVelocity);//this is the movement that needs to be added to the position vector
                       
                    	oldpos=new Vector2f(x,y);
					 
					
                    
    								
						
				
					}
		}else {
			velocity=new Vector2f(0);
			quarterStepVelocity=new Vector2f();
		}
					
				
		 
	}
	
	Start.STOP_PLAYER_MOVEMENT=false;
			 }
	
	
	
	
	
	 	   
	 	   


	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	private static void fps() {
	       step=0;
	       GetInput.newFrame();
		   // Render.disable();
		   // canRender=false;//don't allow rendering until time
			double time2=Timer.getTIme();//gets current time
		    DeltaTime=time2-time;//this is the time since the last time through the loop
		    AnimationHandler.update();
			
		  
		
			frameTime+=DeltaTime;//this is the time since the last second
			time=time2;
		
			
			
			
		
		//	if(unproccesed>=framCap) {//when the time we have not rendered is greater than or equal to our frame target we allow rendering
				
				
				//System.out.println(DeltaTime);
			//	unproccesed-=framCap;//this is like reseting 
				//take input
				
				
				
				if(Proccesor.isUserInputallowed()) {
				 Inputupdate();}else {
						w.update();//this is needed to still allow exiting
				       
				 }
				 
			     canRender=true;//now we render	
			 	frames++;
			 	lastFrame=time2;
			 
			      if(frameTime>=1.0) {//if a second has passed print fps
			    	 
  
			    	  
			    	if(!JustStarted) {
			    	 
                      fps=frames;
                    
                      if(fps<lowFPS)
                    	  lowFPS=fps;
                      if(fps>HighFPs)
                    	  HighFPs=fps;  
                    	  
                      
                      //reset frame time and frames  
			    	}else {
			    		JustStarted=false;
			    	}
			    	
			    	  drawcalls=Render.getDrawcalls();
			    
			    	  drawcallsFrame=drawcalls/frames;
			    	 Start.DebugPrint("FPS="+fps);
			    	  
			    	  Render.resetDrawCalls();
			    	  frameTime=0;
			    	  frames=0;  
			    	  
			    	 
			    	  
			   //   }
			      
			     
					}
			
			
	}


private static void drawmap(MapLoader loader,int gridx,int gridy) {
	 // loader.loadTile(0,0);

	  for(int i=-amountHeight+2;i<amountHeight-1;i++) {
		for(int j=-amountWidth+2;j<amountWidth-1;j++) {
			   loader.loadTile(gridx+j,gridy+i);
		
			    }
	}
	  
    //loader.getModel().setDrawMethod(GL_LINES);
	  loader.drawtiles(tex);
	   currentMap.flushModel();
}




private static Vector2f updateColisions(AABB colision,Vector2f position,Vector2f oldposition,Vector2f movement,Vector2f direction) {
	
	   ColisionHandeler.updateTriggers(colision);
	   return  ColisionHandeler.updateVector(colision,position,oldposition,movement, direction);
}









	
	
public static void DebugPrint(String string) {
if(DebugPrint)	
System.out.println(string);}	
		
public static void DebugPrint(String string,Class calledFrom) {
if(DebugPrint)	
System.out.println("["+calledFrom.getName()+"]---"+string);}	
		

	
public static void UseMove(Moves move) {
	System.out.println("move "+move.name());
	
}
	


	
	



	
private static void initializeFPS() {
	time=Timer.getTIme();

    frameTime=0;
    frames=0;
	
	
	
	
	InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
	
	
	
	
	

	

}	



	
}
