import javax.swing.*;
import java.io.*;

public class Aufgabe5 {
    public static void main(String[]args) throws IOException {
        String text;
        int length = 0;
        int zahl = Integer.parseInt(JOptionPane.showInputDialog("Welche Zahl wird gesucht?"));
        File file = new File("zahlen.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        while(true) {
            text = br.readLine();
            if (text==null){
                br.close();
                break;
            }
            if (text.equals(zahl)) {
                length++;
            }
        }
        System.out.println(length);
    }
}
