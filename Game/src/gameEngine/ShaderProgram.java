package gameEngine;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

/*1.we first have to create a shader and have our id call it	
2.we then need to show the shader where the source code is for the shader
*  we do this by glShaderSource(our id,a string that has all of our source code)
*3.next we compile the shader so the graphics card can understand it
*4.we check if the compile failed if it did tell us which one and print the info log
*5.we then attach the shader to the program and bind the program's attributes with a variable names we can use later 
*6.lastly when link and validate the program so it can be called on later
*
*/
public  class ShaderProgram {
private int program,vs,fs,locationSlot;
//private File file=new File(System.getProperty("user.dir"));	

	     //make our shader
	public ShaderProgram(String path) {
	
	//System.out.print(file.getAbsolutePath());
		program=glCreateProgram();
		createShaders(path);
	    Locations();
	    linkandValidate();
	
	}
	
	public ShaderProgram(String vertShader,String fragShader) {
		program=glCreateProgram();
		createShaders(vertShader,fragShader);
	    Locations();
	    linkandValidate();
		
	}
	
	
	
	
	//turning our text file into a string 
	
	private String readFile(String path) {//just the method to store our file contents in a string
		
		StringBuilder string=new StringBuilder();
		BufferedReader br;
		
		try{
			InputStream i=getClass().getResourceAsStream("/shaders/"+path);
			  InputStreamReader isr = new InputStreamReader(i);
			br=new BufferedReader(isr);
			String line;
			while((line= br.readLine())!= null) {
				string.append(line);
				string.append("\n");
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
			System.err.println("shader does not exist");
		}
		return string.toString();
		
	}
	
	//creating our shader from source
	private  void createShaders(String path) {
		
		vs=glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vs,readFile(path+".vs"));//tells opengl where the source code is and loads it
	glCompileShader(vs);//compiles the shader so it can be used by the graphics card
	if(glGetShaderi(vs,GL_COMPILE_STATUS)!=1) {//print out any compile errors
		System.err.println(glGetShaderInfoLog(vs));
		System.err.println("shader vs not compiled");//this tells us which shader failed
		System.exit(1);
	}
	
	fs=glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fs,readFile(path+".fs"));
	
	glCompileShader(fs);
	if(glGetShaderi(fs,GL_COMPILE_STATUS)!=1) {
		System.err.println(glGetShaderInfoLog(fs));
		System.err.println("shader fs not compiled");
		System.exit(1);
	
		
	}
	//attach our shaders to the program
	glAttachShader(program,vs);
	glAttachShader(program,fs);
	
	}
private  void createShaders(String path1,String path2) {
		
		vs=glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vs,readFile(path1+".vs"));//tells opengl where the source code is and loads it
	glCompileShader(vs);//compiles the shader so it can be used by the graphics card
	if(glGetShaderi(vs,GL_COMPILE_STATUS)!=1) {//print out any compile errors
		System.err.println(glGetShaderInfoLog(vs));
		System.err.println("shader vs not compiled");//this tells us which shader failed
		System.exit(1);
	}
	
	fs=glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fs,readFile(path2+".fs"));
	
	glCompileShader(fs);
	if(glGetShaderi(fs,GL_COMPILE_STATUS)!=1) {
		System.err.println(glGetShaderInfoLog(fs));
		System.err.println("shader fs not compiled");
		System.exit(1);
	
		
	}
	
	//attach our shaders to the program
	glAttachShader(program,vs);
	glAttachShader(program,fs);
	
	}
	
	 private void Locations() { //this method tells our shader where the location of our attributes is
		   
		 glBindAttribLocation(program,0,"vertices");
		 glBindAttribLocation(program,1,"textureCoords");
			
		   locationSlot=2;
	   }

   private  void linkandValidate() {
	
	glLinkProgram(program);
	if(glGetProgrami(program,GL_LINK_STATUS)!=1) {
		System.err.println(glGetProgramInfoLog(program));
		System.err.println("program link failed");
		System.exit(1);
	}
	glValidateProgram(program);
	if(glGetProgrami(program,GL_VALIDATE_STATUS)!=1) {
		System.err.println(glGetProgramInfoLog(program));
		System.err.println("program Validate failed");
		System.exit(1);
	}
	
}
   public void bind() {
		glUseProgram(program);//tells opengl we want to use this program
   }
   
   
   public void unbind() {
		glUseProgram(0);
		
	}
	
	
	
	
	//load values
	public void loadInt(int location,int value) {
	     	glUniform1i(location,value);

}

	public void loadFloat(int location,float value) {
	   	     	glUniform1f(location,value);
		
	}
	
	public void loadVec2(int location,Vector2f vec) {
	     	glUniform2f(location,vec.x,vec.y);
			
}
	
	public void loadVec4(int location,Vector4f vec) {
     	glUniform4f(location,vec.x,vec.y,vec.z,vec.w);
		
}	
	
	
	public void loadMat(int location,Matrix4f matrix) {
         FloatBuffer matrixBuffer=BufferUtils.createFloatBuffer(16);	
         matrix.get(matrixBuffer);
         
        GL20.glUniformMatrix4fv(location,false, matrixBuffer);
    		
	
	}
	
	
	
	//allowing our uniforms to be written to by defining  a location based on the name of the uniform
	
	 public int makeLocation(String name) throws Exception {
	    	int location=glGetUniformLocation(program,name);
	    	if(location==-1) {//if it failed to find the location print a error 
	    		throw new IllegalStateException("Uniform \""+name+"\" :Location not found");
	    	}
	    	
	 return location;  
	 }
	public void makeNewLocation(String location) {
		
		 glBindAttribLocation(program,locationSlot,location);
		 linkandValidate();
		 locationSlot++;
	}

	

}
   