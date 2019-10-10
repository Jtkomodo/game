package gameEngine;
import java.io.IOException;
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
import sun.security.util.Debug;
import Data.Pcs;
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
    public static Input I,DebugI;
    public static Fontloader font;
    public static boolean canRender,overworld=true,test=false,testcol,circCol,LOG=true,DEBUGCOLISIONS=true,HideSprite=false,DebugPrint=false,DebugdrawString=true,showFps=true,Debug=false;
    public static double framCap,time,time2,passed,unproccesed,frameTime=0,lastFrame=0,DeltaTime,animateTime,Ti,TT,seconds,amountInSeconds;
    public static Texture tex,MAP,bg,playerTex,COLTEX,piont,piont2,col2,circleCol1,circleCol2,textbox,testSprite;
    public static float x2,y2,camx,camy,x,y,Playerscale=64;
    public static Model background,player,textboxM,Arrow;
    public static BatchedModel testM;
    public static TextBuilder textB,textA,textC,textD,text1,textDrawCalls;
    public static Vector2f currentmovement,c2,oldpos,direction,BattleBoxPosition;
    public static Player p;
    public static CircleColision circle1, circle2;
    public static float[] uvtextbox,uvArrow,vert; 
    public static UIBox battleBox;
   
   
  

    
    public static AABB playerCol,Col,COl2;
    public static Animate a1;
    public static SpriteSheetLoader sloader;
    
    public static Fontloader aakar;
    
    public static boolean facingLeft,running;
    
    
    
	public static void main(String[] args) {
	
	
		
		sloader= new SpriteSheetLoader("playerSpriteSheet",138);//loads the sprite sheet info
		
		DebugPrint("Starting.....");
	double startTime=Timer.getTIme();
	
	
	
	
	
	
	
	
	
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
		TextBuilder textCircle= new TextBuilder("aakar",512);
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
		
		player= new Model(vertPlayer,uvPlayer,ind);
	    background=new Model(vert,uvBg,ind);
	    textboxM=new Model(vertText,uvtextbox,ind);
	    Arrow=new Model(vert,uvArrow,ind);
		
	
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
		
		UIStringElement elements[]= {new UIStringElement("moves",new Vector2f(-17,15), .15f,Constants.BLACK),
				new UIStringElement("bag",new Vector2f(-35,-5), .15f,Constants.BLACK),
				new UIStringElement("specials",new Vector2f(10,-5), .15f,Constants.BLACK)
		};
		UIStringElement elements2[]= {new UIStringElement("---moves---",new Vector2f(-28.5f,23), .15f,Constants.BLACK,false),
				new UIStringElement(" ",new Vector2f(-54,5), .15f,Constants.BLACK),new UIStringElement(" ",new Vector2f(15,5), .15f,Constants.BLACK),
				new UIStringElement(" ",new Vector2f(-54,-8), .15f,Constants.BLACK),new UIStringElement(" ",new Vector2f(15,-8), .15f,Constants.BLACK)
			
		};
		
		
		UIBoxState boxs[]= {new UIBoxState(new Vector2f(),71,28,elements,textbox,true),new UIBoxState(new Vector2f(100,10f),71,28,elements2,textbox)
		};
		
		battleBox=new UIBox(new Vector2f(100,0),boxs);//this is the UIbox for the battle UI
    //----------------------GAME--LOOP------------------------------
		while(!w.isExited() && !I.IsEscapePushed()) {
			oldpos=new Vector2f(x,y);
		fps();    
	    
	if(canRender) {
		
	  // TextureUpdate(MAP)
Model.enable();
BatchedModel.enable();
	
		
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
		SpriteUpdate(player,playerTex,x,y,Playerscale,facingLeft);
		textforTILES.setString("Tiles: "+loader.getTilesrenderd());
		textB.UIDebugdrawString(screencoordx-300,screencoordy-220,.24f);
		textC.UIDebugdrawString(screencoordx+100,screencoordy-220,.24f);
         textforTILES.UIDebugdrawString(screencoordx,(480/2)+ screencoordy-20, .24f);

}else {
	//---------------------battle loop---------------------
	battleupdate();
    screencoordx=0;
	screencoordy=0;
	
	
   






}

textDrawCalls.setString("Drawcalls(S:"+drawcalls+ " F:"+drawcallsFrame+")");
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
		
		
	 I=new Input(w);	
       //load our shaders 
	 DebugPrint("Making Shader Program....");
		s= new ShaderProgram(Shader);
		
		//bind shader
		s.bind();	
		
		
	}

	private static void Inputupdate() {
		
		w.update();//this is needed to actually poll events from keyboard 
		I.findKeys();
	    dKeys=I.getDirectionalInput();
	    
	   testKey=I.getStateofButton(GLFW_KEY_T);
	   
	   float speed=1;
	    		
		byte stateofFullscreen=I.stateOfFullscreen();
	      if(stateofFullscreen==1) {//if the fuscreenCode is just pressed toggle full screen
			 w.toggleFullscreen();}
	  
		
		
		if( I.getStateofButton(GLFW_KEY_F1)==1) {
	if(Start.Debug==false) {
			DEBUGCOLISIONS=true;DebugdrawString=true;showFps=true;
	Start.Debug=true;
	}

	else{
		DEBUGCOLISIONS=false;DebugdrawString=false;showFps=false;
	Start.Debug=false;
	}

	}
		
		if( I.getStateofButton(GLFW_KEY_F2)==1) {
			if(Start.DebugPrint==false) {
				
			Start.DebugPrint=true;
			}

			else{
				
			Start.DebugPrint=false;
			}

			}
		
		if( I.getStateofButton(GLFW_KEY_F3)==1) {
			if(Start.DEBUGCOLISIONS==false) {
				
			Start.DEBUGCOLISIONS=true;
			}

			else{
				
			Start.DEBUGCOLISIONS=false;
			}

			}
		
		if( I.getStateofButton(GLFW_KEY_F4)==1) {
			if(Start.DebugdrawString==false) {
				
			Start.DebugdrawString=true;
			}

			else{
					
			Start.DebugdrawString=false;
			}

			}
		if( I.getStateofButton(GLFW_KEY_F12)==1) {
			if(showFps==false) {
				showFps=true;
			
			}

			else{
				showFps=false;	
			
			}

			}
					
			
	if(testKey==2) {
	//	x=0;y=0;
		if (overworld==false) {
			overworld=true;
	
			
		}else {
			overworld=false;
			a1.Stop();
			battleState=0;
			arrowPosition=0;
			sprite=0;
			
		}
		
		
		
	}
	

	
	
	
	if(overworld) {
		
		        if(I.getStateofButton(GLFW_KEY_W)==1 || I.getStateofButton(GLFW_KEY_W)==3) {
		        	speed=5;
		        	running=true;
		        	
		        }else {
		        	speed=1f;
		        	a1.changefps(7);
		        	running=false;
		        }
		     float speedx=0,speedy=0;
		
		     speed=5*speed;
				if((dKeys>>4 &0x01)==1) {//if c is pushed
					if((dKeys & 0x01)==1) {//up
						camy-=10;

						
					}
					if((dKeys>>1 & 0b001)==1) {//down
						camy+=10;
						
					}if((dKeys>>2 & 0b001)==1) {//left
						camx-=10;
						
					}if((dKeys>>3 & 0b001)==1) {//right
						camx+=10;}	 
					}else{ 
						
						
						
						if((dKeys & 0x01)==1) {//up
						
							speedy=1;
							 a1.Play();
							
						}
						if((dKeys>>1 & 0b001)==1) {//down
						
							speedy=-1;
							 a1.Play();
									
						}if((dKeys>>2 & 0b001)==1) {//left
					        facingLeft=true;
					        a1.Play();
							speedx=1;
							
						}if((dKeys>>3 & 0b001)==1) {//right
						
						   speedx=-1;
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
		 
	}else {
		
	
		if(I.getStateofButton(GLFW_KEY_S)==1) {
			   if(sprite==0) {
			    	 sprite=1;
			    	 
			     }else {
			    	 sprite=0;
			     }
			arrowPosition=0;
			battleState=0;
		}
		
		
		if(I.getStateofButton(GLFW_KEY_UP)==1) //up
		BattleInputUp();
			
		
		if(I.getStateofButton(GLFW_KEY_DOWN)==1) //down
			BattleInputDown();
		
		if(I.getStateofButton(GLFW_KEY_LEFT)==1) //left
			BattleInputleft();
			
		if(I.getStateofButton(GLFW_KEY_RIGHT)==1) //right
			BattleInputRight();
		
		if(I.getStateofButton(GLFW_KEY_ENTER)==1) //up
			BattleInputSelect();
				
		if(I.getStateofButton(GLFW_KEY_BACKSPACE)==1) //up
			BattleInputBack();
					
		
		
	}
		 
			 }
	
	
   private static void battleupdate() {
	   cam.setPosition(new Vector2f(0,0));
		 
	   
	   Vector2f position1=new Vector2f(-90,40);
	   Vector2f position2=new Vector2f(-110,-98);
	   
	 		Renderer.draw(background,new Vector2f(0),0,64*40,bg); 
	 	   
	 	 	 
	 	 	SpriteUpdate(player,playerTex,-192,-20,64*1.5f,true); //doing the same model and texture just for testing  will change that when we actually get the battle system down  
	 	 	 SpriteUpdate(player,playerTex,-222,-128-20,64*1.5f,true);
	 	 	 SpriteUpdate(player,playerTex,222-20,-128+40,64*1.5f,false);
	 	    
	 	 	 battleBox.drawAndSetState(battleState);
	 	     battleBox.getUIState().setOffsetPositionOnlist(arrowPosition);   	   
	 	   
	 	    
	 	    
	 	    
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
	
		    Model.disable();
		    canRender=false;//don't allow rendering until time
			time2=Timer.getTIme();//gets current time
		    passed=time2-time;//this is the time since the last time through the loop
			a1.updateTime();  		
			
		    unproccesed+=passed;//this is the time that we have not rendered anything
			Ti+=passed;
			TT+=passed;
			frameTime+=passed;//this is the time since the last second
			time=time2;
		
			
			
			
		
			if(unproccesed>=framCap) {//when the time we have not rendered is greater than or equal to our frame target we allow rendering
				
				DeltaTime=Timer.getTIme()-lastFrame;
				//System.out.println(DeltaTime);
				unproccesed-=framCap;//this is like reseting but instead if it is way over for some reason it makes sure to allow all rendering missed 
			       
				//take input
				 Inputupdate();
				 
			     canRender=true;//now we render	
			 	frames++;
			 	lastFrame=Timer.getTIme();
			 
			      if(frameTime>=1.0) {//if a second has passed print fps
			    	 
			    	  DebugPrint("FPS:"+frames+"-------------------------------");
			    	 
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
		
private static void BattleInputUp() {
	
	
	switch(battleState) {
	
	case 0:
	if(arrowPosition==2 || arrowPosition==1) {
		arrowPosition=0;
	}
	break;
	
	case 1:
		if(arrowPosition==2) {
			arrowPosition=0;
			
		}
		
		if(arrowPosition==3) {
			arrowPosition=1;
			
		}
		
		
		
	break;	
	
	
	
	}
}

private static void BattleInputDown() {
switch(battleState) {

case 0:
if(arrowPosition==0) {
	arrowPosition=1;
}

break;	

case 1:
	if(arrowPosition==0) {
		arrowPosition=2;
	}
	if (arrowPosition==1) {
		arrowPosition=3;
	}
	
	
break;	


	
	}
	
	
}

private static void BattleInputleft() {
switch(battleState) {
case 0:
if(arrowPosition==0) {
	arrowPosition=1;
}  else if(arrowPosition==2) {
	arrowPosition=1;
}
break;

case 1:
	if(arrowPosition==1) {
		arrowPosition=0;
		
	}
	if(arrowPosition==3) {
		arrowPosition=2;
	}
	
	
break;	



	
	}
	
}
	
private static void BattleInputRight() {
	
switch(battleState) {
case 0:
if(arrowPosition==1 || arrowPosition==0) {
	arrowPosition=2;
	
}
break;

case 1:
	if( arrowPosition==0) {
		arrowPosition=1;
		
	}
	if(arrowPosition==2) {
		arrowPosition=3;
	}
	
	
break;	
	
	





	}
}


private static void BattleInputSelect() {
	
switch(battleState) {

case 0:
	
BattleInputSelect0();
break;




	
	
	}
}	

private static void BattleInputBack() {
switch(battleState) {
case 1:
	battleState=0;
	arrowPosition=0;
break;	

	
	
	
	}
	
}		




private static void BattleInputSelect0() {//this is for the select from the main screen case ) means we are on moves 2 bag 3 specials
	
switch(arrowPosition) {

case 0:
	battleState=1;
	arrowPosition=0;
break;	




	
	
	}
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
