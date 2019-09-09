package gameEngine;
import java.io.IOException;
import static org.lwjgl.glfw.GLFW.*;
import java.util.Map;
import static java.lang.Math.*;

import static org.lwjgl.opengl.GL11.*;
import org.joml.Matrix4f;
import  org.joml.Vector2f;
import org.joml.Vector4f;

import Collisions.AABB;
import Collisions.CircleColision;
import textrendering.Fontloader;
import textrendering.TextBuilder;
import Data.Constants;
public class Start {
    
	
	public static Window w;
	public static final int width=640,height=480;
    public static int location,Projection,Color,RTS,frames=0,j=0,i=0,fps,gridx,gridy,Aframes,drawcalls=0,drawcallsFrame=0;;
    public static byte dKeys,testKey;
    public static ShaderProgram s;
    public static float scaleOfMapTiles=128,Rendercamx,Rendercamy;
    public static int amountWidth=Math.round((width/scaleOfMapTiles)),amountHeight=Math.round((height/scaleOfMapTiles));
    public static Camera cam;
    public static float screencoordx=0,screencoordy=0;
    public static Input I;
    public static Fontloader font;
    public static boolean canRender,overworld=true,test=false,testcol,circCol,LOG=true,DEBUGCOLISIONS=true,HideSprite=false,DebugPrint=false,DebugdrawString=true,showFps=false;
    public static double framCap,time,time2,passed,unproccesed,frameTime=0,lastFrame=0,DeltaTime,animateTime,Ti,TT,seconds,amountInSeconds;
    public static Texture tex,MAP,bg,playerTex,COLTEX,piont,piont2,col2,circleCol1,circleCol2,textbox;
    public static float x2,y2,camx,camy,x,y,Playerscale=64;
    public static Model background,player,textboxM;
    public static BatchedModel testM;
    public static TextBuilder textB,textA,textC,textD,text1,textDrawCalls;
    public static Vector2f currentmovement,c2,oldpos,direction;
    public static AABB playerCol,Col;
    public static CircleColision circle1, circle2;
    public static Animate a1;
    public static SpriteSheetLoader sloader;
    public static boolean facingLeft,running;
    
	public static void main(String[] args) {
	
	
		
		sloader= new SpriteSheetLoader("playerSpriteSheet",138);//loads the sprite sheet info
		
		DebugPrint("Starting.....");
	double startTime=Timer.getTIme();
	
	
	
	
	
	
	
	
	
	DebugPrint("intialzing vars....");
	float[] vert={
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
		
		float[] uvtextbox={
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
	    textB= new TextBuilder("aakar",512); 
		textA= new TextBuilder("aakar",512);
		textC= new TextBuilder("aakar",512);
		textD= new TextBuilder("aakar",512);
		text1= new TextBuilder("aakar",512);
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
		//map=new Texture("map1"); 
		//Define models
	   DebugPrint("Making Models....");
		
		player= new Model(vertPlayer,uvPlayer,ind);
	    background=new Model(vert,uvBg,ind);
	    textboxM=new Model(vertText,uvtextbox,ind);
		
	   DebugPrint("Making Camera....");
		//set camera
		cam= new Camera(width,height);
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
		Col=new AABB(new Vector2f(4078+(2001),1026),2048,64,0);
		circle1=new CircleColision(new Vector2f(256,256),64);
		circle2=new CircleColision(new Vector2f(0,0),32);
		
	
	   
		
		
		initializeFPS();
		x=100;
		y=100;	
		double ENDTime=Timer.getTIme();
		double timeTaken=ENDTime-startTime;
		DebugPrint("Tiem to load--- "+timeTaken+" seconds");
		
		DebugPrint("Starting Game loop.....");
		
		
		s.loadVec4(Color, new Vector4f(1,1,1,1));
     
		
			
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
		Vector2f currentpos=new Vector2f(x,y);
		circle2.setPosition(currentpos);
		   testcol=playerCol.vsAABB(Col);
		   circCol=circle2.vsCircle(circle1);
	       boolean colT=circle2.vsAABB(Col);				
	       Vector2f new2=playerCol.findVector(oldpos,c2,direction,Col);//old position,the new movement,the normalized vector of the direction the player is going,th aaabb box that we are checking colision with 
				
				
				//newvec.sub(new2);
				Vector2f a=new Vector2f(0,0);
				new2.sub(currentpos,a);
				if(testcol) {
			      x+=a.x;
				  y+=a.y;
				   camx=-x;
					camy=-y;
					playerCol.setPosition(new Vector2f(x,y));
			
			//x=oldpos.x;
			//y=oldpos.y;
			}
				
				Vector2f new3=circle2.findVector(oldpos,c2,direction,circle1);
				new3.sub(currentpos,a);
				if(circCol) {
				      x+=a.x;
					  y+=a.y;
					   camx=-x;
						camy=-y;
						circle2.setPosition(new Vector2f(x,y));
				
				//x=oldpos.x;
				//y=oldpos.y;
				}	
				
				new2=circle2.findVector(oldpos, c2, direction,Col);
				new2.sub(currentpos,a);
				if(colT) {
					 x+=a.x;
					  y+=a.y;
					   camx=-x;
						camy=-y;
						circle2.setPosition(new Vector2f(x,y));
					
				}
				
				
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
	    
	    circle1.debug();
	    circle2.debug();
	      textD.setString("circCol:"+circCol);
	      textC.setString("xmap="+gridx+" ymap="+gridy+" col:  "+testcol);
	    
	  
	      textCircle.setString("CircleVSAABB: "+colT);
	    
	      
		if(HideSprite==false) 
		SpriteUpdate(player,playerTex,x,y,Playerscale,facingLeft);
	
		textB.DebugdrawString(screencoordx-300,screencoordy-220,.24f);
		textC.DebugdrawString(screencoordx,screencoordy-220,.24f);
		textD.DebugdrawString(screencoordx, screencoordy-200, .24f);
		textCircle.DebugdrawString(screencoordx-300, screencoordy-200, .24f);


}else {
	//---------------------battle loop---------------------
	battleupdate();
    screencoordx=0;
	screencoordy=0;
	
	
   






}

textDrawCalls.setString("Drawcalls(S:"+drawcalls+ " F:"+drawcallsFrame+")");
textA.setString("fps="+(int)fps);
if(showFps)
textA.drawString((640/2)+screencoordx-100,(480/2)+screencoordy-20,.24f);
textDrawCalls.DebugdrawString((640/2)+screencoordx-625,(480/2)+screencoordy-20,.24f);

		
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
	
		w=new Window(width,height);
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
		
	if(testKey==2) {
	//	x=0;y=0;
		if (overworld==false) {
			overworld=true;
			
		}else {
			overworld=false;
			a1.Stop();
			
		}
		
		
		
	}
	if(overworld==true) {
		
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
		 
	}
		 
		 
			 }
	
	
   private static void battleupdate() {
	   Vector2f position1=new Vector2f(-90,40);
	   Vector2f position2=new Vector2f(-110,-98);
	   Vector2f positiont;
	   cam.setPosition(new Vector2f(0,0));
	   int sprite=0;
	   switch(sprite) {
	   case 0:
		   positiont=position1;
	   break;
	   case 1:
		   positiont=position2;
		break;   
		   
		   
		default:
			 positiont=position1;
	   
	   }
	   
	
		  cam.setPosition(new Vector2f(0,0));
	 	 
	 	Renderer.draw(background,new Vector2f(0),0,64*40,bg); 
	 	 
	 	 
	 	SpriteUpdate(player,playerTex,-192,-20,64*1.5f,true); //doing the same model and texture just for testing  will change that when we actually get the battle system down  
	 	 SpriteUpdate(player,playerTex,-222,-128-20,64*1.5f,true);
	 	 SpriteUpdate(player,playerTex,222-20,-128+40,64*1.5f,false);
	 	 SpriteUpdate(textboxM,textbox,positiont.x,positiont.y,1,false);
	 	 
	 	  
	 	  
	 	  
	 	  text1.setString("moves");
	 	  text1.drawString(positiont.x-15,positiont.y+15, .15f, Constants.COL_COLOR_RED);
	 	  
	 	 
	 	  
	 	  s.loadInt(location, 0);
	 	   
	 	  
	   
	   
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
private static void drawmap(MapLoader loader,int gridx,int gridy) {
	  loader.loadTile(0,0);

	  for(int i=-amountHeight+2;i<amountHeight-1;i++) {
		for(int j=-amountWidth+2;j<amountHeight;j++) {
			   loader.loadTile(gridx+j,gridy+i);
			    }
	}
 //   loader.getModel().setDrawMethod(GL_LINES);
	loader.drawtiles(tex);

}








private static void initializeFPS() {
		time=Timer.getTIme();
		unproccesed=0;
	    frameTime=0;
	    frames=0;
		framCap=1.0/60.0;
	
		a1=new Animate(7,player,sloader,0,7);
	}
	
	
public static void DebugPrint(String string) {
if(DebugPrint)	
System.out.println(string);}	
		
		
	
	
	
	
}
