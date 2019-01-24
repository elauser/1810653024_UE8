import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;


public class Aufgabe1 {
    public static void main(String[] args) {
        while (true) {
            try {
                logTrys();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        if (!file.exists()){
            logger(un,pw,false);
            return "Invalid Username";
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String pwr = br.readLine();
        br.close();
        if ((pwr.equals(encrypt(pw)))){
            logger(un,pw,true);
            return "Mein Geheimnis ist... Beim duschen bin ich nackt :^)";
        }
        else{
            logger(un,pw,false);
            return "Invalid Password";
        }
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
    public static void logger(String un, String pw, boolean li) throws IOException {
        String sep = System.getProperty("line.separator");
        DateTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime now = LocalDateTime.now();
        File log = new File("log.txt");
        if(!log.exists())log.createNewFile();
        FileWriter lfw = new FileWriter(log,true);
        BufferedWriter bw = new BufferedWriter(lfw);
        bw.write(now.format(df) + " UN: " + un + " PW: " + pw.substring(0,1) + "###" + pw.substring(pw.length()-2,pw.length()) + " " + li);
        bw.newLine();
        bw.close();
    }
    public static void logTrys() throws IOException {
        String text;
        int lengthTrue = 0;
        int lengthFalse = 0;
        File log = new File("log.txt");
        if(!log.exists()) System.out.println("No log file");
        FileReader fr = new FileReader(log);
        BufferedReader br = new BufferedReader(fr);
        while(true) {
            text = br.readLine();
            if (text==null){
                br.close();
                break;
            }
            text = text.substring(text.length()-4, text.length());

            if (text.contains("true")) {
                lengthTrue++;
            }
            else lengthFalse++;
        }
        System.out.println("True: " + lengthTrue + " False: " + lengthFalse);
    }
}

