import java.io.*;
import java.util.Vector;

/**
 * Make this class a utility class for storing the cordinates
 */
public class Recorder01 {
    private static int allEnemyNum = 0;
    private static FileWriter fileWriter  = null;
    private static BufferedWriter bufferedWriter = null;
    private static BufferedReader bufferedReader = null;
    private static Vector<EnemyTank> enemyTanks = null;
    private  static Vector<Node> nodes = new Vector<>();
    private static String recordFile = "/Users/carson/Downloads/java.bili/Chapter15/src/TankGame03/record01.txt"; // it is subject to be changed


    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder01.enemyTanks = enemyTanks;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    // Used to read files and recover relevant information
    public static Vector<Node> GetNodesAndEnemyTankRec(){
        try {
             bufferedReader = new BufferedReader(new FileReader(recordFile));
             allEnemyNum = Integer.parseInt( bufferedReader.readLine());
             String line = "";
             while((line = bufferedReader.readLine()) != null){
                 String[] xzd = line.split(" ");
                 Node node = new Node(Integer.parseInt(xzd[0]), Integer.parseInt(xzd[1]),
                         Integer.parseInt(xzd[2]));
                 nodes.add(node);
             }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader !=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }


    public static int getAllEnemyNum(){
        return allEnemyNum;
    }

    public static void addAllEnemyNum(){
        Recorder01.allEnemyNum ++;
    }

    public static void saveRecord()  {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(recordFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedWriter.write(allEnemyNum+"\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank e = enemyTanks.get(i);
                if (e.isLive) {
                    String record = e.getX() + " " + e.getY() + " " + e.getDirection();
                    bufferedWriter.write(record + "\n");

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(bufferedWriter!= null){
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
