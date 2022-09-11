
import java.util.Vector;

/**
 * this us subclass from Tank for our own tank
 */
public class Hero extends Tank {
    Shot shot =null;
    boolean isLive = true;
    Vector<Shot> shots = new Vector<>();    // for multiple bullets

    public Hero(int x, int y) {

        super(x, y);
    }

    public void shotEnemyTank(){

        if(shots.size()==5){ //Limit the maximum number of bullets fired by your own tank on the screen
            return;
        }
        switch (getDirection()){
            case 0:
                shot = new Shot( getX()+20,getY(),0);
                break;
            case 1:
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot =  new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot =  new Shot(getX(),getY()+20,3);
                break;
        }

        shots.add(shot);
        // start the thread
        new Thread(shot).start();

    }
}
