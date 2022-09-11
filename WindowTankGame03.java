import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * main class for starting the game
 */
public class WindowTankGame03 extends JFrame {

    // define Mypanel
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        WindowTankGame03 windowTankGame01 = new WindowTankGame03();

    }
    public WindowTankGame03(){
        System.out.println("Please choose an option:  1: start a new game 2: continue the game ");
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
                System.out.println("close monitoring");
                Recorder01.saveRecord();
                //Recorder01.SaveTank(MyPanel.enemyTank);
                System.exit(0);
            }
        });
    }
}
