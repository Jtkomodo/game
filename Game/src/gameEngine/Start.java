package gameEngine;
import java.io.IOException;
import java.util.Map;

import org.joml.Matrix4f;
import  org.joml.Vector2f;
public class Start {
    
	
	public static Window w;	
	public static final int width=640,height=480;
    public static int location,Projection,RTS,frames,j=0,i=0;
    public static byte dKeys;
    public static ShaderProgram s;
    public static Camera cam;
    public static Input I;
    public static boolean canRender;
    public static double framCap,time,time2,passed,unproccesed,frameTime;
    public static Texture tex,MAP;
    public static float x2,y2,camx,camy,x,y,Palyerscale=100;
	public static void main(String[] args) {
		float[] vert={
				-0.5f,+0.5f,
				0.5f,0.5f,
				0.5f,-0.5f,
				-0.5f,-0.5f
			};
		float Texwidth=256;
		float Texheight=256;		
		float wi=64;
		float h=64;
		float Texx=64;
		float Texy=0;		
		
		float[] uv={
				Texx/Texwidth,Texy/Texheight,
				(Texx+wi)/Texwidth,Texy/Texheight,
				(Texx+wi)/Texwidth,(Texy+h)/Texheight,
				Texx/Texwidth,(Texy+h)/Texheight
				};
			
		int[] ind= {
			0,1,2,
			2,3,0	
				
		};
	   Texwidth=256;
	   Texheight=256;		
	   wi=64;
	   h=64;
	   Texx=64;
	   Texy=0;	



       float[] uvPlayer={
		Texx/Texwidth,Texy/Texheight,
		(Texx+wi)/Texwidth,Texy/Texheight,
		(Texx+wi)/Texwidth,(Texy+h)/Texheight,
		Texx/Texwidth,(Texy+h)/Texheight  };





		for(int i=0;i<8;i++) {
		System.out.println(uv[i]);
		} 
		
		
		//make our window and attach shaders
		createWindow("shader");
	
		
		//Define textures
	    
		tex=new Texture("newsprite");
		Texture playerTex= new Texture("playerSprites");
		//map=new Texture("map1"); 
		//Define models
		Model m=new Model(vert,uv,ind);//model for tiles
		Model player= new Model(vert,uvPlayer,ind);
		//set camera
		cam= new Camera(width,height);
		
		//make shader locations so we can use uniforms
		try {
			location=s.makeLocation("sampler");
			Projection=s.makeLocation("projection");
			RTS=s.makeLocation("rts");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		World world=new World("map", 64,64, cam);
		Tiles t=new Tiles(tex, m, s, cam, location, Projection,RTS);
	    gameEngine.Map grid=new gameEngine.Map(world.Getmap(),t);
	
		
		
		
		 boolean map=true;
		initializeFPS();
		float x=0;
		int y=0;	
	
//----------------------game loop------------------------------
		while(!w.isExited() && !I.IsEscapePushed()) {
	
		fps();
	    t.unbind();
	
	

	if(canRender) {
	
		 frames++;
		 cam.setPosition((new Vector2f(camx,camy)));
		 grid.Draw();
		
		
		 PlayerUpdate(player,playerTex);     

		    w.render();
		    w.clear();
			
		}
		}
//--------------------------------------------------------------		
		
		w.destroy();
		
		
	}

	private static void TextureUpdate(Texture tex) {

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
		s= new ShaderProgram(Shader);
		//bind shader
		s.bind();	
		
		
	}

	private static void Inputupdate() {
		w.update();//this is needed to actually poll events from keyboard 
		I.findKeys();
	    dKeys=I.getDirectionalInput();
		byte stateofFullscreen=I.stateOfFullscreen();
	      if(stateofFullscreen==1) {//if the fuscreenCode is just pressed toggle full screen
			 w.toggleFullscreen();}
		
	
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
							y+=5;
							
						}
						if((dKeys>>1 & 0b001)==1) {//down
							y-=5;
							
						}if((dKeys>>2 & 0b001)==1) {//left
							x+=5;
							
						}if((dKeys>>3 & 0b001)==1) {//right
							x-=5;}	 
					camx=-x;
					camy=-y;
					
						}
		 
		 
		 
		 
			 }
	
	
	
	
	private static void fps() {
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
			    	 
			    	  System.out.println("FPS:"+frames+"-------------------------------");
			      
			    	//reset frame time and frames  
			    	  
			    	  frameTime=0;
			    	  frames=0;
			      }
			}
		
	}
private static void PlayerUpdate(Model player,Texture tex){
   s.bind();
   tex.bind(0);
   Matrix4f target=Math.getMatrix(new Vector2f(x/Palyerscale,y/Palyerscale),0,Palyerscale);
   s.loadInt(location, 0);
  	   s.loadMat(Projection,cam.getProjection());
       s.loadMat(RTS, target);
	player.draw();	 



}




	private static void initializeFPS() {
		time=Timer.getTIme();
		unproccesed=0;
	    frameTime=0;
	    frames=0;
		framCap=1.0/60;
		
		
	}

	
}
