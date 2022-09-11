import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.Vector;

/**
 * the panel where all the operation take place and draw all the tanks and bullets
 */
public class MyPanel extends JPanel implements KeyListener,Runnable  {
    //  define our own tank
    Hero hero = null;
     Vector<EnemyTank> enemyTank = new Vector<>(); // vector - safe for Multithreading
    // Define a Vector object that stores node, which is used to restore the coordinate direction of the enemy tank.
    Vector<Node> nodes = new Vector<>();

    int enemyTankSize = 6;
    // Because the explosion effect only appears on panel, only objects are created here.
    Vector<Boom> booms = new Vector<>();
    Image image = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {

        File file = new File(Recorder01.getRecordFile());
        if(file.exists()){
            nodes = Recorder01.GetNodesAndEnemyTankRec();
        }else{
            System.out.println("The file does not exist and you can only start a new game.");
            key = "1";
        }

        Recorder01.setEnemyTanks(enemyTank);    // OOP thought! Using Set methods to connect each class or file

        hero = new Hero(100, 500);   // Initialize your own tank

        switch (key) {
            case "1":
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank02 = new EnemyTank(100 * (i + 1), 0);
                    enemyTank02.setDirection(2);
                    // This connects the EnemyTank Vector of MyPanel to the EnemyTank Vector in the EnemyTank class.
                    enemyTank02.setEnemyTanks(enemyTank);
                    new Thread(enemyTank02).start();

                    // This method of drawing bullets will constantly change the firing position of the tank according to its position!
                    Shot shot = new Shot(enemyTank02.getX(), enemyTank02.getY(), enemyTank02.getDirection());
                    // add shot object
                    enemyTank02.shots.add(shot);
                    new Thread(shot).start();
                    enemyTank.add(enemyTank02);
                }
                break;
            case "2":

                for (int i = 0; i < nodes.size(); i++) { // The Nodes class is wonderful!  Store information such as enemy coordinates

                    Node node = nodes.get(i);
                    EnemyTank enemyTank02 = new EnemyTank(node.getX(), node.getY());
                    enemyTank02.setDirection(node.getDirect());
                    enemyTank02.setEnemyTanks(enemyTank);
                    new Thread(enemyTank02).start();

                    Shot shot = new Shot(enemyTank02.getX(), enemyTank02.getY(), enemyTank02.getDirection());
                    // add shot object
                    enemyTank02.shots.add(shot);
                    new Thread(shot).start();
                    enemyTank.add(enemyTank02);
                }
                break;
            default:
                System.out.println("invalid input");
        }

        // Initialize picture object
        image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_3.gif"));


        //sound effect
        new AePlayWave("/Users/carson/Downloads/java.bili/Chapter15/src/TankGame03/111.wav").start();

    }
    public void showInfo(Graphics g){
        g.setColor(Color.black);
        Font font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("The number of enemy tanks destroyed is: ",1020,30);
        drawTank(1020,60,g,0,0);
        g.setColor(Color.black);
        g.drawString(Recorder01.getAllEnemyNum()+"",1080,100);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);    
        showInfo(g);
        // draw the tank
        if(hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);
        }

//         
//        if(hero.shot != null && hero.shot.isLive ==true ) {
//            g.fillOval(hero.shot.x,hero.shot.y,4,4);
//        }

        //Draw all the bullets.
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive == true) {
                g.fillOval(shot.x, shot.y, 4, 4);
            }else{
                hero.shots.remove(shot);
            }
        }


        for (int i = 0; i < booms.size(); i++) {
            Boom  boom = booms.get(i);
            if(boom.life>6){
                g.drawImage(image,boom.x,boom.y,60,60,this);
            }else if(boom.life>3){
                g.drawImage(image2,boom.x,boom.y,60,60,this);
            }else{
                g.drawImage(image3,boom.x,boom.y,60,60,this);
            }
            boom.lifeDown();
            if(boom.life == 0){
                booms.remove(boom);
            }
        }

        //Draw enemy tanks
        for (int i = 0; i < enemyTank.size(); i++) {
            EnemyTank enemyTank01 = enemyTank.get(i);   // To control the direction of the tank
            if(enemyTank01.isLive ) {
                drawTank(enemyTank01.getX(), enemyTank01.getY(), g, enemyTank01.getDirection(), 0);
                // To prepare for multiple bullets in the future, iterate the bullets.
                for (int j = 0; j < enemyTank01.shots.size(); j++) {
                    // Remove the bullet
                    Shot shot = enemyTank01.shots.get(j);
                    if (shot.isLive) {
                        g.fillRect(shot.x, shot.y, 4, 4);
                    } else {  // If the bullet dies and is not removed, the bullet will always be drawn
                        enemyTank01.shots.remove(shot);
                    }
                }
            }
        }


    }
    /**
     *
     * @param x The upper left corner of the tank x coordinate
     * @param y
     * @param g paintbrush
     * @param direction  the direction of the tank
     * @param type
     */
    public void drawTank(int x,int y,Graphics g, int direction,int type){

        switch (type){
            case 0: // 0 represents our own tanks
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.ORANGE);
        }

        // direction：0 up, 1 to the right, 2 down, 3 to the left
        switch (direction){
            case 0: //up
                g.fill3DRect(x,y,10,60,false);  // left wheel
                g.fill3DRect(x+30,y,10,60,false);// right wheel
                g.fill3DRect(x+10,y+10,20,40,false);//tank cover
                g.fillOval(x+10,y+20,20,20);//round cover
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1: // right
                g.fill3DRect(x,y,60,10,false);  // left wheel
                g.fill3DRect(x,y+30,60,10,false);// right wheel
                g.fill3DRect(x+10,y+10,40,20,false);//tank cover
                g.fillOval(x+20,y+10,20,20);// round cover
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2: // down
                g.fill3DRect(x,y,10,60,false);  // left wheel
                g.fill3DRect(x+30,y,10,60,false);// right wheel
                g.fill3DRect(x+10,y+10,20,40,false);//tank cover
                g.fillOval(x+10,y+20,20,20);// round cover
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3: // left
                g.fill3DRect(x,y,60,10,false);  // left wheel
                g.fill3DRect(x,y+30,60,10,false);// right wheel
                g.fill3DRect(x+10,y+10,40,20,false);//tank cover
                g.fillOval(x+20,y+10,20,20);// round cover
                g.drawLine(x+30,y+20,x,y+20);

            default:

                System.out.println("TBD");
        }
    }





    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            hero.setDirection(0);
            hero.moveUp();
        }else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirection(1);
            hero.moveRIght();
        }else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirection(2);
            hero.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirection(3);
            hero.moveLeft();
        }

        if(e.getKeyCode()==KeyEvent.VK_J){
//            Fire a single bullet
//            if(hero.shot ==null || hero.shot.isLive == false) {
//                hero.shotEnemyTank();
//            }

            //Fire multiple bullets
            hero.shotEnemyTank();
        }
        this.repaint();

    }



    public  void hitTank(Shot s, Tank tank){  // Hit an enemy tank

        // determine the direction of the enemy tank
        switch (tank.getDirection()){
            case 0:
            case 2:
                if(s.x > tank.getX() && s.x < tank.getX()+40 &&
                        s.y>tank.getY() && s.y < tank.getY()+60){
                    s.isLive =false;
                    tank.isLive= false;

                    if(tank instanceof EnemyTank){
                    enemyTankSize--;
                    Recorder01.addAllEnemyNum();
                    }

                    enemyTank.remove(tank);

                    booms.add(new Boom(tank.getX(), tank.getY()));

                }
                break;
            case 1:
            case 3:
                if(s.x > tank.getX() && s.x < tank.getX()+60 &&
                        s.y>tank.getY() && s.y < tank.getY()+40){
                    s.isLive =false;
                    tank.isLive= false;
                    if(tank instanceof EnemyTank){
                        enemyTankSize--;
                        Recorder01.addAllEnemyNum();
                    }
                    //if(tank instanceof EnemyTank){
                        enemyTank.remove(tank);
                    booms.add(new Boom(tank.getX(), tank.getY()));
                }
                break;
        }
    }
    public  void hitHeroTank(Shot s, Hero tank){

        // determine the direction of the enemy tank
        switch (tank.getDirection()){
            case 0:
            case 2:
                if(s.x > tank.getX() && s.x < tank.getX()+40 &&
                        s.y>tank.getY() && s.y < tank.getY()+60){
                    s.isLive =false;
                    tank.isLive= false;
                    booms.add(new Boom(tank.getX(), tank.getY()));
                }
                break;
            case 1:
            case 3:
                if(s.x > tank.getX() && s.x < tank.getX()+60 &&
                        s.y>tank.getY() && s.y < tank.getY()+40){
                    s.isLive =false;
                    tank.isLive= false;
                    //if(tank instanceof EnemyTank){
                    booms.add(new Boom(tank.getX(), tank.getY()));
                }
                break;
        }
    }

    public void hitEnemyTank(){
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if( shot!= null && shot.isLive){
                // Determine if your bullet hits
                for (int j = 0; j < enemyTank.size(); j++) {
                    EnemyTank enemyTank022 = enemyTank.get(j);
                    hitTank(shot,enemyTank022);
                }
            }
        }
    }

    public void hitHeroTank(){
        for (int i = 0; i < enemyTank.size(); i++) {
            EnemyTank enemyTank022 = enemyTank.get(i);
            for (int j = 0; j < enemyTank022.shots.size(); j++) {
                Shot shot =enemyTank022.shots.get(j);
                if( hero.isLive && shot.isLive){
                    hitHeroTank(shot,hero);
                }
            }
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
    }

    // In order to keep the bullet redrawn, make MyPanel a thread
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hitEnemyTank();
            hitHeroTank();
            this.repaint();
        }
    }
}
