package gameEngine;
import org.lwjgl.system.Pointer.Default;
import org.joml.*;
import Data.TilesData;;

public class Map{
   private int[][] mapData;
   private TilesData[][] tileData;
   private float[] uv=TilesData.Grass.getUVcoords(),lastuv;
   public static  boolean norenderR=true,norenderT=true,norenderL=true,norenderB=true;   
   private gameEngine.Tiles t;


   public Map(int[][] mapData,gameEngine.Tiles tile){
    tileData= new TilesData[mapData.length][mapData[0].length];       
    this. t=tile;
    this.mapData=mapData;
         for(int i=0;i<mapData.length;i++){
            for(int j=0;j<mapData[i].length;j++){
              // System.out.println(j+" "+i);
               switch(mapData[j][i]){
               
                   case 1:
                       tileData[j][i]=TilesData.Dirt;
                   break;
                       case 2:
                   tileData[j][i]=TilesData.Grassw;
                   break;
                   case 3:
                   tileData[j][i]=TilesData.Water;
                   break;
                   case 4:
                   tileData[j][i]=TilesData.Grass;
                   break;

                
                    }}}
                  
                  
      
                  }

   public void Draw(){
     
      t.Bind(0);
		t.setScale(128);
      int Mapwidth= tileData[0].length-1;
      int Mapheight=tileData.length;


      TilesData tile;


      i:for(int i=Mapwidth; i>-1;i--) {
         j:for(int j=0;j<Mapheight;j++) {
            Model.enable();
        	 tile=tileData[j][Mapwidth-i];
             lastuv=uv;
             uv=tile.getUVcoords();

if(lastuv !=uv){
             t.getModel().changeUV(uv);
             
}  t.setTranslation(new Vector2f(j,i));
            t.draw();
            if(!norenderR){
            	 Model.disable();
                 break;
            }
            if(!norenderT){
            	 Model.disable();
               i--;
               continue i;
            }
            if(!norenderB){
            	 Model.enable();
            	return;
            
            }
            if(!norenderL){
            	 Model.disable();
            	j++;
               continue j;
            }
            
         }
         
      }
      Model.enable();
      }




   }
