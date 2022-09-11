package TankGame03;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author Carson
 * @Version
 */
public class WindowTankGame03 extends JFrame {

    // 定义Mypanel
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        WindowTankGame03 windowTankGame01 = new WindowTankGame03();

    }
    public WindowTankGame03(){
        System.out.println("请输入选择1：开始新游戏  2： 继续上句游戏 ");
        String key = scanner.next();
        mp= new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("间听关闭");
                Recorder01.saveRecord();
                //Recorder01.SaveTank(MyPanel.enemyTank);
                System.exit(0);
            }
        });
    }
}
