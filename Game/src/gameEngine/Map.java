package gameEngine;
import org.lwjgl.system.Pointer.Default;
import org.joml.*;
import Data.Tiles;;

public class Map{
   private int[][] mapData;
   private Tiles[][] tileData;
   public static  boolean norenderR=true,norenderT=true,norenderL=true,norenderB=true;
   private float lastU,lastV ,Texwidth=256,Texheight=256,wi=64,h=64,Texx=64,Texy=0;   
   
   public Map(int[][] mapData){
    tileData= new Tiles[mapData.length][mapData[0].length];       
     this.mapData=mapData;
         for(int i=0;i<mapData.length;i++){
            for(int j=0;j<mapData[i].length;j++){
              // System.out.println(j+" "+i);
               switch(mapData[j][i]){
               
                   case 1:
                       tileData[j][i]=Tiles.Dirt;
                   break;
                       case 2:
                   tileData[j][i]=Tiles.Grassw;
                   break;
                   case 3:
                   tileData[j][i]=Tiles.Water;
                   break;
                   case 4:
                   tileData[j][i]=Tiles.Grass;
                   break;

                
                    }}}
         
          
         
   
   
   
   
   }

   public void Draw(gameEngine.Tiles t){
      int Mapwidth= tileData[0].length-1;
      int Mapheight=tileData.length;


      Tiles tile;


      i:for(int i=Mapwidth; i>-1;i--) {
         j:for(int j=0;j<Mapheight;j++) {
            tile=tileData[j][Mapwidth-i];
         lastU=Texx;
         lastV=Texy;
           Texx=tile.getU();
           Texy=tile.getV();		
         if(lastU!=Texx || lastV!=Texy){   
            float[] uv={
                  Texx/Texwidth,Texy/Texheight,
                  (Texx+wi)/Texwidth,Texy/Texheight,
                  (Texx+wi)/Texwidth,(Texy+h)/Texheight,
                  Texx/Texwidth,(Texy+h)/Texheight
                  };
             t.getModel().changeUV(uv);
               }
            t.setTranslation(new Vector2f(j,i));
            t.draw();
            if(!norenderR){
                 
                 break;
            }
            if(!norenderT){
                
               i--;
               continue i;
            }
            if(!norenderB){
             return;
            
            }
            if(!norenderL){
            j++;
               continue j;
            }
            
         }
         
      }
      }




   }
