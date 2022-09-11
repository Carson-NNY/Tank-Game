package TankGame03;

import java.util.Vector;

/**
 * @author Carson
 * @Version
 */
public class Tank {
    private int x;
    private int y;
    private int direction;
    private int speed;
    boolean isLive = true;

    public Tank(int x, int y, Vector<EnemyTank> enemyTank,Hero hero) {
        this.x = x;
        this.y = y;
        speed = 12;
    }
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 12;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void moveUp(){
        if(getY()>0){
        y -= speed;}
        else{
            setDirection(2);
        }
    }
    public void moveRIght(){
        if(getX()+60 < 1000){
        x += speed;}
        else{
            setDirection(3);
        }

    }
    public void moveDown(){
        if(getY()+150 < 750){
        y += speed;}

        else{
            setDirection(0);
        }
    }
    public void moveLeft(){
        if(getX()>0){
        x -= speed;}
        else{
            setDirection(1);
        }
    }

}

