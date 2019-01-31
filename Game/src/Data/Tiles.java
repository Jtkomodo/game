package Data;



public enum Tiles{
//these are just test tiles but i think we need to load all of the tiles wwe use into a single tile sheet so all we have to do is bind the texture once and just change the u v coords
     
Dirt(0.0f,0.0f,64,64,true,(byte)0xbe,(byte)0x7c,(byte)0x51),Grass(64.0f,0.0f,64,64,true,(byte) 0x00,(byte) 0xce,(byte) 0x46),Grassw(128.0f,0.0f,64,64,true,(byte) 0x00,(byte) 0xac,(byte) 0x46),
 Water(192.0f,64.0f,64,64,false,(byte)0x99,(byte)0xd9,(byte)0xea);
	
     private float u,v,width,height;
     private  boolean walkable;
      private byte colorR,colorG,colorB;

         Tiles(float u,float v,float width,float height,boolean walkable,byte colorR,byte colorG,byte colorB){

                this.u=u;
                this.v=v;
                this.width=width;
                this.height=height;
                this.walkable=walkable;
                this.colorR=colorR;
                this.colorG=colorG;
                this.colorB=colorB;


         }
//all of out getters
        public float getU(){
            return u;
        }        
        public float getV(){
            return v;
        }   
        public float getWidth(){
            return width;
        }   
       public float getHeight(){
           return height;
       }

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
}