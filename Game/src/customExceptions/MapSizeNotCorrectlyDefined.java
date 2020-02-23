package customExceptions;

public class MapSizeNotCorrectlyDefined extends Exception {

	/**
	 * 
	 */
	public MapSizeNotCorrectlyDefined(String fileName) {
		
		super("map:"+fileName+" size not corretly defined must have first line of file be\"width,height\"");
	}
	
	
	

	
	
	
	
	
	private static final long serialVersionUID = 1L;

}
