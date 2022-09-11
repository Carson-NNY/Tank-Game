package TankGame03;

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
 * @author Carson
 * @Version
 */
public class MyPanel extends JPanel implements KeyListener,Runnable  {
    //  定义我的坦克
    Hero hero = null;
     Vector<EnemyTank> enemyTank = new Vector<>(); // vector 多线程安全
    // 定义一个存放的node对象Vector，用于恢复敌人坦克的坐标方向
    Vector<Node> nodes = new Vector<>();

    int enemyTankSize = 6;
    // 因为爆炸效果只出现panel上，所以只在这里创建对象。。
    Vector<Boom> booms = new Vector<>();
    Image image = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {

        File file = new File(Recorder01.getRecordFile());
        if(file.exists()){
            nodes = Recorder01.GetNodesAndEnemyTankRec();
        }else{
            System.out.println("文件不存在，只能存在新的游戏");
            key = "1";
        }

        Recorder01.setEnemyTanks(enemyTank);    // OOP思想！Set方法相互连接

        hero = new Hero(100, 500);   // 初始化自己坦克

        switch (key) {
            case "1":
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank02 = new EnemyTank(100 * (i + 1), 0);
                    enemyTank02.setDirection(2);
                    // 这个把MyPanel和EnemyTank类里面的EnemyTank Vector连接起来
                    enemyTank02.setEnemyTanks(enemyTank);
                    new Thread(enemyTank02).start();

                    // 这种绘制子弹方法会根据坦克的位置而不停改变它的发射位置！
                    Shot shot = new Shot(enemyTank02.getX(), enemyTank02.getY(), enemyTank02.getDirection());
                    // 加入enemyTank02 的子弹vector成员
                    enemyTank02.shots.add(shot);
                    // 启动shot对象
                    new Thread(shot).start();
                    enemyTank.add(enemyTank02);
                }
                break;
            case "2":

                for (int i = 0; i < nodes.size(); i++) {// Nodes类很妙！存储敌人坐标等信息

                    Node node = nodes.get(i);
                    EnemyTank enemyTank02 = new EnemyTank(node.getX(), node.getY());
                    enemyTank02.setDirection(node.getDirect());
                    enemyTank02.setEnemyTanks(enemyTank);
                    new Thread(enemyTank02).start();

                    // 这种绘制子弹方法会根据坦克的位置而不停改变它的发射位置！
                    Shot shot = new Shot(enemyTank02.getX(), enemyTank02.getY(), enemyTank02.getDirection());
                    // 加入enemyTank02 的子弹vector成员
                    enemyTank02.shots.add(shot);
                    // 启动shot对象
                    new Thread(shot).start();
                    enemyTank.add(enemyTank02);
                }
                break;
            default:
                System.out.println("你的输入有误");
        }

        // 初始化图片对象
        image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_3.gif"));

        //image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_3.gif"));

        //音效
        new AePlayWave("/Users/carson/Downloads/java.bili/Chapter15/src/TankGame03/111.wav").start();

    }
    public void showInfo(Graphics g){
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累积击毁敌方坦克",1020,30);
        drawTank(1020,60,g,0,0);
        g.setColor(Color.black);
        g.drawString(Recorder01.getAllEnemyNum()+"",1080,100);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);    // 填充默认的是黑色
        showInfo(g);
        // 画出坦克-封装方法
        if(hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);
        }

//         画自己的子弹
//        if(hero.shot != null && hero.shot.isLive ==true ) {
//            g.fillOval(hero.shot.x,hero.shot.y,4,4);
//        }

        //绘制所有的子弹
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

        //画敌人坦克
        for (int i = 0; i < enemyTank.size(); i++) {
            EnemyTank enemyTank01 = enemyTank.get(i);   // 为了控制坦克的方向
            if(enemyTank01.isLive ) {
                drawTank(enemyTank01.getX(), enemyTank01.getY(), g, enemyTank01.getDirection(), 0);
                // 为了以后 多个子弹作准备，遍历子弹
                for (int j = 0; j < enemyTank01.shots.size(); j++) {
                    // 取出子弹
                    Shot shot = enemyTank01.shots.get(j);
                    if (shot.isLive) {
                        g.fillRect(shot.x, shot.y, 4, 4);
                    } else {  // 如果子弹死了不移除，子弹会被一直绘制
                        enemyTank01.shots.remove(shot);
                    }
                }
            }
        }


    }
    /**
     *
     * @param x 坦克的左上角x坐标
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

        // direction： 0向上，1向右，2向下，3向左
        switch (direction){
            case 0: // 向上
                g.fill3DRect(x,y,10,60,false);  // 左边轮子
                g.fill3DRect(x+30,y,10,60,false);// 右轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克盖子
                g.fillOval(x+10,y+20,20,20);// 圆形盖子
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1: // 向右
                g.fill3DRect(x,y,60,10,false);  // 左边轮子
                g.fill3DRect(x,y+30,60,10,false);// 右轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克盖子
                g.fillOval(x+20,y+10,20,20);// 圆形盖子
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2: // 向下
                g.fill3DRect(x,y,10,60,false);  // 左边轮子
                g.fill3DRect(x+30,y,10,60,false);// 右轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克盖子
                g.fillOval(x+10,y+20,20,20);// 圆形盖子
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3: // 向左
                g.fill3DRect(x,y,60,10,false);  // 左边轮子
                g.fill3DRect(x,y+30,60,10,false);// 右轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克盖子
                g.fillOval(x+20,y+10,20,20);// 圆形盖子
                g.drawLine(x+30,y+20,x,y+20);

            default:

                System.out.println("待定");
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
//            发射单颗子弹
//            if(hero.shot ==null || hero.shot.isLive == false) {
//                hero.shotEnemyTank();
//            }

            //发射多颗子弹
            hero.shotEnemyTank();
        }
        this.repaint();

    }



    public  void hitTank(Shot s, Tank tank){  // 击中敌方坦克

        // 判断敌人坦克的方向
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

        // 判断敌人坦克的方向
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
                // 判断自己的子弹是否击中
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

    // 为了让子弹不停重绘，将MyPanel做成线程
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
