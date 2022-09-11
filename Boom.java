package TankGame03;

/**
 * @author Carson
 * @Version
 */
public class Boom {
    int x,y;
    int life = 10;
    boolean isLive = true;

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown(){
        if(life>0){
        life--;
        } else{
            isLive = false;
        }
    }
}
