package Data;



public enum Tiles{
//these are just test tiles but i think we need to load all of the tiles we use into a single tile sheet so all we have to do is bind the texture once and just change the u v coords
     
Dirt(new float[]{
    0,0,//top left
    0.25f,0,//top right
    0.25f,0.25f,//bottom right
    0,0.25f//bottom left
//-walk------red-------green-------blue----
},true,(byte)0xbe,(byte)0x7c,(byte)0x51),
Grass( new float[]{
    0.25f,0,
    0.5f,0,
    0.5f,0.25f,
    0.25f,0.25f


},true,(byte) 0x00,(byte) 0xce,(byte) 0x46),
Grassw( new float[]{
    0.5f,0,
    0.75f,0,
    0.75f,0.25f,
    0.5f,0.25f


},true,(byte) 0x00,(byte) 0xac,(byte) 0x46),
 Water(new float[]{
    0.75f,0.25f,
    1,0.25f,
    1,0.5f,
    0.75f,0.5f


},false,(byte)0x99,(byte)0xd9,(byte)0xea);

     private  boolean walkable;
      private byte colorR,colorG,colorB;
      private float[] uvcoords;
   ;
    
Tiles(float[] uvcoords,boolean walkable,byte colorR,byte colorG,byte colorB){
                
                this.uvcoords=uvcoords;
                this.walkable=walkable;
                this.colorR=colorR;
                this.colorG=colorG;
                this.colorB=colorB;




}


//all of out getters
     

       public Boolean getWalkable(){
        return walkable;
    }   
    public Byte getColorR(){
        return colorR;
    }   
    public Byte getColorG(){
        return colorG;
    }   
    public Byte getColorB(){
        return colorB;
    }   
    public float[] getUVcoords(){
        return uvcoords;
    }
}