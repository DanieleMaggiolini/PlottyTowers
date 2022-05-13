package towers;

import static helpz.Constants.Projectiles.*;

public class AttackAnimation {
    
    private int narutoIndex=0;
    private int sasukeIndex=0;
    private int sakuraIndex=0;
    private int type;
    
    public AttackAnimation(int type){
        this.type=type;
    }
    public int getAnimationIndex(){
        switch(type){
            case SHURIKEN:
                return updateNarutoIndex();
            case FIREBALL:
                return updateSasukeIndex();
            case PUNCH:
                return updateSakuraIndex();
        }
        return 0;
    }
    
    int per1=5;
    private int updateNarutoIndex() {
        int index=0;
        if(narutoIndex<4*per1){
           index=narutoIndex;
           narutoIndex++;
        }else{
           narutoIndex=0;
        }     
        return index/per1;
    }
    
    int per2=20;
    private int updateSasukeIndex() {
        int index=0;
        if(sasukeIndex<3*per2){
           index=sasukeIndex;
           sasukeIndex++;
        }else{
            return 2;
        }     
        return index/per2;
    }
    
    int per3=5; 
    private int updateSakuraIndex() {
        int index=0;
        if(sakuraIndex<10*per3){
           index=sakuraIndex;
           sakuraIndex++;
        }else{
            return 9;
        }     
        return index/per3;
    }
}
