package TankGame03;


import java.util.Vector;

/**
 * @author Carson
 * @Version
 */
public class Hero extends Tank {
    Shot shot =null;
    boolean isLive = true;
    Vector<Shot> shots = new Vector<>();    // 多颗子弹实现

    public Hero(int x, int y) {

        super(x, y);
    }

    public void shotEnemyTank(){

        if(shots.size()==5){ //限制屏幕自己坦克最多射出子弹
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
        // 启动线程！
        new Thread(shot).start();

    }
}
