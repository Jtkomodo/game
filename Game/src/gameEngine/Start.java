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

import java.util.ArrayList;

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
import guis.BarElement;
import guis.CloseWindow;
import guis.FullHeal;
import guis.GUIManeger;
import guis.GUINode;
import guis.PickMove;
import guis.UIBox;
import guis.UIBoxState;
import guis.UIElement;
import guis.UIStringElement;
import guis.UseItem;
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
    public static UIBox battleBox,StartBox,ShowBox;
 
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
		
		
		DebugPrint("Making Textures....");
		//Define texturesbr.close();
		//font=new Fontloader("aakar",512);    
		sloader= new SpriteSheetLoader("playerSpriteSheet",138);//loads the sprite sheet info
		tex=new Texture("newsprite");
		textbox=new Texture("textbox");
		bg= new Texture("testBackground");
		HealthBarBackground=new Texture("HealthBarBackground");
	    VectorTex=new Texture("vector");
		
		
		
		
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
	    playerTex= new Texture("SpriteSheets/playerSpriteSheet");
	    col2= new Texture("ColTex2");
	    COLTEX=new Texture("Whitebox");
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
		MapFIle map=new MapFIle("map1TEST");
		map.readMap();
	    currentMap=new MapLoader(tex,map,scaleOfMapTiles);
      
	
		DebugPrint("Settign Colisions....");
		playerCol=new AABB(new Vector2f(0,0),64,64,0,false);
		//playerCol=new AABB(new Vector2f(0,0),15,44,0,false);
		Col=new AABB(new Vector2f(0,0),32,32,0,true);
	AABB	Col3=new AABB(new Vector2f(100,0),16,32,0,true);
	AABB	Col4=new AABB(new Vector2f(200,0),32,32,0,true);
	AABB	Col5=new AABB(new Vector2f(400,0),32,32,0,true);
        COl2=new AABB(new Vector2f(-64,1026-64),2048,64,0,false);
	    buttonNamses = new BIndingNameParser("GLFW");
		ColisionHandeler.addCollisions(new Collisions[] {playerCol,Col,COl2,Col3,Col4,Col5});
	
		
		initializeFPS();
		x=100;
		y=100;	
		double ENDTime=Timer.getTIme();
		double timeTaken=ENDTime-startTime;
		DebugPrint("Tiem to load--- "+timeTaken+" seconds");
		
		DebugPrint("Starting Game loop.....");
		
		
		s.loadVec4(Color, new Vector4f(1,1,1,1));
     
		a1=new Animate(7,player,sloader,0,7);
		 
		 playersInventory= new Inventory(new Items[] {Items.hpPotion,Items.SuperHpPotion,Items.spRestore},new int[] {1,3,2});
		 enemyTestInventory = new Inventory(new Items[] {Items.hpPotion,Items.SuperHpPotion,Items.spRestore,Items.spSuperRestore},new int[] {3,1,4,2});
	
 
		p=new BattleEntity(player, playerTex,64*1.5f, new Vector2f(100,10),Pcs.C1.getAtk(),Pcs.C1.getDef(),Pcs.C1.getHp(),Pcs.C1.getSp(),Pcs.C1.getSpeed(),Pcs.C1.getMoves(),playersInventory);
	BattleEntity    p2=new BattleEntity(player, playerTex,64*1.5f, new Vector2f(100,10),Pcs.C1.getAtk(),Pcs.C1.getDef(),Pcs.C1.getHp(),Pcs.C1.getSp(),Pcs.C1.getSpeed(),Pcs.C1.getMoves(),playersInventory);
//	BattleEntity	p3=new BattleEntity(player, playerTex,64*1.5f, new Vector2f(100,10),Pcs.C1.getAtk(),Pcs.C1.getDef(),Pcs.C1.getHp(),Pcs.C1.getSp(),Pcs.C1.getSpeed(),Pcs.C1.getMoves(),playersInventory);
		
		Enemy enemy=new Enemy(new Vector2f(50,10),player, playerTex, 96,Enemies.E1.getName(),Enemies.E1.getAtk(),Enemies.E1.getDef(),Enemies.E1.getHp(),Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy2=new Enemy(new Vector2f(50,10),player, playerTex,96,"E2",Enemies.E1.getAtk(),Enemies.E1.getDef(),Enemies.E1.getHp(),Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy3=new Enemy(new Vector2f(50,10),player, playerTex,96,"E3",Enemies.E1.getAtk(),Enemies.E1.getDef(),Enemies.E1.getHp(),Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy4=new Enemy(new Vector2f(50,10),player, playerTex,96,"E4",Enemies.E1.getAtk(),Enemies.E1.getDef(),Enemies.E1.getHp(),Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy5=new Enemy(new Vector2f(50,10),player, playerTex, 96,Enemies.E1.getName(),Enemies.E1.getAtk(),Enemies.E1.getDef(),15,Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		Enemy enemy6=new Enemy(new Vector2f(50,10),player, playerTex, 96,Enemies.E1.getName(),Enemies.E1.getAtk(),Enemies.E1.getDef(),15,Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory,Enemies.E1.getEnemyAI());
		
		
		
	
		
		enemyField=new BattleEnemyField(new BattleSlot[] {new BattleSlot(new Vector2f(140,-20),enemy),new BattleSlot(new Vector2f(202,-88),enemy2),new BattleSlot(new Vector2f(202,10),enemy3),new BattleSlot(new Vector2f(260,-20),enemy4)});
	    playerField=new BattlePlayerField(new BattleSlot[] {new BattleSlot(new Vector2f(-90,40),p),new BattleSlot(new Vector2f(-100,-40),p2)});
		
		
		playersSPBAr=new HpBar(p.getMaxsp(),p.getSp(),new Vector2f(80,10),HealthBarBackground, COLTEX,Constants.BAR_COLOR_YELLOW,Constants.BAR_COLOR_YELLOW); 
		
	
		
		
		 
		UIElement StartElements[] ={
			new UIStringElement("Stats",new Vector2f(-17,35),.2f,Constants.BLACK,new guis.DebugPrint("stats")),
			new UIStringElement("Bag",new Vector2f(-17,15),.2f,Constants.BLACK,1),
			new UIStringElement("Heal",new Vector2f(-17,-5),.2f,Constants.BLACK,new FullHeal(p)),
			new UIStringElement("Quit",new Vector2f(-17,-25),.2f,Constants.BLACK,new CloseWindow(w))
		
			};
		
		
		
		UIStringElement BagElements[]= {new UIStringElement("-------bag------",new Vector2f(-38,40), .15f,Constants.BLACK)
		};
			 
		UIBoxState StartBoxs[]= {
				new UIBoxState(new Vector2f(0,0),30,50,StartElements,Start.COLTEX,Constants.COL_COLOR_BLUE.add(new Vector4f(0,0,50,-50),new Vector4f(0))),
				new UIBoxState(new Vector2f(-200,0),w.getWidth()-200,w.getHeight()-200,BagElements,Start.textbox,Constants.COL_COLOR_BLUE.add(new Vector4f(0,0,50,80),new Vector4f(0)))
		};
			 
		UIStringElement Elements[]= {new UIStringElement("event walked\n into colision\n Box",new Vector2f(-38,30), .15f,Constants.BLACK)
		};
		UIBoxState showBoxStates[]= {
			
				new UIBoxState(new Vector2f(-200,0),100,41,Elements,Start.textbox,Constants.COL_COLOR_BLUE.add(new Vector4f(0,0,50,80),new Vector4f(0)))
		};
		
	   ShowBox=new UIBox(new Vector2f(0,50),showBoxStates);	
	   StartBox=new UIBox(new Vector2f(screencoordx,screencoordy),StartBoxs); 
	 

			 
			 
			 
			 
			UIStringElement MenuElements[]= {
					new UIStringElement("bag",new Vector2f(-35,-5), .15f,Constants.BLACK,2),
					new UIStringElement("moves",new Vector2f(-17,15), .15f,Constants.BLACK,1),
					new UIStringElement("specials",new Vector2f(1,-5), .15f,Constants.BLACK,3)
			};
			
			
			Moves punch=p.getmoveFromString(Moves.punch.getName());
			Moves heal=p.getmoveFromString(Moves.heal.getName());
			
			UIElement MoveElements[]= {new UIStringElement("---moves---",new Vector2f(-28.5f,23), .15f,Constants.BLACK),
					new UIStringElement(punch.getName(),new Vector2f(-54,5),.15f,Constants.BLACK,new PickMove(punch.getName()))
					
			};
			
			
			
			UIStringElement SPElements[]= {new UIStringElement("---specials---",new Vector2f(-34,23), .15f,Constants.BLACK),
					new UIStringElement(heal.getName()+"sp",new Vector2f(-54,5), .15f,Constants.BLACK,new PickMove(heal.name()))
					
			};
			
			
			UIBoxState boxs[]= {new UIBoxState(new Vector2f(),71,28,MenuElements,textbox,true),new UIBoxState(new Vector2f(150,10f),71,28,MoveElements,textbox),
					new UIBoxState(new Vector2f(150,10),71,50,BagElements,Start.COLTEX,Constants.COL_COLOR_BLUE.add(new Vector4f(0,0,29,50),new Vector4f(0))),new UIBoxState(new Vector2f(150,10f),71,28,SPElements,textbox)
			};
		
		
	
		
		
		
		
		battleBox=new UIBox(new Vector2f(100,0),boxs);//this is the UIbox for the battle UI
        StartBox.getUIState(1).addElement(new BarElement("HP:",p.getHpbar(),new Vector2f(-17,65)));
        source1=new Source(new Vector2f(0),1,1, 1,200, 0);
        source=new Source(new Vector2f(0), 1, 1, 0, 0,0);
		source.setSourceRelitive(true);	
		
		//teste=new Entity(player,new Vector3f(0,0,200),0,64, playerTex);
		
//		enemy.setHp(10);
//		enemy2.setHp(10);
//		enemy3.setHp(10);
//		enemy4.setHp(10);
		enemy.setSp(1);
		enemy2.setSp(1);
		enemy3.setSp(1);
		enemy4.setSp(1);
		
	
		BattleSystem.INIT(battleBox);
		
        
		GUINode Nodeheal=new GUINode("item",new PickMove(heal.getName()));
		GUINode NodePunch=new GUINode("item",new PickMove(punch.getName()));
		GUINode Nodeitem3=new GUINode("item",new PickMove(heal.getName()));
		GUINode Nodeitem4=new GUINode("item",new PickMove(punch.getName()));
		
		GUINode root=new GUINode(new GUINode[]{Nodeheal,NodePunch,Nodeitem3,Nodeitem4,Nodeheal,Nodeitem3,Nodeitem3,Nodeitem3},3,4);
		GUIManeger m=new GUIManeger(root);
	
	while(!w.isExited()) {
		
		fps();    
	    
	//if(canRender) {
		BattleSystem.soundPlay=true;
		
		//----------------------GAME--LOOP------------------------------
 		Items[] ITM= playersInventory.getItems();		
			UIElement[] elementlist=new UIElement[ITM.length];	
			
			for(int i=0;i<ITM.length;i++) {
				elementlist[i]=new UIStringElement(ITM[i].Item.getName()+"  "+playersInventory.getAmountOfItem(ITM[i].Item),new Vector2f(-54,5-(i*14)), .15f,Constants.BLACK,new UseItem(ITM[i]));
			}

		

			
	 	
				//MainBatchRender.addTexture(textbox);
	
		StartBox.getUIState(1).relpaceALLActive(elementlist);
		battleBox.getUIState(2).relpaceALLActive(elementlist);
			
		
	
	

		
 	
			//MainBatchRender.addTexture(textbox);

	
	  // TextureUpdate(MAP)
//Render.enable();//enables render


		
	//if(test==false) {


	
	if(overworld==true) {	
		
		ShowBox.hide();
		 // MainRenderHandler.addEntity(new Entity(background,new Vector3f(100,100,100),0,64, COLTEX,Constants.BLACK,10,true));
		
		  
		 
		  //  MainRenderHandler.addEntity(new Entity(background,new Vector3f(new Vector2f(x,y).add(quarterStepVelocity.mul(3,new Vector2f())),200),0,5, COLTEX,Constants.YELLOW));
		  if(!ESCAPEBOXUP) {    
		   Vector2f step=new Vector2f();
	
		  //step 1
		   ColisionHandeler.setColided(false);
           new Vector2f(x,y).add(quarterStepVelocity,step);
		    playerCol.setCenterPosition(step);//take step
		 
		    Vector2f vector=updateColisions(playerCol,step,oldpos, quarterStepVelocity, direction);//check colisions
		  //  MainRenderHandler.addEntity(new Entity(background,new Vector3f(vector,200),0,5, COLTEX,Constants.GREEN));//put marker to indicate where this step ended at
		    Start.text1.setString("bc: "+Math.round(playerCol.getPosBeforeCol().x)+", "+Math.round(playerCol.getPosBeforeCol().y));
			   Start.text1.UIdrawString((640/2)+Start.screencoordx-100,(480/2)+Start.screencoordy-80,.2f);			  
		    
		    //step 2
			oldpos.set(vector);
		    vector.add(quarterStepVelocity,step);
		    playerCol.setCenterPosition(step);
			vector=updateColisions(playerCol,step,oldpos, quarterStepVelocity, direction);
		//    MainRenderHandler.addEntity(new Entity(background,new Vector3f(vector,200),0,5, COLTEX,Constants.BLUE));
		    
			
		   //step3
			oldpos.set(vector);
		    vector.add(quarterStepVelocity,step);
		    playerCol.setCenterPosition(step);
			vector=updateColisions(playerCol,step,oldpos, quarterStepVelocity, direction);
		//    MainRenderHandler.addEntity(new Entity(background,new Vector3f(vector,200),0,5, COLTEX,Constants.RED));
			
		    //step4
			oldpos.set(vector);
		    vector.add(quarterStepVelocity,step);
		    playerCol.setCenterPosition(step);
			vector=updateColisions(playerCol,step,oldpos, quarterStepVelocity, direction);
		//    MainRenderHandler.addEntity(new Entity(background,new Vector3f(vector,200),0,5, COLTEX,Constants.YELLOW));
		
			if(!ColisionHandeler.getColided()) {
				
				playerCol.setPosBeforeCol(vector);
				
				
			
				
			}
			  
		    
			x=vector.x; y=vector.y;	
		    playerCol.setCenterPosition(vector);	
			
			
			
		
			     
				    camx=-x;
					camy=-y;
							
		screencoordx=-camx;
	    screencoordy=-camy;
		
		  }	
		//  textA.UIdrawString((640/2)+screencoordx-100,(480/2)+screencoordy-80,.2f);
		//DebugPrint("x="+x+","+y);
		
	     cam.setPosition((new Vector2f(camx,camy)));
	   //  DebugPrint(""+cam.getPosition());		
		//screencoordx=-camx;
	   // screencoordy=-camy;
	//   MainRenderHandler.addEntity(test);
	 //   MainRenderHandler.addEntity(test2);
	    		
	     //cam.setPosition((new Vector2f(camx,camy)));s.bind();
	     //cam.setPosition((new Vector2f(camx,camy)));s.bind();
		
		       
		    //   testcol=playerCol.AABBwAABB(Col); 
	   
	  
	  
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
	    
	      m.draw(new Vector2f(screencoordx,screencoordy),0.2f,new Vector2f(50,80));
		   
	    
	      
		if(HideSprite==false) 
       
		a1.drawAnimatedModel(new Vector3f(x,y,100),0,Playerscale,!facingLeft);
		
	
		//SpriteUpdate(player,playerTex,x,y,Playerscale,facingLeft);
	

		textB.UIDebugdrawString(screencoordx-300,screencoordy-220,.2f);
		
		textC.UIDebugdrawString(screencoordx+100,screencoordy-220,.2f);
      
		 ColisionHandeler.Debug();

}else {
	//---------------------battle loop---------------------
	

	BattleSystem.battleUpdate();
	
    screencoordx=0;
	screencoordy=0;
	
	
   






}
	
     	 StartBox.setPosition(new Vector2f(screencoordx+300,screencoordy));
     	StartBox.draw(); 
      
	  ShowBox.draw();
	  textA.setString("FPS="+(int)fps+"\nH:"+HighFPs+" L:"+lowFPS);
		
	  if(showFps)
		  textA.UIdrawString((640/2)+screencoordx-100,(480/2)+screencoordy-20,.2f);
	


if(CharCallback.takeInput) {
	
   MainRenderHandler.addEntity(new Entity(textboxM,new Vector3f(screencoordx,screencoordy,text1.getZ()-1),0,3, COLTEX,Constants.BLACK));
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

	private static void Inputupdate() {
	
		
		w.update();//this is needed to actually poll events from keyboard 
		
//		boolean isPresent=GLFW.glfwJoystickPresent(GLFW.GLFW_JOYSTICK_1);
//		
//			DebugPrint(""+isPresent);
//		
//		    	
		 
		    	InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_ENTER,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
		    if(CharCallback.takeInput) {
		    	InputHandler.EnableButton(GLFW_KEY_BACKSPACE);
		    }
		    

		StartBox.Update();
		battleBox.Update();
		
		

	   
	    int UP=InputHandler.getStateofButton(GLFW_KEY_UP),DOWN=InputHandler.getStateofButton(GLFW_KEY_DOWN),
	    LEFT=InputHandler.getStateofButton(GLFW_KEY_LEFT),RIGHT=InputHandler.getStateofButton(GLFW_KEY_RIGHT),
		
		F1=InputHandler.getStateofButton(GLFW_KEY_F1),F2=InputHandler.getStateofButton(GLFW_KEY_F2),
		F3=InputHandler.getStateofButton(GLFW_KEY_F3),F4=InputHandler.getStateofButton(GLFW_KEY_F4),
	    F12=InputHandler.getStateofButton(GLFW_KEY_F12),C=InputHandler.getStateofButton(GLFW_KEY_C),Y=InputHandler.getStateofButton(GLFW_KEY_Y),W=InputHandler.getStateofButton(GLFW_KEY_W)
		,ESCAPE=InputHandler.getStateofButton(GLFW_KEY_ESCAPE),ENTER=InputHandler.getStateofButton(GLFW_KEY_ENTER),
		BACKSPACE=InputHandler.getStateofButton(GLFW_KEY_BACKSPACE),S=InputHandler.getStateofButton(GLFW_KEY_S),CONTROLRIGHT=InputHandler.getStateofButton(GLFW_KEY_RIGHT_CONTROL),
		CONTROLLEFT=InputHandler.getStateofButton(GLFW_KEY_LEFT_CONTROL),F=InputHandler.getStateofButton(GLFW_KEY_F),H=InputHandler.getStateofButton(GLFW_KEY_H);
		
		
	 
	  double time=Timer.getTIme();
	  double timeHeld=time-BackSpaceHoldStart;
	  
	   if(CharCallback.takeInput && BACKSPACE==1) {
		   CharCallback.backspace();
		   Start.BackSpaceHoldStart=Timer.getTIme();	   
	   }else if(CharCallback.takeInput && BACKSPACE>1) {
		   if(timeHeld>=0.5) {
			   Start.BackSpaceHoldStart=Timer.getTIme();
			   CharCallback.removeWord();
		   }
	   }
	    
	    	
		
		
	    if(H==1 && CONTROLLEFT>0) {
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
	    ESCAPEBOXUP=StartBox.isActive();
	       if(ESCAPE==1) {
			if(StartBox.isActive()) {
				StartBox.hide();
				InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
	
			}else {
				
				StartBox.show();
				a1.Pause();
			    InputHandler.DisableAllBut(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_BACKSPACE,GLFW_KEY_ENTER});
			    StartBox.reset();
			 
			}
			
		}
	    
	    
		
		
		
		if(testKey==2) {
			//	x=0;y=0;
				if (overworld==false) {
					overworld=true;
					a1.addAnimation();
			         battleBox.hide();
					
				}else {
					
					BattleSystem.StartBattle(enemyField,playerField, bg, background);
					
				
				}
				
				
				
			}
			
		
		
		
		
		
		
		
		
		testKey=InputHandler.getStateofButton(GLFW_KEY_T);
	   
	   float speed=1;
	    		
		
	      if(((CONTROLRIGHT==1 || CONTROLRIGHT==3)||(CONTROLLEFT==1||CONTROLLEFT==3)) && (F==1)) {//if the fuscreenCode is just pressed toggle full screen
			 w.toggleFullscreen();}
	  
		
		
	

	
		
		
		
		
		
		
		
		
		
		
	
	
		
		
		if(F2==1) {
			if(Start.DebugPrint==false) {
				
			Start.DebugPrint=true;
			}

			else{
				
			Start.DebugPrint=false;
			}

			}
		
		if( F3==1) {
			if(Start.DEBUGCOLISIONS==false) {
				
			Start.DEBUGCOLISIONS=true;
			}

			else{
				
			Start.DEBUGCOLISIONS=false;
			}

			}
		
		if(F4==1) {
			if(Start.Debugdraw==false) {
				
			Start.Debugdraw=true;
			}

			else{
					
			Start.Debugdraw=false;
			}

			}
		if( F12==1) {
			if(showFps==false) {
				showFps=true;
			
			}

			else{
				showFps=false;	
			
			}

			}
					
			


	
	
	
	if(overworld) {
	
		
	
		
		if(!StartBox.isActive()) {
		
		
		
		        if(W==1 ||W==3) {
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
						
						
						
						if(UP==1 || UP==3) {//up
						
							speedy=1;
							 a1.Play();
							
						}
						if(DOWN==1||DOWN==3) {//down
							speedy=-1;
							
							 a1.Play();
									
						}if(LEFT==1||LEFT==3) {//left
					        facingLeft=true;
					        a1.Play();
							speedx=-1;
							
						}if(RIGHT==1||RIGHT==3) {//right
						
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
		}
					
				
		 
	}
	
		 
			 }
	
	
	
	
	
	 	   
	 	   


	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	private static void fps() {
	       step=0;
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
	
 //   loader.getModel().setDrawMethod(GL_LINES);
	  loader.drawtiles(tex);
	   currentMap.flushModel();
}




private static Vector2f updateColisions(AABB colision,Vector2f position,Vector2f oldposition,Vector2f movement,Vector2f direction) {
	
	step++;
    
   
	 ColisionHandeler.amountThrough=0;
	 ColisionHandeler.FlushPushList();
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

public static void ShowBox(Vector2f position) {
   ShowBox.setPosition(position.add(new Vector2f(Rendercamx,Rendercamy),new Vector2f(0)));
   ShowBox.show();
    	
}


	
}
