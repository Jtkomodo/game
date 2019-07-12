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
    public static int location,Projection,Color,RTS,frames=0,j=0,i=0,fps,gridx,gridy;
    public static byte dKeys,testKey;
    public static ShaderProgram s;
    public static float scaleOfMapTiles=128,Rendercamx,Rendercamy;
    public static int amountWidth=Math.round((width/scaleOfMapTiles)),amountHeight=Math.round((height/scaleOfMapTiles));
    public static Camera cam;
    public static float screencoordx=0,screencoordy=0;
    public static Input I;
    public static Fontloader font;
    public static boolean canRender,overworld=true,test=false,testcol,circCol,LOG=false,DEBUGCOLISIONS=true;
    public static double framCap,time,time2,passed,unproccesed,frameTime=0;
    public static Texture tex,MAP,bg,playerTex,COLTEX,piont,piont2,col2,circleCol1,circleCol2,textbox;
    public static float x2,y2,camx,camy,x,y,Playerscale=64;
    public static Model background,player,textboxM;
    public static BatchedModel testM;
    public static TextBuilder textB,textA,textC,textD,text1;
    public static Vector2f currentmovement,c2,oldpos,direction;
    public static AABB playerCol,Col;
    public static CircleColision circle1, circle2;
    
	public static void main(String[] args) {
	
		if(LOG==true)
		System.out.println("Starting.....");
	double startTime=Timer.getTIme();
	
	
	
	
	
	
	
	
	if(LOG==true)
	System.out.println("intialzing vars....");
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
		//font=new Fontloader("aakar",512);    
		tex=new Texture("newsprite");
		textbox=new Texture("textbox");
		bg= new Texture("testBackground");
	    textB= new TextBuilder("aakar",512); 
		textA= new TextBuilder("aakar",512);
		textC= new TextBuilder("aakar",512);
		textD= new TextBuilder("aakar",512);
		text1= new TextBuilder("aakar",512);
		testM= new BatchedModel();
		piont= new Texture("Point");

		piont2= new Texture("Point2");
		circleCol1=new Texture("Circle");
	//	MAP=new Texture("map3");
	    playerTex= new Texture("playerSprite2s_1");
	    COLTEX= new Texture("ColTex");
	    col2= new Texture("ColTex2");
		//map=new Texture("map1"); 
		//Define models
	    if(LOG==true)
	    System.out.println("Making Models....");
		
		player= new Model(vertPlayer,uvPlayer,ind);
	    background=new Model(vert,uvBg,ind);
	    textboxM=new Model(vertText,uvtextbox,ind);
		
	    if(LOG==true)
	    System.out.println("Making Camera....");
		//set camera
		cam= new Camera(width,height);
		if(LOG==true)
		 System.out.println("Making Shader Uniforms....");
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
		
	
		if(LOG==true)
		System.out.println("Settign Colisions....");
		//playerCol=new AABB(new Vector2f(0,0),15,44,0);
		playerCol=new AABB(new Vector2f(0,0),15,44,0);
		Col=new AABB(new Vector2f(1280,180),64,64,0);
		circle1=new CircleColision(new Vector2f(256,256),64);
		circle2=new CircleColision(new Vector2f(0,0),32);
		
	
		
		
		
		initializeFPS();
		x=100;
		y=100;	
		double ENDTime=Timer.getTIme();
		double timeTaken=startTime-ENDTime;
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
		frames++;
		
		if(test==false) {
	
			
if(overworld==true) {		 
		playerCol.setPosition(new Vector2f(x,y));
		Vector2f currentpos=new Vector2f(x,y);
		circle2.setPosition(currentpos);
		   testcol=playerCol.vsAABB(Col);
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
	      
	      
	      
		 
		 //SpriteUpdate(player,playerTex,x,y,Playerscale,true);
		textB.drawString(screencoordx-300,screencoordy-220,.24f);
		textC.drawString(screencoordx,screencoordy-220,.24f);
		textD.drawString(screencoordx, screencoordy-200, .24f);
		
	

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
	 	  text1.drawString(positiont.x-15,positiont.y+15, .15f, Constants.BLACK);
	 	  
	 	  
	 	  
	 	  s.loadInt(location, 0);
	 	   
	 	  
	   
	   
   }
	
	private static void fps() {
		    Model.disable();
		    canRender=false;//don't allow rendering until time
			time2=Timer.getTIme();//gets current time
		    passed=time2-time;//this is the time since the last time through the loop
			unproccesed+=passed;//this is the time that we have not rendered anything
			frameTime+=passed;//this is the time since the last second
			time=time2;
			while(unproccesed>=framCap) {//when the time we have not rendered is greater than or equal to our frame target we allow rendering
				
				unproccesed-=framCap;//this is like reseting but instead if it is way over for some reason it makes sure to allow all rendering missed 
			       
				//take input
				 Inputupdate();
			     canRender=true;//now we render	
			    
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

	
		
		
	
	
	
	
}
