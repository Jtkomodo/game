package guis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FunctionCaller {



 
   private String functionName;
   private Object[] args;
   private Method Function;
   private Class baseClass=GUIMEthods.class;
   private Class[]  argumentTypes=null;
   private Object objToCallfrom=null;

   public FunctionCaller(String functionName) {
	   this.functionName=functionName;
	   loadFuntion(); 
	   
   }
   
   
   public FunctionCaller(String functionName,Object[] args ) {
	 construct(functionName,args);
   }
   
   public FunctionCaller(String functionName,Object[] args,Class[] argumentTypes) {
	 construct(functionName,args,argumentTypes);
   }
  
   
   public FunctionCaller(String functionName,Object objToCallfrom) {
	   this.functionName=functionName;
	   this.objToCallfrom=objToCallfrom;
	   this.baseClass=objToCallfrom.getClass();
	   loadFuntion(); 
	   
   }
   
   
   public FunctionCaller(String functionName,Object[] args,Object objToCallfrom) {
	   this.objToCallfrom=objToCallfrom;
	   this.baseClass=objToCallfrom.getClass();
	   construct(functionName,args);
   }
   
   public FunctionCaller(String functionName,Object[] args,Class[] argumentTypes,Object objToCallfrom) {
	   this.objToCallfrom=objToCallfrom;
	   this.baseClass=objToCallfrom.getClass();
	   construct(functionName,args,argumentTypes);
   }
   
  
   public FunctionCaller(String functionName,Class baseClass) {
	   this.functionName=functionName;
	   this.baseClass=baseClass;
	   loadFuntion(); 
	   
   }
   
   
   public FunctionCaller(String functionName,Object[] args,Class baseClass ) {
	    this.baseClass=baseClass;
	   construct(functionName,args);
   }
   
   public FunctionCaller(String functionName,Object[] args,Class[] argumentTypes,Class baseClass) {
	 this.baseClass=baseClass;
	   construct(functionName,args,argumentTypes);
   }
   
   
   
   
  
   private void  construct(String functionName,Object[] args,Class[] argumentTypes) {
	   this.functionName=functionName;
	   this.args=args;
	  
	   this.argumentTypes=argumentTypes;
		
	   loadFuntion();
   }
  
   
   
   private void  construct(String functionName,Object[] args) {
	   
	   
	   this.functionName=functionName;
	   this.args=args;
	   
		if(args!=null) {
			 argumentTypes= new Class[args.length];
			
			 for(int i=0;i<args.length;i++) {
				 argumentTypes[i]=args[i].getClass();
			 }
		}else {
			argumentTypes=null;
		}
	   
	   loadFuntion();
	   
   }
		   
   
   
   
   
   
   
   
   
 private void loadFuntion() {  
   try {
		
		this.Function=this.baseClass.getMethod(functionName,argumentTypes);
		
	} catch (NoSuchMethodException e) {
		
		e.printStackTrace();
		System.exit(3007);	
	} catch (SecurityException e) {
		
		e.printStackTrace();
		System.exit(3002);
	}
 }	
   
   
   
   
   
   
   
   
   
   
   
   public void invoke() {
	  try {
		this.Function.invoke(objToCallfrom, args);
	} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		
		e.printStackTrace();
	}
	   
	   
	   
	   
   }










}

