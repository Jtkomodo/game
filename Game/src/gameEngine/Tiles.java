package gameEngine;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Tiles {

	private Texture tex;
	private Model model;
	private ShaderProgram s;
	private Camera cam;
	private Vector2f Translation=new Vector2f(0,0);

	private float angle=0,scale=128;
    private int sampler,texlocation,ShaderProjectionLocation,l; 
	public Tiles(Texture tex,Model model,ShaderProgram s, Camera cam,int texlocation, int ShaderProjectionLocation,int ShaderRtsLocation) {
		this.tex=tex;
		this.model=model;
		this.s=s;
		this.texlocation=texlocation;
		this.ShaderProjectionLocation=ShaderProjectionLocation;
	   this.cam=cam;
	 l=ShaderRtsLocation;
	   s.bind();
	   tex.bind(0);
	}
	public void Bind(int sampler) {
		s.bind();
		tex.bind(sampler);
		this.sampler=sampler;
		
	}
	
	
    public void draw() {
    	//tile values
    	float Leftcornerx=(Translation.x * scale)-(scale/2);
        float Topcornery =(Translation.y*scale)+(scale/2);
    	float Bottomcornery=Topcornery-scale;
        float Rightcornerx=Leftcornerx+scale;
        
        //camera values
        float camTop=(cam.getPosition().y-(cam.getHeight()/2f))*-1;
    	float camLeft=(cam.getPosition().x+(cam.getWidth()/2))*-1;
    	float camBottom=camTop-cam.getHeight();
    	float camRight=(camLeft+cam.getWidth());
    // render bounds  
 // System.out.println(Leftcornerx);
  
  boolean leftBounds=(Rightcornerx<(camLeft)-scale); 
  boolean rightBounds=(Leftcornerx>(camRight));
  boolean topBounds=(Bottomcornery>(camTop)+scale);
  boolean bottomBounds=(Topcornery<(camBottom)-scale);  	
 
if(rightBounds) {
	 	  Map.norenderR=false;
 }
 else{
	 Map.norenderR=true;
 }
if(leftBounds) {
	  Map.norenderL=false;
}else{
Map.norenderL=true;
}


if(topBounds) {
	Map.norenderT=false;
}else{

	Map.norenderT=true;
}
if(bottomBounds) {
	Map.norenderB=false;
}else{

	Map.norenderB=true;
}	  
 // }
  
  
  if((!leftBounds) && (!rightBounds) && (!topBounds) && (!bottomBounds)){
	  
        Matrix4f target=Math.getMatrix(Translation, angle, scale);
  	   s.loadInt(texlocation, sampler);
  	   s.loadMat(ShaderProjectionLocation,cam.getProjection());
  	   s.loadMat(l, target);
  	   model.draw();
  }else {
	  return;
  }

     }
	
public void unbind() {
	s.unbind();
	tex.unbind();
	
}

   
 //getters and setters
   
public Texture getTex() {
	return tex;
}
public void setTex(Texture tex) {
	this.tex = tex;
}
public Model getModel() {
	return model;
}
public void setModel(Model model) {
	this.model = model;
}
public Vector2f getTranslation() {
	return Translation;
}
public void setTranslation(Vector2f translation) {
	Translation = translation;
}
public float getAngle() {
	return angle;
}
public void setAngle(float angle) {
	this.angle = angle;
}
public float getScale() {
	return scale;
}
public void setScale(float scale) {
	this.scale = scale;
}
	
	
public ShaderProgram getShader() {
	return s;
}
public void setShader(ShaderProgram s) {
	this.s = s;
}	
	


public int getSampler() {
	return sampler;
}

public void setSampler(int sampler) {
	this.sampler = sampler;
}

public int getTexlocation() {
	return texlocation;
}

public void setTexlocation(int texlocation) {
	this.texlocation = texlocation;
}

public int getShaderProjectionLocation() {
	return ShaderProjectionLocation;
}

public void setShaderProjectionLocation(int shaderProjectionLocation) {
	ShaderProjectionLocation = shaderProjectionLocation;
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
