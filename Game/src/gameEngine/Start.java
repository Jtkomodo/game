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
import Collisions.ColisionHandeler;
import textrendering.Fontloader;
import textrendering.TextBuilder;
import Data.Constants;
public class Start {
    
	
	public static Window w;
	public static final int width=640,height=480;
<<<<<<< Updated upstream
    public static int location,Projection,Color,RTS,frames=0,j=0,i=0,fps,gridx,gridy;
=======
	public static final boolean LOG=true,DEBUGCOLISIONS=true,HideSprite=false,DebugPrint=true,DebugdrawString=true,showFps=true;
	
	
    public static int location,Projection,Color,RTS,frames=0,j=0,i=0,fps,gridx,gridy,Aframes,drawcalls=0,drawcallsFrame=0;
    public static int battleState,sprite,arrowPosition;
    
>>>>>>> Stashed changes
    public static byte dKeys,testKey;
    public static int UP=0,DOWN=0,LEFT=0,RIGHT=0;
    public static ShaderProgram s;
    public static float scaleOfMapTiles=128,Rendercamx,Rendercamy;
    public static int amountWidth=Math.round((width/scaleOfMapTiles)),amountHeight=Math.round((height/scaleOfMapTiles));
    public static Camera cam;
    public static float screencoordx=0,screencoordy=0;
    public static Input I;
<<<<<<< Updated upstream
    public static Fontloader font;
    public static boolean canRender,overworld=true,test=false,testcol,circCol,LOG=true,DEBUGCOLISIONS=true;
    public static double framCap,time,time2,passed,unproccesed,frameTime=0,lastFrame=0,DeltaTime;
    public static Texture tex,MAP,bg,playerTex,COLTEX,piont,piont2,col2,circleCol1,circleCol2,textbox;
=======

    public static boolean canRender,overworld=true,test=false,testcol,circCol;
    public static double framCap,time,time2,passed,unproccesed,frameTime=0,lastFrame=0,DeltaTime,animateTime,Ti,TT,seconds,amountInSeconds;
    public static Texture tex,MAP,bg,playerTex,COLTEX,piont,piont2,col2,circleCol1,circleCol2,textbox,testSprite;
>>>>>>> Stashed changes
    public static float x2,y2,camx,camy,x,y,Playerscale=64;
    public static Model background,player,textboxM;
    public static BatchedModel testM;
    public static TextBuilder textB,textA,textC,textD,text1;
    public static Vector2f currentmovement,c2,oldpos,direction;
<<<<<<< Updated upstream
    public static AABB playerCol,Col;
    public static CircleColision circle1, circle2;
=======
    public static AABB playerCol,Col,COl2;
    public static Animate a1;
    public static SpriteSheetLoader sloader;
    
    public static Fontloader aakar;
    
    public static boolean facingLeft,running;
>>>>>>> Stashed changes
    
	public static void main(String[] args) {
	
		if(LOG==true)
		System.out.println("Starting.....");
	double startTime=Timer.getTIme();
	
	
	
	
	
	
	
	
<<<<<<< Updated upstream
	if(LOG==true)
	System.out.println("intialzing vars....");
=======
	
	
	
	
	DebugPrint("intialzing vars....");
>>>>>>> Stashed changes
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
       if(LOG==true)
       System.out.println("Creating window....");
		//make our window and attach shaders
		createWindow("shader");
		if(LOG==true)
		   System.out.println("Making Textures....");
		//Define texturesbr.close();
		aakar=new Fontloader("aakar",512);    
		tex=new Texture("newsprite");
		textbox=new Texture("textbox");
		bg= new Texture("testBackground");
<<<<<<< Updated upstream
	    textB= new TextBuilder("aakar",512); 
		textA= new TextBuilder("aakar",512);
		textC= new TextBuilder("aakar",512);
		textD= new TextBuilder("aakar",512);
		text1= new TextBuilder("aakar",512);
=======
		
		
		
		TextBuilder textforTILES=new TextBuilder(aakar);
	    textB= new TextBuilder(aakar); 
		textA= new TextBuilder(aakar);
		textC= new TextBuilder(aakar);
		textD= new TextBuilder(aakar);
		text1= new TextBuilder(aakar);
		textDrawCalls= new TextBuilder("aakar",512);
		TextBuilder textCircle= new TextBuilder("aakar",512);
>>>>>>> Stashed changes
		testM= new BatchedModel();
		piont= new Texture("Point");

		piont2= new Texture("Point2");
		circleCol1=new Texture("Circle");
	//	MAP=new Texture("map3");
	    playerTex= new Texture("playerSprite2s_1");
	    COLTEX= new Texture("whitebox");
	    col2= new Texture("ColTex2");
		//map=new Texture("map1"); 
		//Define models
	    if(LOG==true)
	    System.out.println("Making Models....");
		
		player= new Model(vertPlayer,uvPlayer,ind);
	    background=new Model(vert,uvBg,ind);
	    textboxM=new Model(vertText,uvtextbox,ind);
<<<<<<< Updated upstream
		
	    if(LOG==true)
	    System.out.println("Making Camera....");
		//set camera
		cam= new Camera(width,height);
		if(LOG==true)
		 System.out.println("Making Shader Uniforms....");
=======
	    Arrow=new Model(vert,uvArrow,ind);

	    
	    
	
		DebugPrint("Making Shader Uniforms....");
>>>>>>> Stashed changes
		//make shader locations so we can use uniforms
		try {
			location=s.makeLocation("sampler");
			Projection=s.makeLocation("projection");
			RTS=s.makeLocation("rts");
			Color=s.makeLocation("color");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if(LOG==true)
		System.out.println("Loading world....");
		WorldLoader worldLoader=new WorldLoader("map", 64,64, cam);
		MapLoader loader=new MapLoader(worldLoader.Getmap(),scaleOfMapTiles);
        PositionTest postest=new PositionTest(worldLoader.Getmap(),scaleOfMapTiles);	
		
	
<<<<<<< Updated upstream
		if(LOG==true)
		System.out.println("Settign Colisions....");
		//playerCol=new AABB(new Vector2f(0,0),15,44,0);
		playerCol=new AABB(new Vector2f(0,0),15,44,0);
		Col=new AABB(new Vector2f(1280,180),64,64,0);
		circle1=new CircleColision(new Vector2f(256,256),64);
		circle2=new CircleColision(new Vector2f(0,0),32);
		
	
		
=======
		DebugPrint("Setting Colisions....");
		//playerCol=new AABB(new Vector2f(0,0),15,44,0);
		playerCol=new AABB(new Vector2f(0,0),15,44,0);
		Col=new AABB(new Vector2f(3968-64,1026),64,128,0);
        COl2=new AABB(new Vector2f(-64,1026-128),2048,64,0);
>>>>>>> Stashed changes
		
		
		initializeFPS();
		x=100;
		y=100;	
		double ENDTime=Timer.getTIme();
		double timeTaken=ENDTime-startTime;
		if(LOG==true)
		System.out.println("Tiem to load--- "+timeTaken+" seconds");
		
		if(LOG==true)
		System.out.println("Starting Game loop.....");
		
		
		s.loadVec4(Color, new Vector4f(1,1,1,1));
    //----------------------GAME--LOOP------------------------------
		while(!w.isExited() && !I.IsEscapePushed()) {
			oldpos=new Vector2f(x,y);
		fps();
	   
	    
	    
	if(canRender) {
		
	  // TextureUpdate(MAP)
Model.enable();
BatchedModel.enable();
	
		
		if(test==false) {
	
			
if(overworld==true) {		 
		playerCol.setPosition(new Vector2f(x,y));
		Vector2f currentpos=new Vector2f(x,y);


		   testcol=playerCol.vsAABB(Col);
<<<<<<< Updated upstream
		   circCol=circle2.vsCircle(circle1);
	
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
		screencoordx=-camx;
	    screencoordy=-camy;
	    
	    		
	     cam.setPosition((new Vector2f(camx,camy)));s.bind();
	     cam.setPosition((new Vector2f(camx,camy)));s.bind();
		
		       
		    //   testcol=playerCol.AABBwAABB(Col); 
	       
=======
		
	     
	       
	       
	     	
			Vector2f vector=updateColisions();
		
			x=vector.x; 
			y=vector.y;	
			     
				    camx=-x;
					camy=-y;
							
		screencoordx=-camx;
	    screencoordy=-camy;
	   
	     cam.setPosition((new Vector2f(camx,camy)));
>>>>>>> Stashed changes
	    

	  
	
	      textB.setString("x="+x+" y="+y);
	      Vector2f camPos=cam.getPosition();
	     Rendercamx=-(camPos.x);
	     Rendercamy=-(camPos.y);
	      Vector2f newvec=postest.findPositionOnMap(Rendercamx,Rendercamy);
	      gridx= Math.round(newvec.x);gridy=Math.round(newvec.y);
	    drawmap(loader,gridx,gridy);		     
	    Col.debug();
	    playerCol.debug();
<<<<<<< Updated upstream
	    circle1.debug();
	    circle2.debug();
	      textD.setString("circCol:"+circCol);
	      textC.setString("xmap="+gridx+" ymap="+gridy+" col:  "+testcol);
	      
	      
	      
		 
		 //SpriteUpdate(player,playerTex,x,y,Playerscale,true);
		textB.drawString(screencoordx-300,screencoordy-220,.24f);
		textC.drawString(screencoordx,screencoordy-220,.24f);
		textD.drawString(screencoordx, screencoordy-200, .24f);
		
	
=======
	    COl2.debug();
	    
	  
	      textC.setString("xmap="+gridx+" ymap="+gridy);
	    
	  
	      
	      
		if(HideSprite==false) 
		SpriteUpdate(player,playerTex,x,y,Playerscale,facingLeft);
		
		textforTILES.setString("Tiles: "+loader.getTilesrenderd());
	
		textB.UIDebugdrawString(screencoordx-300,screencoordy-220,.24f);
		textC.UIDebugdrawString(screencoordx+100,screencoordy-220,.24f);
         textforTILES.UIDebugdrawString(screencoordx,(480/2)+ screencoordy-20, .24f);

>>>>>>> Stashed changes

}else {
	//---------------------battle loop---------------------
	battleupdate();
    screencoordx=0;
	screencoordy=0;
	
	
   






}
textA.setString("fps="+(int)fps);
textA.drawString((640/2)+screencoordx-100,(480/2)+screencoordy-20,.24f);


		}
		    w.render();
		    w.clear();
		    loader.flushModel();
			
		}
		}
//--------------------------------------------------------------	
		if(LOG==true)
		System.out.println("Closing.....");
		w.destroy();
		
		
	}

	private static void createMapfromtex(Texture tex) {

		byte b=I.getDirectionalInput();
		
			if(b==0x0001) {
			try {
				System.out.println("yes");
				tex.createFile(true,"map");
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		}
		
		
		
	

	private static void createWindow(String Shader) {
	
		w=new Window(width,height);
	 I=new Input(w);	
       //load our shaders 
	 if(LOG==true)
	 System.out.println("Making Shader Program....");
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
<<<<<<< Updated upstream
=======
			a1.Stop();
			battleState=0;
>>>>>>> Stashed changes
			
		}
		
		
		
	}
	if(overworld==true) {
		
		        if(I.getStateofButton(GLFW_KEY_W)==1 || I.getStateofButton(GLFW_KEY_W)==3) {
		        	speed=5;
		        	
		        	
		        }else {
		        	speed=.5f;
		        	
		        	
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
							
						}
						if((dKeys>>1 & 0b001)==1) {//down
						
							speedy=-1;
									
						}if((dKeys>>2 & 0b001)==1) {//left
					
							speedx=1;
							
						}if((dKeys>>3 & 0b001)==1) {//right
						
						   speedx=-1;	
						}	
					
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
		 
<<<<<<< Updated upstream
=======
	}else {
		

	
	
	
		if(I.getStateofButton(GLFW_KEY_S)==1) {
			   if(sprite==0) {
			    	 sprite=1;
			    	 
			     }else {
			    	 sprite=0;
			     }
			arrowPosition=0;
		}
		
		
		if(I.getStateofButton(GLFW_KEY_UP)==1) {//up
		BattleInputUp();
			
		}
		if(I.getStateofButton(GLFW_KEY_DOWN)==1) {//down
		if(arrowPosition==0) {
			arrowPosition=1;
		}
		
					
		}
		if(I.getStateofButton(GLFW_KEY_LEFT)==1) {//left
			if(arrowPosition==0) {
				arrowPosition=1;
			}  else if(arrowPosition==2) {
				arrowPosition=1;
			}
			
			
		}if(I.getStateofButton(GLFW_KEY_RIGHT)==1) {//right
		if(arrowPosition==1 || arrowPosition==0) {
			arrowPosition=2;
			
		}
		
		}

		
		
		
		
		
		
		
		
		
>>>>>>> Stashed changes
	}
		 
		 
			 }
	
	
   private static void battleupdate() {
<<<<<<< Updated upstream
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
	   
		s.bind();
		bg.bind(0);
		  s.loadInt(location, 0);
		  cam.setPosition(new Vector2f(0,0));
	 	   s.loadMat(Projection,cam.getProjection());
	 	  s.loadMat(RTS,MatrixMath.getMatrix(new Vector2f(0,0),0,64*40));//change rotation and scale with this
	 	 background.draw();
	 	SpriteUpdate(player,playerTex,-192,-20,64*1.5f,true); //doing the same model and texture just for testing  will change that when we actually get the battle system down  
	 	 SpriteUpdate(player,playerTex,-222,-128-20,64*1.5f,true);
	 	 SpriteUpdate(player,playerTex,222-20,-128+40,64*1.5f,false);
	 	 SpriteUpdate(textboxM,textbox,positiont.x,positiont.y,1,false);
	 	 
	 	  
	 	  
	 	  
	 	  text1.setString("moves");
	 	  text1.drawString(positiont.x-15,positiont.y+15, .15f, Constants.TEST_COLOR);
	 	  
	 	  
	 	  
	 	  s.loadInt(location, 0);
=======
	   cam.setPosition(new Vector2f(0,0));
		 
	   
	   
	   
		Renderer.draw(background,new Vector2f(0),0,64*40,bg); 
	   
	 	 
	 	SpriteUpdate(player,playerTex,-192,-20,64*1.5f,true); //doing the same model and texture just for testing  will change that when we actually get the battle system down  
	 	 SpriteUpdate(player,playerTex,-222,-128-20,64*1.5f,true);
	 	 SpriteUpdate(player,playerTex,222-20,-128+40,64*1.5f,false);
	   
	   
	   switch(battleState) {
	   case 0:
	   BattleUISTATE0(arrowPosition);
	
	   break;
	   
	   
	   
	   
	   
	   }
	   
	   
	   
	   
	   
	   
	   
	   
>>>>>>> Stashed changes
	 	   
	 	  
	   
	   
   }
	
	private static void fps() {
		    Model.disable();
		    canRender=false;//don't allow rendering until time
			time2=Timer.getTIme();//gets current time
		    passed=time2-time;//this is the time since the last time through the loop
			unproccesed+=passed;//this is the time that we have not rendered anything
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
			    	 
			    	 // System.out.println("FPS:"+frames+"-------------------------------");
			    	 
                      fps=frames;
			    	//reset frame time and frames  
			    	  
			    	  frameTime=0;
			    	  frames=0;
			      }
			   
			}
	}
private static void SpriteUpdate(Model sprite,Texture tex,float x,float y,float spritescale,boolean mirror){
   s.bind();
   tex.bind(0);
   Matrix4f target=MatrixMath.getMatrix(new Vector2f(x/spritescale,y/spritescale),0,spritescale);
   if(mirror==true)
   MatrixMath.mirror(target);
   
   s.loadInt(location, 0); 
  	   s.loadMat(Projection,cam.getProjection());
       s.loadMat(RTS, target);
	sprite.draw();	 
	
    
    
    
    
    
    
}
private static void drawmap(MapLoader loader,int gridx,int gridy) {
	  loader.loadTile(0,0);
	for(int i=-amountHeight+2;i<amountHeight-1;i++) {
		for(int j=-amountWidth+2;j<amountHeight;j++) {
			   loader.loadTile(gridx+j,gridy+i);
			    }
	}
  
	loader.drawtiles(tex);
	
}



	private static void initializeFPS() {
		time=Timer.getTIme();
		unproccesed=0;
	    frameTime=0;
	    frames=0;
		framCap=1.0/60.0;
		
		
	}

	
		
		
private static Vector2f updateColisions() {
	
	  Vector2f cs=new Vector2f(x,y);
      
     
	   cs =ColisionHandeler.CheckAndGetResponse(playerCol,COl2,cs,oldpos, c2, direction);
	    
	   cs =ColisionHandeler.CheckAndGetResponse(playerCol, Col,cs,oldpos, c2, direction);
	   cs =ColisionHandeler.CheckAndGetResponse(playerCol,COl2,cs,oldpos, c2, direction);

	   
	   
	   return cs;
}
	


private static void BattleUISTATE0(int arrowposition) {
	
	
	   Vector2f position1=new Vector2f(-90,40);
	   Vector2f position2=new Vector2f(-110,-98);
	   Vector2f positiont;
	   Vector2f arrowpos = new Vector2f(0);
	
	   float angle;
	   switch(sprite) {
	   case 0:
		   positiont=position1;
		   angle=2;
	   break;
	   case 1:
		   positiont=position2;
		   angle=-2;
		break;   
		   
		   
		default:
			 positiont=position1;
	         angle=2;
	   }
	   
	   switch(arrowPosition) {
	   case 0:
		   positiont.add(new Vector2f(-24,15),arrowpos);
		 
	   break;
	   case 1:
		  positiont.add(new Vector2f(-43,-5),arrowpos);
		   
		break;   
	   case 2:
		   positiont.add(new Vector2f(5,-5),arrowpos);
		  
		break;     
		   
		default:
			 positiont.add(new Vector2f(-24,15),arrowpos);
	   }
	   
	   
	   
	  angle=0;
	
		  cam.setPosition(new Vector2f(0,0));
	 	 
	 
	 	 

	 	 SpriteUpdate(textboxM,textbox,positiont.x,positiont.y,angle,1,false);
	 	 SpriteUpdate(Arrow,testSprite,arrowpos.x,arrowpos.y,angle,10,false);
	 	 
	 	 
	 	 
	 	 
	 	  
	 	  
	 	 
	 	  text1.setString("moves");
	 	  text1.drawString(positiont.x-17,positiont.y+15,angle, .15f, Constants.BLACK);
	 	  text1.setString("bag");
	 	  text1.drawString(positiont.x-35,positiont.y-5,angle, .15f, Constants.BLACK);
	 	  text1.setString("specials");
	 	 text1.drawString(positiont.x+10,positiont.y-5,angle, .15f, Constants.BLACK);
	 	 
	 	  
	 	  s.loadInt(location, 0);
}

private static void BattleInputUp() {
	
	
	switch(battleState) {
	
	case 0:
	if(arrowPosition==2 || arrowPosition==1) {
		arrowPosition=0;
	}
	break;
	}
}

private static void BattleInputDown() {
switch(battleState) {
	
	
	}
	
	
}

private static void BattleInputleft() {
switch(battleState) {
	
	
	}
	
}
	
private static void BattleInputRight() {
	
switch(battleState) {
	
	
	}
}


private static void BattleInputSelect() {
	
switch(battleState) {
	
	
	}
}	

private static void BattleInputBack() {
switch(battleState) {
	
	
	}
	
}	


}
