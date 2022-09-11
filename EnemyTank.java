
import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

/**
 * a subclass from Tank, designed for various operations of enemy tanks
 */
public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    private Vector<EnemyTank> enemyTanks = new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public boolean isTouchEnemy(){

        switch (getDirection()){
            case 0: // When the tank is facing up
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank02 = enemyTanks.get(i);
                    if(enemyTank02 != this){
                        if(enemyTank02.getDirection() == 0 || enemyTank02.getDirection()==2){
                            // When the tank hit is up or down

                            // When touching the upper left coordinate
                            if(this.getX()>= enemyTank02.getX() && this.getX()<=enemyTank02.getX()+40
                            && this.getY()>=enemyTank02.getY() && this.getY() <= enemyTank02.getY()+60){
                                return true;
                            }

                            // When touching the upper right coordinates
                            if(this.getX()+40 >= enemyTank02.getX() && this.getX()+40<=enemyTank02.getX()+40
                                    && this.getY()>=enemyTank02.getY() && this.getY() <= enemyTank02.getY()+60){
                                return true;
                            }

                        }
                        if(enemyTank02.getDirection() == 1 || enemyTank02.getDirection()==3){
                            // When the tank hit is left or right

                            // // When touching the upper left coordinate
                            if(this.getX()>= enemyTank02.getX()
                                    && this.getX()<=enemyTank02.getX()+60
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+40){
                                return true;
                            }

                            // When touching the upper right coordinate
                            if(this.getX()+40 >= enemyTank02.getX()
                                    && this.getX()+40<=enemyTank02.getX()+60
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+40){
                                return true;
                            }

                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank02 = enemyTanks.get(i);
                    if(enemyTank02 != this){
                        if(enemyTank02.getDirection() == 0 || enemyTank02.getDirection()==2){
                            // When the tank hit is up or down

                            // When touching the upper right  coordinates
                            if(this.getX() +60>= enemyTank02.getX()
                                    && this.getX()+60 <=enemyTank02.getX()+40
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+60){
                                return true;
                            }

                            // When the coordinates of the lower right corner are encountered
                            if(this.getX()+60 >= enemyTank02.getX()
                                    && this.getX()+60<=enemyTank02.getX()+40
                                    && this.getY()+40>=enemyTank02.getY()
                                    && this.getY()+40 <= enemyTank02.getY()+60){
                                return true;
                            }

                        }
                        if(enemyTank02.getDirection() == 1 || enemyTank02.getDirection()==3){
                            // When the tank hit is left or right

                            // // When touching the upper right corner coordinates
                            if(this.getX() +60 >= enemyTank02.getX()
                                    && this.getX()+60 <=enemyTank02.getX()+60
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+40){
                                return true;
                            }

                            // When touching the lower right corner coordinates
                            if(this.getX()+60 >= enemyTank02.getX()
                                    && this.getX()+60<=enemyTank02.getX()+60
                                    && this.getY()+40>=enemyTank02.getY()
                                    && this.getY() +40 <= enemyTank02.getY()+40){
                                return true;
                            }

                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank02 = enemyTanks.get(i);
                    if(enemyTank02 != this){
                        if(enemyTank02.getDirection() == 0 || enemyTank02.getDirection()==2){
                            // When the tank you encounter is up or down

                            // When touching the coordinates of the lower left corner
                            if(this.getX() >= enemyTank02.getX()
                                    && this.getX() <=enemyTank02.getX()+40
                                    && this.getY()+60>=enemyTank02.getY()
                                    && this.getY()+60 <= enemyTank02.getY()+60){
                                return true;
                            }

                            // When touching the coordinates of the lower right corner
                            if(this.getX()+40 >= enemyTank02.getX()
                                    && this.getX()+40<=enemyTank02.getX()+40
                                    && this.getY()+60>=enemyTank02.getY()
                                    && this.getY()+60 <= enemyTank02.getY()+60){
                                return true;
                            }

                        }
                        if(enemyTank02.getDirection() == 1 || enemyTank02.getDirection()==3){
                            // When the tank you encounter is left or right

                            // // When touching the coordinates of the lower left corner
                            if(this.getX()  >= enemyTank02.getX()
                                    && this.getX()<=enemyTank02.getX()+60
                                    && this.getY()+60>=enemyTank02.getY()
                                    && this.getY() +60<= enemyTank02.getY()+40){
                                return true;
                            }

                            // When touching the coordinates of the lower right corner
                            if(this.getX()+40 >= enemyTank02.getX()
                                    && this.getX()+40<=enemyTank02.getX()+60
                                    && this.getY()+60>=enemyTank02.getY()
                                    && this.getY() +60 <= enemyTank02.getY()+40){
                                return true;
                            }

                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank02 = enemyTanks.get(i);
                    if(enemyTank02 != this){
                        if(enemyTank02.getDirection() == 0 || enemyTank02.getDirection()==2){
                            // When the tank you encounter is up or down

                            // When touching the coordinates of the upper left corner
                            if(this.getX() >= enemyTank02.getX()
                                    && this.getX() <=enemyTank02.getX()+40
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+60){
                                return true;
                            }

                            // When touching the coordinates of the lower right corner
                            if(this.getX()>= enemyTank02.getX()
                                    && this.getX()<=enemyTank02.getX()+40
                                    && this.getY()+60>=enemyTank02.getY()
                                    && this.getY()+60 <= enemyTank02.getY()+60){
                                return true;
                            }

                        }
                        if(enemyTank02.getDirection() == 1 || enemyTank02.getDirection()==3){
                            // When the tank you encounter is left or right

                            // // When touching the coordinates of the upper left corner
                            if(this.getX()  >= enemyTank02.getX()
                                    && this.getX() <= enemyTank02.getX()+60
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+40){
                                return true;
                            }

                            // When touching the coordinates of the lower right corner
                            if(this.getX() >= enemyTank02.getX()
                                    && this.getX() <=enemyTank02.getX()+60
                                    && this.getY()+40 >=enemyTank02.getY()
                                    && this.getY() +40 <= enemyTank02.getY()+40){
                                return true;
                            }

                        }
                    }
                }
            break;
        }

       return false;
    }


    @Override
    public void run() {
        while(true){
            setDirection((int)(Math.random()*4));
            if(isLive && shots.size()<10){
                Shot shot = null;
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
                // starting the thread of shot
                new Thread(shot).start();
            }


            switch (getDirection()){
                case 0:
                    for (int i = 0; i < 20; i++) {
                        if(this.getY()>0 && !isTouchEnemy()){
                        moveUp();}
                        try {
                            Thread.sleep(140);   //
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 20; i++) {
                        if(this.getX()+60 < 1000 && ! isTouchEnemy()){
                        moveRIght();}
                        try {
                            Thread.sleep(140);   //
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 20; i++) {
                        if(this.getY()+60 < 750 && !isTouchEnemy()){
                        moveDown();}
                        try {
                            Thread.sleep(140);   //
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if(this.getX() > 0  && !isTouchEnemy()){
                        moveLeft();}
                        try {
                            Thread.sleep(140);   //
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            if(!isLive){
                break;
            }
        }
    }
}
