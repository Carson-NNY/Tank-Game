import java.awt.*;
import java.io.Serializable;

/**
 * the class for bullets
 */
public class Shot implements Runnable, Serializable {
    int x;
    int y;
    int direction;
    int speed =6;    // at what speed that bullets move on the screen
    boolean isLive = true;

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    @Override
    public void run() {
        while(true){

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (direction){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
                default:
            }

            System.out.println("bullet x: " +x+ " y: "+ y);
            if( ! (x>=0 && x <= 1000 && y >=0 && y<= 750)){
                isLive = false;
                break;
            }
        }
    }

}
