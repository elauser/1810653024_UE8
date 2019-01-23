import javax.swing.*;
import java.io.*;
import java.util.ArrayList;



public class Aufgabe1 {
    public static void main(String[] args) {
        while (true) {
            int optionen = Integer.parseInt(JOptionPane.showInputDialog("1 = anmelden 2 = registrieren 3 = beenden"));
            if (optionen == 1) {
                String un = JOptionPane.showInputDialog("Geben Sie den Usernamen ein");
                String pw = JOptionPane.showInputDialog("Geben Sie das Passwort ein");
                try {
                    System.out.println(login(un, pw));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (optionen == 2) {
                String un = JOptionPane.showInputDialog("Geben Sie den Usernamen ein");
                String pw = JOptionPane.showInputDialog("Geben Sie das Passwort ein");
                try {
                    createUser(un, pw);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else break;
        }
    }

    public static void createUser(String un, String pw) throws IOException {
        File file = new File("C:\\Users\\User\\Desktop\\User\\" + un + ".txt");
        if (file.exists()) {
            System.out.println("Username already taken");
        } else {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(encrypt(pw));
            bw.close();
            System.out.println("User erstellt");
        }
    }

    public static String login(String un, String pw) throws IOException {
        File file = new File("C:\\Users\\User\\Desktop\\User\\" + un + ".txt");
        if (!file.exists()) return "Invalid Username";
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String pwr = br.readLine();
        br.close();
        if ((pwr.equals(encrypt(pw)))) return "Mein Geheimnis ist... Beim duschen bin ich nackt :^)";
        else return "Invalid Password";
    }

    public static String encrypt(String pw) {
        char[] charArray = pw.toCharArray();

        char[] cryptArray = new char[charArray.length];

        for (int i = 0; i < charArray.length; i++) {

            int verschiebung = (charArray[i] + 20) % 128;
            cryptArray[i] = (char) (verschiebung);

        }
        String re = new String(cryptArray);
        return re;
    }
}

