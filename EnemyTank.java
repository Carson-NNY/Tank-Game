package TankGame03;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

/**
 * @author Carson
 * @Version
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
            case 0: // 当当前坦克向上时
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank02 = enemyTanks.get(i);
                    if(enemyTank02 != this){
                        if(enemyTank02.getDirection() == 0 || enemyTank02.getDirection()==2){
                            // 当碰到的坦克为 上或下时

                            // 当碰到左上角坐标
                            if(this.getX()>= enemyTank02.getX() && this.getX()<=enemyTank02.getX()+40
                            && this.getY()>=enemyTank02.getY() && this.getY() <= enemyTank02.getY()+60){
                                return true;
                            }

                            // 当碰到右上角坐标
                            if(this.getX()+40 >= enemyTank02.getX() && this.getX()+40<=enemyTank02.getX()+40
                                    && this.getY()>=enemyTank02.getY() && this.getY() <= enemyTank02.getY()+60){
                                return true;
                            }

                        }
                        if(enemyTank02.getDirection() == 1 || enemyTank02.getDirection()==3){
                            // 当碰到的坦克为 左或右 时

                            // // 当碰到左上角坐标
                            if(this.getX()>= enemyTank02.getX()
                                    && this.getX()<=enemyTank02.getX()+60
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+40){
                                return true;
                            }

                            // 当碰到右上角坐标
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
                            // 当碰到的坦克为 上或下时

                            // 当碰到右上角坐标
                            if(this.getX() +60>= enemyTank02.getX()
                                    && this.getX()+60 <=enemyTank02.getX()+40
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+60){
                                return true;
                            }

                            // 当碰到右下角坐标
                            if(this.getX()+60 >= enemyTank02.getX()
                                    && this.getX()+60<=enemyTank02.getX()+40
                                    && this.getY()+40>=enemyTank02.getY()
                                    && this.getY()+40 <= enemyTank02.getY()+60){
                                return true;
                            }

                        }
                        if(enemyTank02.getDirection() == 1 || enemyTank02.getDirection()==3){
                            // 当碰到的坦克为 左或右 时

                            // // 当碰到右上角坐标
                            if(this.getX() +60 >= enemyTank02.getX()
                                    && this.getX()+60 <=enemyTank02.getX()+60
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+40){
                                return true;
                            }

                            // 当碰到右下角坐标
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
                            // 当碰到的坦克为 上或下时

                            // 当碰到坐下角坐标
                            if(this.getX() >= enemyTank02.getX()
                                    && this.getX() <=enemyTank02.getX()+40
                                    && this.getY()+60>=enemyTank02.getY()
                                    && this.getY()+60 <= enemyTank02.getY()+60){
                                return true;
                            }

                            // 当碰到右下角坐标
                            if(this.getX()+40 >= enemyTank02.getX()
                                    && this.getX()+40<=enemyTank02.getX()+40
                                    && this.getY()+60>=enemyTank02.getY()
                                    && this.getY()+60 <= enemyTank02.getY()+60){
                                return true;
                            }

                        }
                        if(enemyTank02.getDirection() == 1 || enemyTank02.getDirection()==3){
                            // 当碰到的坦克为 左或右 时

                            // // 当碰到左下角坐标
                            if(this.getX()  >= enemyTank02.getX()
                                    && this.getX()<=enemyTank02.getX()+60
                                    && this.getY()+60>=enemyTank02.getY()
                                    && this.getY() +60<= enemyTank02.getY()+40){
                                return true;
                            }

                            // 当碰到右下角坐标
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
                            // 当碰到的坦克为 上或下时

                            // 当碰到左上 角坐标
                            if(this.getX() >= enemyTank02.getX()
                                    && this.getX() <=enemyTank02.getX()+40
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+60){
                                return true;
                            }

                            // 当碰到右下角坐标
                            if(this.getX()>= enemyTank02.getX()
                                    && this.getX()<=enemyTank02.getX()+40
                                    && this.getY()+60>=enemyTank02.getY()
                                    && this.getY()+60 <= enemyTank02.getY()+60){
                                return true;
                            }

                        }
                        if(enemyTank02.getDirection() == 1 || enemyTank02.getDirection()==3){
                            // 当碰到的坦克为 左或右 时

                            // // 当碰到左上角坐标
                            if(this.getX()  >= enemyTank02.getX()
                                    && this.getX() <= enemyTank02.getX()+60
                                    && this.getY()>=enemyTank02.getY()
                                    && this.getY() <= enemyTank02.getY()+40){
                                return true;
                            }

                            // 当碰到右下角坐标
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
                // 启动shot的线程
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
