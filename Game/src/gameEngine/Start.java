package gameEngine;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.lwjgl.glfw.GLFW.*;
import java.util.Map;
import static java.lang.Math.*;

import static org.lwjgl.opengl.GL11.*;
import org.joml.Matrix4f;
import  org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import Collisions.AABB;
import Collisions.CircleColision;
import Collisions.ColisionHandeler;
import textrendering.Fontloader;
import textrendering.TextBuilder;
import Data.Constants;
import Data.Moves;
import battleClasses.Player;
import entitys.DynamicEntity;
import guis.GUIMEthods;
import guis.TextureElement;
import guis.UIBox;
import guis.UIBoxState;
import guis.UIElement;
import guis.UIStringElement;
import input.GetInput;
import sun.security.util.Debug;
import Data.Pcs;
import  Scripter.Proccesor;
import ScripterCommands.Wait;
import ScripterCommands.animate;
import animation.Animate;
import animation.AnimationHandler;
public class Start {
    
	
	public static Window w;
	public static final int width=640,height=480;
 
	public static int location,Projection,Color,RTS,frames=0,j=0,i=0,fps,gridx,gridy,Aframes,drawcalls=0,drawcallsFrame=0;;
    public static byte dKeys,testKey;
    
    private static int battleState,sprite,arrowPosition;
    
    
    public static ShaderProgram s;
    public static float scaleOfMapTiles=128,Rendercamx,Rendercamy;
    public static int amountWidth=Math.round((width/scaleOfMapTiles)),amountHeight=Math.round((height/scaleOfMapTiles));
    public static Camera cam,DebugCam;
    public static float screencoordx=0,screencoordy=0;
    public static GetInput I;
    public static Fontloader font;
    public static boolean canRender,overworld=true,test=false,testcol,circCol,LOG=true,DEBUGCOLISIONS=true,HideSprite=false,DebugPrint=true,DebugdrawString=true,showFps=true,Debug=false,StateOfStartBOx=false;
    public static double framCap,time,time2,passed,unproccesed,frameTime=0,lastFrame=0,DeltaTime,animateTime,Ti,TT,seconds,amountInSeconds;
    public static Texture tex,MAP,bg,playerTex,COLTEX,piont,piont2,col2,circleCol1,circleCol2,textbox,testSprite;
    public static float x2,y2,camx,camy,x,y,Playerscale=64;
    public static Model background,player,textboxM,Arrow;
    public static BatchedModel testM;
    public static TextBuilder textB,textA,textC,textD,text1,textDrawCalls;
    public static Vector2f currentmovement,c2,oldpos,direction,BattleBoxPosition;
    public static Player p;
    public static CircleColision circle1, circle2;
    public static float PHP;
    public static float[] uvtextbox,uvArrow,vert; 
    public static UIBox battleBox,StartBox;
    public static DynamicEntity Ply;
   public static int amountOfMoves,amountOfSPMoves,function;
   
   public static double startTime;

    
    public static AABB playerCol,Col,COl2;
    public static Animate a1;
    public static SpriteSheetLoader sloader;
    
    public static Fontloader aakar;
    
    public static boolean facingLeft,running;
    
    
    
	public static void main(String[] args) {
	
	
		
		
		
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

	
		
		
		
		
			
		int[] ind= {
			0,1,2,
			2,3,0	
				
		};
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
		
		
		
		aakar=new Fontloader("aakar",512);    
		TextBuilder textforTILES=new TextBuilder(aakar);
	    textB= new TextBuilder(aakar); 
		textA= new TextBuilder(aakar);
		textC= new TextBuilder(aakar);
		textD= new TextBuilder(aakar);
		text1= new TextBuilder(aakar);
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
		WorldLoader worldLoader=new WorldLoader("map3", 64,64, cam);
		MapLoader loader=new MapLoader(worldLoader.Getmap(),scaleOfMapTiles);
        PositionTest postest=new PositionTest(worldLoader.Getmap(),scaleOfMapTiles);	
		
	
		DebugPrint("Settign Colisions....");
		//playerCol=new AABB(new Vector2f(0,0),15,44,0);
		playerCol=new AABB(new Vector2f(0,0),15,44,0);
		Col=new AABB(new Vector2f(3968-64,1280-64),64,128,0);
        COl2=new AABB(new Vector2f(-64,1026-64),2048,64,0);
	
	   
		
		
		initializeFPS();
		x=100;
		y=100;	
		double ENDTime=Timer.getTIme();
		double timeTaken=ENDTime-startTime;
		DebugPrint("Tiem to load--- "+timeTaken+" seconds");
		
		DebugPrint("Starting Game loop.....");
		
		
		s.loadVec4(Color, new Vector4f(1,1,1,1));
     
		 p=new Player(Pcs.C1.getAtk(),Pcs.C1.getDef(),Pcs.C1.getHp(),Pcs.C1.getMoves());
		
		try {
		 
	UIStringElement StartElements[] ={
		
		new UIStringElement("Stats",new Vector2f(-17,35),.2f,Constants.BLACK,"DebugPrint",new Object[] {"stats"},new Class[] {String.class},Start.class),
		new UIStringElement("Bag",new Vector2f(-17,15),.2f,Constants.BLACK,1),
		new UIStringElement("Save",new Vector2f(-17,-5),.2f,Constants.BLACK,GUIMEthods.saveGAME,null),
		new UIStringElement("Quit",new Vector2f(-17,-25),.2f,Constants.BLACK,GUIMEthods.exitWINDOW,null)
		};
	
	
	
	UIStringElement BagElements[]= {new UIStringElement("-------bag------",new Vector2f(-38,40), .15f,Constants.BLACK),
			new UIStringElement("test",new Vector2f(-54,5), .15f,Constants.BLACK),new UIStringElement("test",new Vector2f(15,5), .15f,Constants.BLACK),
			new UIStringElement("test",new Vector2f(-54,-8), .15f,Constants.BLACK),new UIStringElement("test",new Vector2f(15,-8), .15f,Constants.BLACK)
		
	};
		 
	UIBoxState StartBoxs[]= {
			new UIBoxState(new Vector2f(0,0),30,50,StartElements,Start.COLTEX,Constants.COL_COLOR_BLUE.add(new Vector4f(0,0,50,-50),new Vector4f(0))),
			new UIBoxState(new Vector2f(-200,0),w.getWidth()-200,w.getHeight()-200,BagElements,Start.textbox,Constants.COL_COLOR_BLUE.add(new Vector4f(0,0,50,80),new Vector4f(0)))
	};
		 
		 
		 
   StartBox=new UIBox(new Vector2f(screencoordx,screencoordy),StartBoxs); 
	

		 
		 
		 
		 
		UIStringElement MenuElements[]= {new UIStringElement("moves",new Vector2f(-17,15), .15f,Constants.BLACK,1),
				new UIStringElement("bag",new Vector2f(-35,-5), .15f,Constants.BLACK,2),
				new UIStringElement("specials",new Vector2f(1,-5), .15f,Constants.BLACK,3)
		};
		UIElement MoveElements[]= {new UIStringElement("---moves---",new Vector2f(-28.5f,23), .15f,Constants.BLACK),
				new UIStringElement(p.getmoves()[0].getName(),new Vector2f(-54,5),.15f,Constants.BLACK,GUIMEthods.useMOVE,new Object[] {p,p.getmoves()[0].name()}),new UIStringElement("test ",new Vector2f(15,5), .15f,Constants.BLACK,GUIMEthods.useMOVE,new Object[] {p,"test"}),
				new TextureElement(playerTex,32,46,new Vector2f(-54,-8),new Vector2f(0,0),.15f),new UIStringElement("test ",new Vector2f(15,-8), .15f,Constants.BLACK,GUIMEthods.useMOVE,new Object[] {p,"test"})
			
		};
		
		
		
		UIStringElement SPElements[]= {new UIStringElement("---specials---",new Vector2f(-34,23), .15f,Constants.BLACK),
				new UIStringElement(p.getspmoves()[0].getName()+" "+(p.getspmoves()[0]).getCost()+"sp",new Vector2f(-54,5), .15f,Constants.BLACK),new UIStringElement("test",new Vector2f(15,5), .15f,Constants.BLACK),
				new UIStringElement("test",new Vector2f(-54,-8), .15f,Constants.BLACK),new UIStringElement("test ",new Vector2f(15,-8), .15f,Constants.BLACK)
			
		};
		
		
		UIBoxState boxs[]= {new UIBoxState(new Vector2f(),71,28,MenuElements,textbox,true),new UIBoxState(new Vector2f(150,10f),71,28,MoveElements,textbox),
				new UIBoxState(new Vector2f(150,10),71,50,BagElements,Start.COLTEX,Constants.COL_COLOR_BLUE.add(new Vector4f(0,0,29,50),new Vector4f(0))),new UIBoxState(new Vector2f(150,10f),71,28,SPElements,textbox)
		};
		
		
		
		
		
		
		
		battleBox=new UIBox(new Vector2f(100,0),boxs);//this is the UIbox for the battle UI

		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(300);
		}
			
		
		
		//----------------------GAME--LOOP------------------------------
		while(!w.isExited()) {
			oldpos=new Vector2f(x,y);
		fps();    
	    
	if(canRender) {
		
	  // TextureUpdate(MAP)
Renderer.enable();//enables render

	
		
	//if(test==false) {
	
	if(overworld==true) {		 
		
	    playerCol.setPosition(new Vector2f(x,y));
	
			Vector2f vector=updateColisions();
			
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
	       
	    

	  
	
	      textB.setString("x="+x+" y="+y);
	      Vector2f camPos=cam.getPosition();
	     Rendercamx=-(camPos.x);
	     Rendercamy=-(camPos.y);
	      Vector2f newvec=postest.findPositionOnMap(Rendercamx,Rendercamy);
	      gridx= Math.round(newvec.x);gridy=Math.round(newvec.y);
	    drawmap(loader,gridx,gridy);		     
	    Col.debug();
	    playerCol.debug();
	    

	    COl2.debug();
	      textD.setString("circCol:"+circCol);
	      textC.setString("xmap="+gridx+" ymap="+gridy);
	    
	   
	    
	      
		if(HideSprite==false) 
		a1.drawAnimatedModel(new Vector2f(x,y),0,Playerscale,!facingLeft);	
		//SpriteUpdate(player,playerTex,x,y,Playerscale,facingLeft);
		textforTILES.setString("Tiles: "+loader.getTilesrenderd());
		textB.UIDebugdrawString(screencoordx-300,screencoordy-220,.24f);
		textC.UIDebugdrawString(screencoordx+100,screencoordy-220,.24f);
         textforTILES.UIDebugdrawString(screencoordx,(480/2)+ screencoordy-20, .24f);
         if(StartBox.isActive()) {
        	 StartBox.setPosition(new Vector2f(screencoordx+300,screencoordy));
        	StartBox.draw(); 
         }

}else {
	//---------------------battle loop---------------------
	battleupdate();
    screencoordx=0;
	screencoordy=0;
	
	
   






}
	Proccesor.proccesCommands(time);
textDrawCalls.setString("Drawcalls(S:"+drawcalls+ "\nF:"+drawcallsFrame+")");
textA.setString("fps="+(int)fps);
if(showFps)
textA.UIdrawString((640/2)+screencoordx-100,(480/2)+screencoordy-20,.24f);
textDrawCalls.UIDebugdrawString((640/2)+screencoordx-625,(480/2)+screencoordy-20,.24f);

		    w.render();
		    w.clear();
		    loader.flushModel();
			
		}
		}
//--------------------------------------------------------------	
		DebugPrint("Closing.....");
		w.destroy();
		
		
	}

	
		
	

	private static void createWindow(String Shader) {
		

		
		DebugPrint("Making Camera....");
			//set camera
			cam= new Camera(width,height);
		w=new Window(width,height,cam,"Game");
		
		
	 I=new GetInput(w);	
       //load our shaders 
	 DebugPrint("Making Shader Program....");
		s= new ShaderProgram(Shader);
		
		//bind shader
		s.bind();	
		
		
	}

	private static void Inputupdate() {
	
		w.update();//this is needed to actually poll events from keyboard 
		
		battleBox.Update(I);
		StartBox.Update(I);
	   
	    int UP=I.getStateofButton(GLFW_KEY_UP),DOWN=I.getStateofButton(GLFW_KEY_DOWN),
	    LEFT=I.getStateofButton(GLFW_KEY_LEFT),RIGHT=I.getStateofButton(GLFW_KEY_RIGHT),
		
		F1=I.getStateofButton(GLFW_KEY_F1),F2=I.getStateofButton(GLFW_KEY_F2),
		F3=I.getStateofButton(GLFW_KEY_F3),F4=I.getStateofButton(GLFW_KEY_F4),
	    F12=I.getStateofButton(GLFW_KEY_F12),C=I.getStateofButton(GLFW_KEY_C),Y=I.getStateofButton(GLFW_KEY_Y),W=I.getStateofButton(GLFW_KEY_W)
		,ESCAPE=I.getStateofButton(GLFW_KEY_ESCAPE),ENTER=I.getStateofButton(GLFW_KEY_ENTER),
		BACKSPACE=I.getStateofButton(GLFW_KEY_BACKSPACE),S=I.getStateofButton(GLFW_KEY_S),CONTROLRIGHT=I.getStateofButton(GLFW_KEY_RIGHT_CONTROL),
		CONTROLLEFT=I.getStateofButton(GLFW_KEY_LEFT_CONTROL),F=I.getStateofButton(GLFW_KEY_F),H=I.getStateofButton(GLFW_KEY_H);
		
		
		
		
		
		
	    if(H==1) {
	    	
	    	Animate animation=new Animate(7,player, sloader, 0, 7);
	         Proccesor.addComandtoItorator(new animate(animation,oldpos, 0,32,12,false));
	    }
	    
	    
	    
		
		
		
		if(testKey==2) {
			//	x=0;y=0;
				if (overworld==false) {
					overworld=true;
			         battleBox.hide();
					
				}else {
					overworld=false;
					a1.Stop();
					StartBox.reset();
					StateOfStartBOx=false;
					battleState=0;
					arrowPosition=0;
					sprite=0;
					battleBox.show();
				
				}
				
				
				
			}
			
		
		
		
		
		
		
		
		
		testKey=I.getStateofButton(GLFW_KEY_T);
	   
	   float speed=1;
	    		
		
	      if(((CONTROLRIGHT==1 || CONTROLRIGHT==3)||(CONTROLLEFT==1||CONTROLLEFT==3)) && (F==1)) {//if the fuscreenCode is just pressed toggle full screen
			 w.toggleFullscreen();}
	  
		
		
		if( F1==1) {
	if(Start.Debug==false) {
			DEBUGCOLISIONS=true;DebugdrawString=true;showFps=true;
	Start.Debug=true;
	}

	else{
		DEBUGCOLISIONS=false;DebugdrawString=false;showFps=false;
	Start.Debug=false;
	}

	}
		
		
		
		
		
		
		
		
		
		
	
		if(Y==1) {
			Proccesor.addComandtoQueue(new Wait(12d));
			}
		
		
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
			if(Start.DebugdrawString==false) {
				
			Start.DebugdrawString=true;
			}

			else{
					
			Start.DebugdrawString=false;
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
		
		
	if(ESCAPE==1) {
			if(StartBox.isActive()) {
				StartBox.hide();
			}else {
				StartBox.show();
				a1.Pause();
			    StartBox.reset();
			}
			
		}
		
		if(!UIBox.isOpened()) {
		
		
		
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
						
						
						Vector2f velocity=new Vector2f(direction.x*speed,direction.y*speed);
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
	
	
	
	private static void StartBattle(Player Player) {
		
		amountOfMoves=Player.getAmountofMoves();
		amountOfSPMoves=Player.getAmountofSPMoves();
	    PHP=Player.getHp();
	 
		
	}
	
private static void EndBattle(Player Player) {
		
		
	    Player.setHp(PHP);;
	    
		
	}
	
	
   private static void battleupdate() {
	   cam.setPosition(new Vector2f(0,0));
		 
	   
	   Vector2f position1=new Vector2f(-90,40);
	   Vector2f position2=new Vector2f(-110,-98);
	   
	 		Renderer.draw(background,new Vector2f(0),0,64*40,bg); 
	 	   
	 	 	 
	 	 	SpriteUpdate(player,playerTex,-192,-20,64*1.5f,true); //doing the same model and texture just for testing  will change that when we actually get the battle system down  
	 	 	 SpriteUpdate(player,playerTex,-222,-128-20,64*1.5f,true);
	 	 	 SpriteUpdate(player,playerTex,222-20,-128+40,64*1.5f,false);
	 
	 	 	 battleBox.draw();
	 	     //battleBox.getUIState().setOffsetPositionOnlist(arrowPosition);   	   
	 	   
	 	    
	 	    
	 	    
	 	   switch(sprite) {
		   case 0:
			 BattleBoxPosition=position1;
			   
		   break;
		   case 1:
			   BattleBoxPosition=position2;
			  
			break;   
			   
			   
			default:
				 BattleBoxPosition=position1;
		       
		   }
	 	    
	 	  battleBox.setPosition(BattleBoxPosition);
	 	    
	 	    
	 	    
	 
	 	  
	   
	   
   }
	
	 	   
	 	   


	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	 	   
	private static void fps() {
	
		    Renderer.disable();
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
			    	 
			    	  //DebugPrint("FPS:"+frames+"-------------------------------");
			    	 
                      fps=frames;
			    	//reset frame time and frames  
			    	  
			    	
			    	  drawcalls=Renderer.getDrawcalls();
			    
			    	  drawcallsFrame=drawcalls/frames;
			    	  Renderer.resetDrawCalls();
			    	  frameTime=0;
			    	  frames=0;  
			      }
			      
			     
					}
			
			
	}
private static void SpriteUpdate(Model sprite,Texture tex,float x,float y,float spritescale,boolean mirror){
if(mirror)
Renderer.Mirror();
	
Renderer.draw(sprite,new Vector2f(x,y), 0, spritescale, tex);
    
    
    
    
    
}
private static void SpriteUpdate(Model sprite,Texture tex,float x,float y,float angle,float spritescale,boolean mirror){
if(mirror)
Renderer.Mirror();
	
Renderer.draw(sprite,new Vector2f(x,y), angle, spritescale, tex);
    
    
    
    
    
}
private static void SpriteUpdate(Model sprite,Texture tex,float x,float y,float angle,float spritescale,Vector4f color,boolean mirror){
if(mirror)
Renderer.Mirror();
	
Renderer.draw(sprite,new Vector2f(x,y), angle, spritescale, tex,color);
    
    
    
    
    
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




private static Vector2f updateColisions() {
	
	  Vector2f cs=new Vector2f(x,y);
    
   
	   cs =ColisionHandeler.CheckAndGetResponse(playerCol,COl2,cs,oldpos, c2, direction);
	    
	   cs =ColisionHandeler.CheckAndGetResponse(playerCol, Col,cs,oldpos, c2, direction);
	   cs =ColisionHandeler.CheckAndGetResponse(playerCol,COl2,cs,oldpos, c2, direction);

	   
	   
	   return cs;
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
	framCap=1.0/60;

	a1=new Animate(7,player,sloader,0,7);
 

}	
	
}
