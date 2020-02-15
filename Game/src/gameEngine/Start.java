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
import org.joml.Vector4f;

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
import battleClasses.Enemy;
import battleClasses.HpBar;
import battleClasses.TimedButton;
import guis.BarElement;
import guis.FunctionCaller;
import guis.GUIMEthods;
import guis.UIBox;
import guis.UIBoxState;
import guis.UIElement;
import guis.UIStringElement;
import input.BIndingNameParser;
import input.CharCallback;
import input.GetInput;
import input.InputHandler;
import textrendering.Fontloader;
import textrendering.TextBuilder;
public class Start {
    
	
	public static Window w;
	public static final int width=640,height=480;
    public static int lowFPS=60,HighFPs=0;
	
	
	public static int location,Projection,Color,RTS,frames=0,j=0,i=0,fps,gridx,gridy,Aframes,drawcalls=0,drawcallsFrame=0;
    public static byte dKeys,testKey;
    
   
    public static BIndingNameParser buttonNamses;
    public static boolean JustStarted=true,MoveInprogress=false,MoveCalled=false,TurnFinished=false,BattleEnded=false;
    public static TimedButton Button;
    public static ShaderProgram s;
    public static float scaleOfMapTiles=128,Rendercamx,Rendercamy;
    public static int amountWidth=Math.round((width/scaleOfMapTiles)),amountHeight=Math.round((height/scaleOfMapTiles));
    public static Camera cam,DebugCam;
    public static float screencoordx=0,screencoordy=0;
    public static InputHandler I;
    public static Fontloader font;
    public static boolean canRender,overworld=true,test=false,testcol,circCol,GLDEBUG=false,LOG=true,DEBUGCOLISIONS=true,HideSprite=false,DebugPrint=true,Debugdraw=true,showFps=true,StateOfStartBOx=false;
    public static double framCap,time,time2,passed,unproccesed,frameTime=0,lastFrame=0,DeltaTime,animateTime,Ti,TT,seconds,amountInSeconds,TARGETFPS=60;
    public static Texture tex,MAP,bg,playerTex,COLTEX,piont,piont2,col2,circleCol1,circleCol2,textbox,testSprite,HealthBarBackground;
    public static float x2,y2,camx,camy,x,y,Playerscale=64;
    public static Model background,player,textboxM,Arrow;
    public static BatchedModel testM;
    public static TextBuilder textB,textA,textC,textD,text1,textDrawCalls,textR;
    public static Vector2f currentmovement,c2,oldpos,direction,BattleBoxPosition,velocity;
    public static BattleEntity p;
 
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
    public static BattleEnemyField enemyField,CurrentEnemyFeild;
    public static BattleEntity currentEntity;
    public static Sound lazer,Heal;
    public static Source source1;
    public static Fontloader aakar;
    public static ArrayList<BattleEntity> turnOrder= new ArrayList<BattleEntity>();
    public static boolean facingLeft,running,PlayersTurn=true;

    
    
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
		DebugPrint("Making Textures....");
		//Define texturesbr.close();
		//font=new Fontloader("aakar",512);    
		sloader= new SpriteSheetLoader("playerSpriteSheet",138);//loads the sprite sheet info
		tex=new Texture("newsprite");
		textbox=new Texture("textbox");
		bg= new Texture("testBackground");
		HealthBarBackground=new Texture("HealthBarBackground");
		
		lazer=new Sound("Lazer");
		Heal=new Sound("healing sound");
		
		
		
		
		aakar=new Fontloader("aakar",512);    
	    textB= new TextBuilder(aakar); 
		textA= new TextBuilder(aakar);
		textC= new TextBuilder(aakar);
		textD= new TextBuilder(aakar);
		text1= new TextBuilder(aakar);
		textR=new TextBuilder(aakar);
		textDrawCalls= new TextBuilder("aakar",512);
	//	TextBuilder textCircle= new TextBuilder("aakar",512);
		testM= new BatchedModel();
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
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		DebugPrint("Loading world....");
		WorldLoader worldLoader=new WorldLoader("map3");
		MapLoader loader=new MapLoader(worldLoader.Getmap(),scaleOfMapTiles);
        PositionTest postest=new PositionTest(worldLoader.Getmap(),scaleOfMapTiles);	
		
	
		DebugPrint("Settign Colisions....");
		//playerCol=new AABB(new Vector2f(0,0),15,44,0);
		playerCol=new AABB(new Vector2f(0,0),15,44,0);
		Col=new AABB(new Vector2f(4968-64,1280-64),64,128,1,new FunctionCaller("ShowBox",new Object[] {new Vector2f(0,100) },Start.class));
        COl2=new AABB(new Vector2f(-64,1026-64),2048,64,0);
	    buttonNamses = new BIndingNameParser("GLFW");
		ColisionHandeler.addCollisions(new Collisions[] {playerCol,Col,COl2});
	
		
		initializeFPS();
		x=100;
		y=100;	
		double ENDTime=Timer.getTIme();
		double timeTaken=ENDTime-startTime;
		DebugPrint("Tiem to load--- "+timeTaken+" seconds");
		
		DebugPrint("Starting Game loop.....");
		
		
		s.loadVec4(Color, new Vector4f(1,1,1,1));
     
	
		 playersInventory= new Inventory(new Items[] {Items.hpPotion,Items.SuperHpPotion,Items.spRestore},new int[] {1,3,2});
		 enemyTestInventory = new Inventory(new Items[] {Items.hpPotion,Items.SuperHpPotion,Items.spRestore,Items.spSuperRestore},new int[] {3,1,4,2});
		 
		p=new BattleEntity(new Vector2f(100,10),Pcs.C1.getAtk(),Pcs.C1.getDef(),Pcs.C1.getHp(),Pcs.C1.getSp(),Pcs.C1.getSpeed(),Pcs.C1.getMoves(),playersInventory);
		Enemy enemy=new Enemy(new Vector2f(50,10),player,playerTex,96,Enemies.E1.getName(),Enemies.E1.getAtk(),Enemies.E1.getDef(),15,Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory);
		Enemy enemy2=new Enemy(new Vector2f(50,10),player,playerTex,96,"E2",Enemies.E1.getAtk(),Enemies.E1.getDef(),20,Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory);
		Enemy enemy3=new Enemy(new Vector2f(50,10),player,playerTex,96,"E3",Enemies.E1.getAtk(),Enemies.E1.getDef(),20,Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory);
		Enemy enemy4=new Enemy(new Vector2f(50,10),player,playerTex,96,"E4",Enemies.E1.getAtk(),Enemies.E1.getDef(),20,Enemies.E1.getSp(),Enemies.E1.getSpeed(),Enemies.E1.getMoves(),enemyTestInventory);
		
		
		
		
		enemyField=new BattleEnemyField(new Enemy[] {enemy,enemy2,enemy3,enemy4});
	
		playersSPBAr=new HpBar(p.getMaxsp(),p.getSp(),new Vector2f(80,10),HealthBarBackground, COLTEX,Constants.BAR_COLOR_YELLOW,Constants.BAR_COLOR_YELLOW); 
		
	
		
		
		 
	UIElement StartElements[] ={
			new BarElement("HP:",p.getHpbar(),new Vector2f(-17,65)),
		new UIStringElement("Stats",new Vector2f(-17,35),.2f,Constants.BLACK,new FunctionCaller("DebugPrint",new Object[] {"stats"},new Class[] {String.class},Start.class)),
		new UIStringElement("Bag",new Vector2f(-17,15),.2f,Constants.BLACK,1),
		new UIStringElement("Heal",new Vector2f(-17,-5),.2f,Constants.BLACK,new FunctionCaller(GUIMEthods.fullheal,new Object[] {p})),
		new UIStringElement("Quit",new Vector2f(-17,-25),.2f,Constants.BLACK,new FunctionCaller(GUIMEthods.exitWINDOW))
		
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
				new UIStringElement(punch.getName(),new Vector2f(-54,5),.15f,Constants.BLACK,new FunctionCaller(GUIMEthods.PickMove,new Object[] {p,punch.getName()})),
				
		};
		
		
		
		UIStringElement SPElements[]= {new UIStringElement("---specials---",new Vector2f(-34,23), .15f,Constants.BLACK),
				new UIStringElement(heal.getName()+" "+heal.getCost()+"sp",new Vector2f(-54,5), .15f,Constants.BLACK,new FunctionCaller(GUIMEthods.PickMove,new Object[] {p,heal.name()}))
				
		};
		
		
		UIBoxState boxs[]= {new UIBoxState(new Vector2f(),71,28,MenuElements,textbox,true),new UIBoxState(new Vector2f(150,10f),71,28,MoveElements,textbox),
				new UIBoxState(new Vector2f(150,10),71,50,BagElements,Start.COLTEX,Constants.COL_COLOR_BLUE.add(new Vector4f(0,0,29,50),new Vector4f(0))),new UIBoxState(new Vector2f(150,10f),71,28,SPElements,textbox)
		};
		
		
		
		
		
		
		
		battleBox=new UIBox(new Vector2f(100,0),boxs);//this is the UIbox for the battle UI
        StartBox.getUIState(1).addElement(new BarElement("HP:",p.getHpbar(),new Vector2f(-17,65)));
        source1=new Source(new Vector2f(0),1,1, 1,200, 0);
     
			
		
	
	while(!w.isExited()) {
			oldpos=new Vector2f(x,y);
		fps();    
	    
	if(canRender) {
		
		//----------------------GAME--LOOP------------------------------
 		Items[] ITM= playersInventory.getItems();		
			UIElement[] elementlist=new UIElement[ITM.length];	
			
			for(int i=0;i<ITM.length;i++) {
				elementlist[i]=new UIStringElement(ITM[i].Item.getName()+"  "+playersInventory.getAmountOfItem(ITM[i]),new Vector2f(-54,5-(i*14)), .15f,Constants.BLACK,new FunctionCaller(GUIMEthods.UseItem,new Object[] {p,ITM[i]},new Class[] {p.getClass(),Items.class}));
			}

		
	 	
			
			
			  playersSPBAr.setValue(p.getSp());
		StartBox.getUIState(1).relpaceALLActive(elementlist);
	    		
	 	 	 
	  // TextureUpdate(MAP)
Render.enable();//enables render

	
		
	//if(test==false) {
	
	if(overworld==true) {	
		
		ShowBox.hide();
	
	    playerCol.setPosition(new Vector2f(x,y));
	    
			Vector2f vector=updateColisions(playerCol,new Vector2f(x,y),oldpos, c2, direction);
			
			x=vector.x; 
			y=vector.y;	
			     
				    camx=-x;
					camy=-y;
							
		screencoordx=-camx;
	    screencoordy=-camy;
	   
	     cam.setPosition((new Vector2f(camx,camy)));
				
		screencoordx=-camx;
	    screencoordy=-camy;
	    
	    		
	     cam.setPosition((new Vector2f(camx,camy)));s.bind();
	     //cam.setPosition((new Vector2f(camx,camy)));s.bind();
		
		       
		    //   testcol=playerCol.AABBwAABB(Col); 
	       
	    

	  
	     Listener.ChangePosition(oldpos);

	      textB.setString("x="+x+" y="+y);
	      Vector2f camPos=cam.getPosition();
	     Rendercamx=-(camPos.x);
	     Rendercamy=-(camPos.y);
	      Vector2f newvec=postest.findPositionOnMap(Rendercamx,Rendercamy);
	      gridx= Math.round(newvec.x);gridy=Math.round(newvec.y);
	    drawmap(loader,gridx,gridy);		     
	   ColisionHandeler.Debug();
	      textD.setString("circCol:"+circCol);
	      textC.setString("xmap="+gridx+" ymap="+gridy);
	    
	   
	    
	      
		if(HideSprite==false) 
		a1.drawAnimatedModel(new Vector2f(x,y),0,Playerscale,!facingLeft);
		
		
		
		//SpriteUpdate(player,playerTex,x,y,Playerscale,facingLeft);
	
		textB.UIDebugdrawString(screencoordx-300,screencoordy-220,.2f);
		
		textC.UIDebugdrawString(screencoordx+100,screencoordy-220,.2f);
      
       

}else {
	//---------------------battle loop---------------------
	

	battleupdate();
	
    screencoordx=0;
	screencoordy=0;
	
	
   






}
	 
     	 StartBox.setPosition(new Vector2f(screencoordx+300,screencoordy));
     	StartBox.draw(); 
      
	  ShowBox.draw();
textDrawCalls.setString("Drawcalls(S:"+drawcalls+ "\nF:"+drawcallsFrame+")\nAnimations: "+AnimationHandler.amountInList());
textA.setString("FPS="+(int)fps+"\nH:"+HighFPs+" L:"+lowFPS);
if(showFps)
textA.UIdrawString((640/2)+screencoordx-100,(480/2)+screencoordy-20,.2f);
textDrawCalls.UIDebugdrawString((640/2)+screencoordx-625,(480/2)+screencoordy-20,.25f);

if(CharCallback.takeInput) {
	
    Render.draw(textboxM,new Vector2f(screencoordx,screencoordy),0,3, COLTEX,Constants.BLACK);
    text1.setString("TAKING INPUT");
	text1.drawString(screencoordx-75, screencoordy+70, .2f,Constants.RED);
}
text1.setString(CharCallback.string);
text1.drawString(screencoordx-200, screencoordy, .15f);


Proccesor.proccesCommands(time);
		    w.render();
		    w.clear();
		  
		    loader.flushModel();
			
		}
		}
//--------------------------------------------------------------	
		DebugPrint("Closing.....");
		DebugPrint("Highest fps="+HighFPs+" Lowest fps="+lowFPS);
		w.destroy();
		AudioInit.destroy();
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
		
if(CharCallback.takeInput) {
	    
		    InputHandler.DisableAllBut(new int[] {GLFW_KEY_H});
		    	String string=CharCallback.string;
		    	
		    	
		    	if(GetInput.getStateofButton(GLFW_KEY_BACKSPACE)==1 && string.length()!=0) {//back space
					   
					   CharCallback.string=(string.substring(0,string.length()-1).toString());
						
					   string=CharCallback.string;
					}	
		    	if(GetInput.getStateofButton(GLFW_KEY_ENTER)==1) {
		    		CharCallback.string=string+"\n";
		    	}
		    	
		    	
		    	if(string.contains("/v")) {
		    		int index=string.indexOf("/v");
		    		String s=string.subSequence(index+1,string.length()).toString();
		    	
		    		if(s.contentEquals("vscreenx")) {
		    			s=string.replace("/vscreenx",""+Start.screencoordx);
		    			CharCallback.string=s;
		    		}
		    		if(s.contentEquals("vscreeny")) {
		    			s=string.replace("/vscreeny",""+Start.screencoordy);
		    			CharCallback.string=s;
		    		}
		    		
		    		
		    		
		    	}
		    if(CharCallback.string.endsWith("\n")) {
		    
		    if(CharCallback.string.contains("#(")&& CharCallback.string.contains(")")){	
		    	string=CharCallback.string.substring(CharCallback.string.indexOf("#"),CharCallback.string.indexOf(")"));
		    	
		    	string=string.replace("#(","");
		    	string=string.replace(")","");
		    	
		    	if(string.contentEquals("EXIT")) {
		    		w.CloseWIndow();
		    	   
		    	
		    	
		    	}else if(string.startsWith("DEBUGPRINT=")) {
		    		
		    		int index=string.indexOf("DEBUGPRINT=")+11;
		    		
		    		string=string.substring(index);
		    		if(string.contentEquals("true") || string.contentEquals("false")) {
		    	    Start.DebugPrint=Boolean.parseBoolean(string);
		    	    CharCallback.string=CharCallback.string.replace("#(DEBUGPRINT="+string+")","/[OK]/Set "+string);
		    		}else{
		    			CharCallback.string=CharCallback.string.replace("#(DEBUGPRINT="+string+")","/[ERROR]/Sorry that is not a valid value");
		    			
		    		}
		  
		    	}else if(string.startsWith("DEBUGDRAW=")) {
		    		
		    		int index=string.indexOf("DEBUGDRAW")+10;
		    		
		    		string=string.substring(index);
		    		if(string.contentEquals("true") || string.contentEquals("false")) {
		    	    Start.Debugdraw=Boolean.parseBoolean(string);
		    	    CharCallback.string=CharCallback.string.replace("#(DEBUGDRAW="+string+")","/[OK]/Set "+string);
		    		}else{
		    			CharCallback.string=CharCallback.string.replace("#(DEBUGDRAW="+string+")","/[ERROR]/Sorry that is not a valid value");
		    			
		    		}
		    	}else if(string.startsWith("DEBUGFPS=")) {
		    		
		    		int index=string.indexOf("DEBUGFPS=")+9;
		    		
		    		string=string.substring(index);
		    		if(string.contentEquals("true") || string.contentEquals("false")) {
		    	    Start.showFps=Boolean.parseBoolean(string);
		    	    CharCallback.string=CharCallback.string.replace("#(DEBUGFPS="+string+")","/[OK]/Set "+string);
		    		}else{
		    			CharCallback.string=CharCallback.string.replace("#(DEBUGFPS="+string+")","/[ERROR]/Sorry that is not a valid value");
		    			
		    		}}else if(string.startsWith("ADD_AABB")) {
		    			
		    			int index=string.indexOf("ADD_AABB")+8;
		    			string=string.substring(index);
		    			String[] arguments=string.split(",");
		    			if(arguments.length==5) {
		    				try {
		    			float x=Float.parseFloat(arguments[0]);	
		    			float y=Float.parseFloat(arguments[1]);
		    			float widthr=Float.parseFloat(arguments[2]);
		    			float heightR=Float.parseFloat(arguments[3]);
		    			float r=Float.parseFloat(arguments[4]);
		    			ColisionHandeler.addCollision(new AABB(new Vector2f(x,y),widthr,heightR,r));
		    		    CharCallback.string=CharCallback.string.replace("#(ADD_AABB"+string+")","/[OK]/COL_ADDED");
		    			
		    				}catch(NumberFormatException e) {
		    				
		    					CharCallback.string=CharCallback.string.replace("#(ADD_AABB"+string+")","/[ERROR]/Sorry there are incorrect argument types");
		    				}
		    				
		    				
		    			}else {
		    				CharCallback.string=CharCallback.string.replace("#(ADD_AABB"+string+")","/[ERROR]/Sorry add aabb takes 5 arguments");
		    			}
		    			
		    		}else if(string.startsWith("REMOVE_AABB")) {
		    			
		    			string=string.substring(string.indexOf(" ")+1);
		    			int index=Integer.parseInt(string);
		    			ColisionHandeler.remove(index);
		    			 CharCallback.string=CharCallback.string.replace("#(REMOVE_AABB "+string+")","/[OK]/COL_REMOVED");
		    			
		    		}
		    	
		    		else if(string.startsWith("CALL")) {
		    			FunctionCaller function;
		    			string=string.substring(string.indexOf("CALL")+5);
		    			if(string.contains(",")) {
		    			String[] arguments=string.split(",");
		    			if(arguments.length==2) {
		    				String a=arguments[0];
		    				String b=arguments[1];
		    				if(b.startsWith("{") && b.endsWith("}")) {
		    					String[] bargs;
		    					if(b.contains(" ")) {
		    						bargs=b.split(" ");}
		    					else {
		    					    bargs=new String[]{b};
		    					}
		    					Object[] args=new Object[bargs.length];
		    				    Class[]  argTypes=new Class[args.length];
		    					for(int i=0;i<bargs.length;i++) {
		    						
		    						String bi=bargs[i];
		    					
		    					
		    						if(bi.contains("{")) {
		    							bi=bi.substring(bi.indexOf("{")+1);
		    						}
		    						if(bi.contains("}")) {
		    						  bi= bi.substring(0,bi.indexOf("}"));
		    						}
		    						
		    						if(bi.startsWith("/i")) {
		    							
		    							bi=bi.substring(bi.indexOf("/i")+2);
		    							args[i]=Integer.parseInt(bi);
		    							argTypes[i]=Integer.TYPE;
		    						}else if(bi.startsWith("/f")) {
		    							bi=bi.substring(bi.indexOf("/f")+2);
		    							args[i]=Float.parseFloat(bi);
		    							argTypes[i]=Float.TYPE;
		    							
		    						}else if(bi.startsWith("/s")) {
		    							bi=bi.substring(bi.indexOf("/s")+2);
		    							args[i]=bi;
		    							argTypes[i]=String.class;
		    							
		    							
		    						}else if(bi.startsWith("/d")) {
		    							bi=bi.substring(bi.indexOf("/d")+2);
		    							args[i]=Double.parseDouble(bi);
		    							argTypes[i]=Double.TYPE;
		    						}else {
		    							CharCallback.string=CharCallback.string.replace("#(CALL "+string+")","[ERROR] that is not a valid type only /s /i /f /d ");
		    						}
		    						
		    						
		    						
		    					}
		    					
		    					function=new FunctionCaller(a,args,argTypes);
		    					function.invoke();
		    					CharCallback.string=CharCallback.string.replace("#(CALL "+string+")","");
		    					
		    				}else {
		    					CharCallback.string=CharCallback.string.replace("#(CALL "+string+")","[ERROR] second arg needs to be a array of types");
		    				}
		    				
		    				
		    			}
		    			
		    			
		    			
		    			}else {
		    			function=new FunctionCaller(string);
		    			CharCallback.string=CharCallback.string.replace("#(CALL "+string+")"," ");
		    			function.invoke();
		    			
		    			}
		    		
		    		}
		    	
		    	
		  else{
		    		CharCallback.string=CharCallback.string.replace("#("+string+")","/[ERROR]/Sorry that is not a valid Command");
	    		}
		    }
	    		
}
	    		
		    	
		    }else if(!MoveInprogress) {
		    	InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_ENTER,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
		    	
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
		
		
		
	    
	    if(GetInput.getStateofButton(GLFW_KEY_S)==1) {
	    	
	    	source1.play(Heal);
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
	    
	    
	    }else if(H==1) {
	    	p.addItemToInventory(Items.spRestore);
	    	
	    	
	    }
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
					overworld=false;
					battleBox.reset();
					a1.removeAnimation();
					StartBox.reset();
					StateOfStartBOx=false;
					StartBattle(enemyField);
					
				
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
		
		     speed=5*speed;
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
					
						Vector2f newvec=new Vector2f(0,0);
						direction=(VectorMath.normalize(new Vector2f(speedx,speedy)));
						
						
					    velocity=new Vector2f(direction.x*speed,direction.y*speed);
						direction.add(velocity,newvec);
						direction=new Vector2f(speedx,speedy);
											
						 
						newvec.mul(50);
						
						newvec=new Vector2f((newvec.x*(float)DeltaTime),(newvec.y*(float)DeltaTime));
						
						c2=newvec;//this is the movement that needs to be added to the position vector

					    x=x+newvec.x;
						y=y+newvec.y;
						
						
						
					camx=-x;
					camy=-y;
					}
		}
					
				
		 
	}
	
		 
			 }
	
	
	
	private static void StartBattleRound(BattleEntity p,BattleEnemyField e) {
		
		turnOrder=BattleFormulas.calcuateTurnOrder(new BattleEntity[] {p},e.getEnemies());
	
	}
	
	private static BattleEntity StartBattleTurn(BattleEntity p,BattleEnemyField e) {
		if(turnOrder.isEmpty())  {
			StartBattleRound(p,e);
			
		}
		BattleEntity returnEntity=turnOrder.remove(0);
		if(!returnEntity.isEnemy()) {
		    Start.PlayersTurn=true;
		    battleBox.reset();
	 		battleBox.show();
	 		
		}else {
			Start.PlayersTurn=false;
			e.setCurrentEnemy((Enemy)returnEntity);
		}
		Start.TurnFinished=false;
		Start.MoveCalled=false;
		Start.MoveInprogress=false;
     
		   		
		return returnEntity;
	}
	
	
	private static void StartBattle(BattleEnemyField enemies) {
		
		
		for(int i=0;i<enemies.getAmountOfEnemies();i++) {
		Enemy e=enemies.getEnemy(i);
		
		e.setInventory(Start.enemyTestInventory);
		
		e.setHp(e.getMaxHP());
		e.setSp(e.getMaxsp());
		}
		
		Start.CurrentEnemyFeild=enemies;
		battleBox.show();
		Start.TurnFinished=false;
		Start.BattleEnded=false;
		
		
		TurnFinished=false;
	    
	
		  
	}
	
private static void EndBattleAsWin() {
		
		
	Start.BattleEnded=true;
	text1.setString("YOU WON!");
	 text1.drawString(100,40,.5f); 
		
	}
private static void EndBattleAsLoss() {
	
	Start.BattleEnded=true;
	
	text1.setString("YOU LOST :(");
	 text1.drawString(100,40,.5f); 
	
}	
	
   private static void battleupdate() {

	   if(Start.TurnFinished) {
		   DebugPrint("FINISHED");
			currentEntity=StartBattleTurn(p,enemyField);
			
	   }
	   boolean selectingEnemy=false;
	   if( Start.MoveCalled && !Start.StartBox.isActive() && !Start.currentlyUsedMove.isHeal()) {
		   selectingEnemy=true;}
		
	   
	   boolean allEnemiesDead=Start.CurrentEnemyFeild.updateField(selectingEnemy);
	  
	   
	   Enemy e=enemyField.getCurrentEnemy();
	   
	   
	 
	   
	   
	 
	   cam.setPosition(new Vector2f(0,0));
		 
	   
	   Vector2f position1=new Vector2f(-90,40);
	
	   
	 		Render.draw(background,new Vector2f(0),0,64*40,bg); 
	 	   
	 	 	 Render.Mirror();
	 	 	Render.draw(player,new Vector2f(-192,-20),0,64*1.5f,playerTex); //doing the same model and texture just for testing  will change that when we actually get the battle system down  
	 	    enemyField.draw(selectingEnemy);//draws all the enemies to the screen
	 	    
	 	    
	 		Items[] ITM= playersInventory.getItems();		
			UIElement[] elementlist=new UIElement[ITM.length];	
			
			for(int i=0;i<ITM.length;i++) {
				elementlist[i]=new UIStringElement(ITM[i].Item.getName()+"  "+playersInventory.getAmountOfItem(ITM[i]),new Vector2f(-54,5-(i*14)), .15f,Constants.BLACK,new FunctionCaller(GUIMEthods.UseItem,new Object[] {p,ITM[i]},new Class[] {p.getClass(),Items.class}));
			}

		
	 	
			//----------------------GAME--LOOP------------------------------
		
		
			
			
		battleBox.getUIState(2).relpaceALLActive(elementlist);
		battleBox.draw();
	 	if(selectingEnemy) { 
	 		  text1.setString("Selecting enemy");
	 		  text1.drawString(position1.x-100,position1.y+150, .64f);
	 	}
	 	 
	 	     //battleBox.getUIState().setOffsetPositionOnlist(arrowPosition);   	   
	 	   
	 	    
	 	    
	 	
	 	    
	 	  battleBox.setPosition(position1);
	 	
	 	
	 	  text1.setString("HP: "+Math.round(p.getHp())+"/"+Math.round(p.getMaxDEF()));
	 	  p.getHpbar().draw(new Vector2f(position1.x-100,position1.y+50),text1);

	 	  text1.setString("SP: "+Math.round(playersSPBAr.getValue())+"/"+Math.round(playersSPBAr.getMax()));
	 	  playersSPBAr.draw(new Vector2f(position1.x-110,position1.y+70), text1);
	 	 
	 	
	 	
	 	  
	 	  
	 	 if(p.getHp()==0) {
			   EndBattleAsLoss();
			   
			   
		   }
		   

	 	 if(allEnemiesDead) {
			   EndBattleAsWin();
			   
			   
	 	 }
	 	 
		if(Start.BattleEnded) {   
		Start.battleBox.hide();
		}
		else {  
	 	  
	 	  
	 	if(!Start.PlayersTurn && Proccesor.isUserInputallowed()) {
	 		Start.DebugPrint("hi'");
	 		if(Start.currentEntity.getHp()!=0) {
	 		((Enemy) Start.currentEntity).takeTurn(Start.CurrentEnemyFeild,p);
	 		}
	 		Start.TurnFinished=true;
	 		
	 	}else if(PlayersTurn && Proccesor.isUserInputallowed()){
	 		battleBox.show();
	 	}
	 	  
	 	   if(Start.MoveInprogress && PlayersTurn) {	
	 		   
	 		   battleBox.hide();
	 		
	 	 
	 		
	 		   
	 		  
	 		  Moves move=p.getLastUsedMove();
	 		  
	 		  if(move.isTimedButton()) {
	 			  int State= Start.Button.update();
	 		  if(State!=TimedButton.NOTPUSHED) {
	 			
	 			   Start.TurnFinished=true;
	 				  
	 			   if(!p.getLastUsedMove().isHeal()) {
	 				   
	 				   float Damage=BattleFormulas.CalculateDamage(p, e, State, p.getLastUsedMove().getDamage());
	 				  
	 				   
	 				   
	 				   
	 				   Proccesor.addComandtoItorator(new DrawString(Math.round(Damage)+"!",new Vector2f(100,40),.5f,true,.5f));			
	 				
	 			        e.decreseHp(Damage);
	 			   
	 			   }else {
	 				
	 				   
	 				   float health=BattleFormulas.CalculateHeath(p, State, p.getLastUsedMove().getDamage());
	 				  
	 				  Proccesor.addComandtoItorator(new DrawString(Math.round(health)+"!",new Vector2f(100,40),.5f,true,.5f));			
	 				   
	 				p.IncreseHp(health);   
	 				   
	 			   }
	 			   
	 			   
	 			   
	 			   
	 		   }}else {
	 			
	 			
	 			   Start.TurnFinished=true;
	 				
	 			   if(!move.isHeal()) {
	 				   
	 				  float Damage=BattleFormulas.CalculateDamage(p, e,2, p.getLastUsedMove().getDamage());
	 				   
	 				   
	 				   Start.textR.setString(Math.round(Damage)+"!");
	 				   Proccesor.addComandtoQueue(new DrawModel(textR.getTextModel(),textR.getLoader().getTex(),new Vector2f(100,40),.5f,1,true));			
	 				
	 			        e.decreseHp(Damage);
	 			   
	 				   
	 				   
	 			   }else {
	 				
	 				   
	 				   float health=BattleFormulas.CalculateHeath(p, 2, p.getLastUsedMove().getDamage());
	 				  textR.setString(Math.round(health)+"!");
	 				  Proccesor.addComandtoQueue(new DrawModel(textR.getTextModel(),textR.getLoader().getTex(),new Vector2f(100,40),.5f,1,true));			
	 				   
	 				p.IncreseHp(health);   
	 				 
	 			   }
	 			   
	 			   
	 			   
	 			   
	 			   
	 		   }
	 			   
	 			   
	 		   
	 		   
	 		   
	 		   
	 		   
	 		   
	 	   }else if(Start.MoveCalled==true && Start.PlayersTurn) {
				 
					  if( Start.currentlyUsedMove.isHeal()){
						  GUIMEthods.UseNonAttack(Start.currentlyUsedMove, p);
					  }
					  battleBox.hide();
				   }
	 	   
	 	   
	 	   
	 	}     
	
	 	  
	   
	   
   }
	
	 	   
	 	   


	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	private static void fps() {
	
		    Render.disable();
		    canRender=false;//don't allow rendering until time
			time2=Timer.getTIme();//gets current time
		    passed=time2-time;//this is the time since the last time through the loop
		    AnimationHandler.update();
			
		    unproccesed+=passed;//this is the time that we have not rendered anything
			Ti+=passed;
			TT+=passed;
			frameTime+=passed;//this is the time since the last second
			time=time2;
		
			
			
			
		
			if(unproccesed>=framCap) {//when the time we have not rendered is greater than or equal to our frame target we allow rendering
				
				DeltaTime=Timer.getTIme()-lastFrame;
				//System.out.println(DeltaTime);
				unproccesed-=framCap;//this is like reseting 
				//take input
				
				
				
				if(Proccesor.isUserInputallowed()) {
				 Inputupdate();}else {
						w.update();//this is needed to still allow exiting
				       
				 }
				 
			     canRender=true;//now we render	
			 	frames++;
			 	lastFrame=Timer.getTIme();
			 
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
			    	 
			    	  
			    	  Render.resetDrawCalls();
			    	  frameTime=0;
			    	  frames=0;  
			    	  
			    	  
			    	  
			      }
			      
			     
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
	unproccesed=0;
    frameTime=0;
    frames=0;
	framCap=1.0/TARGETFPS;
	
	
	
	InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
	
	
	
	
	

	a1=new Animate(7,player,sloader,0,7);
 

}	

public static void ShowBox(Vector2f position) {
   ShowBox.setPosition(position.add(new Vector2f(Rendercamx,Rendercamy),new Vector2f(0)));
   ShowBox.show();
    	
}


	
}
